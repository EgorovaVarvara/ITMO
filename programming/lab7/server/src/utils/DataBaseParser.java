package utils;

import baseClasses.Coordinates;
import baseClasses.Label;
import baseClasses.MusicBand;
import baseClasses.MusicGenre;
import collection.CollectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;

public class DataBaseParser implements Parser{
    private QueryManager queryManager = new QueryManager();
    private final Connection connection = DataBaseManager.connect();
    @Override
    public void save() {
        try{
            PreparedStatement deleteAll = connection.prepareStatement(queryManager.deleteAll);
            deleteAll.execute();
            HashSet<MusicBand> musicBands = CollectionManager.getCollection();
            for (MusicBand musicBand : musicBands){
                PreparedStatement add = connection.prepareStatement(queryManager.addSpacialMusicBand);
                add.setInt(1, musicBand.getId());
                add.setString(2, musicBand.getName());
                add.setLong(3, musicBand.getCoordinates().getX());
                add.setFloat(4, musicBand.getCoordinates().getY());
                add.setString(5, musicBand.getCreationDate().toString());
                add.setInt(6, musicBand.getNumberOfParticipants());
                add.setString(7, musicBand.getMusicGenre().toString());
                add.setLong(8, musicBand.getLabel().getBands());
                add.setString(9, musicBand.getUser_login());
                add.executeQuery();
            }
            ServerLogger.getLogger().info("Коллекция сохранена в базу данных.");
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении к базе данных. Колекция не сохранена.");
        }
    }

    @Override
    public HashSet<MusicBand> load() {
        HashSet<MusicBand> musicBands = new HashSet<>();
        try{
            PreparedStatement selectAll = connection.prepareStatement(queryManager.selectAllObjects);
            ResultSet result = selectAll.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                Long x = result.getLong("coordinate_x");
                float y = result.getFloat("coordinate_y");
                LocalDateTime creationDate = LocalDateTime.parse(result.getString("creation_date"));
                int numberOfParticipants = result.getInt("number_of_participants");
                MusicGenre musicGenre = MusicGenre.valueOf(result.getString("music_genre"));
                long bands = result.getLong("bands");
                String user_login = result.getString("user_login");
                MusicBand musicBand = new MusicBand(id, name, new Coordinates(x, y), creationDate, numberOfParticipants, musicGenre, new Label(bands), user_login);
                musicBands.add(musicBand);
            }
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении или чтении данных из базы данных. Создана пустая коллекция");
        }
        return musicBands;
    }
}
