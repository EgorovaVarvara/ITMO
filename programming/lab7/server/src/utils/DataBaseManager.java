package utils;

import baseClasses.MusicBand;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class DataBaseManager {
    private QueryManager queryManager = new QueryManager();
    public static Connection connect(){
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s408575", "");
        } catch (ClassNotFoundException | SQLException e){
            ServerLogger.getLogger().warning("Ошибка при подключении к базе данных");
        }
        return null;
    }
    public Response registration(User user){
        try {
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement(queryManager.findUser);
            findUser.setString(1, user.getLogin());
            ResultSet resultSet = findUser.executeQuery();
            if (!resultSet.next()){
                PasswordManager passwordManager = new PasswordManager();
                PreparedStatement addUser = connection.prepareStatement(queryManager.addUser);
                addUser.setString(1, user.getLogin());
                addUser.setString(2, user.getPassword());
                addUser.setString(3, passwordManager.hashPassword(user.getPassword()));
                addUser.execute();
                return new Response(ResponseStatus.OK, "Регистрация прошла успешно!");
            } else {
                return new Response(ResponseStatus.ERROR,"Пользователь с таким логином уже существует. Попробуй еще раз: ");
            }
        } catch (SQLException | NullPointerException e){
            return new Response(ResponseStatus.ERROR,"Ошибка подключения к базе данных. Попробуй еще раз: ");
        }
    }
    public Response authorisation(User user){
        try{
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement(queryManager.findUser);
            findUser.setString(1, user.getLogin());
            ResultSet resultSet = findUser.executeQuery();
            if (resultSet.next()){
                if (resultSet.getString("password").equals(user.getPassword())) return new Response(ResponseStatus.OK, "Вход выполнен успешно!");
                return new Response(ResponseStatus.ERROR, "Введен неверный пароль. Попробуй еще раз: ");
            } else {
                return new Response(ResponseStatus.ERROR, "Пользователь с таким логином не найден. Попробуй еще раз: ");
            }
        } catch (SQLException | NullPointerException e){
            return new Response(ResponseStatus.ERROR, "Ошибка подключения к базе данных. Попробуй еще раз: ");
        }
    }
    public boolean updateObject(MusicBand newMusicBand, String user){
        try{
            Connection connection = connect();
            PreparedStatement update = connection.prepareStatement(queryManager.updateObject);
            update.setString(1, newMusicBand.getName());
            update.setLong(2, newMusicBand.getCoordinates().getX());
            update.setFloat(3, newMusicBand.getCoordinates().getY());
            update.setInt(4, newMusicBand.getNumberOfParticipants());
            update.setString(5, newMusicBand.getMusicGenre().toString());
            update.setLong(6, newMusicBand.getLabel().getBands());
            update.setString(7, user);
            update.setInt(8, newMusicBand.getId());
            ResultSet resultSet = update.executeQuery();
            return (resultSet.next());
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return false;
    }
    public boolean removeObject(int id, String user_login){
        try{
            Connection connection = connect();
            PreparedStatement remove = connection.prepareStatement(queryManager.deleteObject);
            remove.setString(1, user_login);
            remove.setInt(2, id);
            ResultSet resultSet = remove.executeQuery();
            return resultSet.next();
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return false;
    }
    public int addObject(MusicBand musicBand){
        try{
            Connection connection = connect();
            PreparedStatement add = connection.prepareStatement(queryManager.addMusicBand);
            add.setString(1, musicBand.getName());
            add.setLong(2, musicBand.getCoordinates().getX());
            add.setFloat(3, musicBand.getCoordinates().getY());
            add.setString(4, musicBand.getCreationDate().toString());
            add.setInt(5, musicBand.getNumberOfParticipants());
            add.setString(6, musicBand.getMusicGenre().toString());
            add.setLong(7, musicBand.getLabel().getBands());
            add.setString(8, musicBand.getUser_login());
            ResultSet resultSet = add.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return -1;
    }
    public int addIfMax(MusicBand musicBand){
        try{
            Connection connection = connect();
            PreparedStatement numbersOfParticipants = connection.prepareStatement(queryManager.selectNumberOfParticipants);
            ResultSet resultSet = numbersOfParticipants.executeQuery();
            ArrayList<Integer> numbers = new ArrayList<>();
            while (resultSet.next()){
                numbers.add(resultSet.getInt("number_of_participants"));
            }
            int maxNum = Collections.max(numbers);
            return (musicBand.getNumberOfParticipants() > maxNum) ? addObject(musicBand) : -2;
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return -1;
    }
    public int addIfMin(MusicBand musicBand){
        try{
            Connection connection = connect();
            PreparedStatement numbersOfParticipants = connection.prepareStatement(queryManager.selectNumberOfParticipants);
            ResultSet resultSet = numbersOfParticipants.executeQuery();
            ArrayList<Integer> numbers = new ArrayList<>();
            while (resultSet.next()){
                numbers.add(resultSet.getInt("number_of_participants"));
            }
            int minNum = Collections.min(numbers);
            return (musicBand.getNumberOfParticipants() < minNum) ? addObject(musicBand) : -2;
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return -1;
    }
    public HashSet<Integer> clear(String user_login){
        HashSet<Integer> ids = new HashSet<>();
        try{
            Connection connection = connect();
            PreparedStatement clear = connection.prepareStatement(queryManager.clearCollection);
            clear.setString(1, user_login);
            ResultSet resultSet = clear.executeQuery();
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return ids;
    }
    public HashSet<Integer> removeLower(String user_login, int numberOfParticipants){
        HashSet<Integer> ids = new HashSet<>();
        try{
            Connection connection = connect();
            PreparedStatement removeLower = connection.prepareStatement(queryManager.removeLower);
            removeLower.setString(1, user_login);
            removeLower.setInt(2, numberOfParticipants);
            ResultSet resultSet = removeLower.executeQuery();
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Ошибка при подключении/выполнении запроса");
        }
        return ids;
    }
}
