package utils;

public class QueryManager {
    String findUser = "SELECT * FROM users where login = ?; ";

    String getPassword = "SELECT hash from users where name = ? ;";
    String addUser = "INSERT INTO users(login, password, hash) VALUES (?, ?, ?)";

    String addMusicBand = """
            INSERT INTO musicBands(name, coordinate_x, coordinate_y, creation_date, number_of_participants, music_genre, bands, user_login) VALUES(?, ?, ?, ?, ?, ?, ?, ?) 
            RETURNING id;
            """;
    String addSpacialMusicBand = """
            INSERT INTO musicBands(id, name, coordinate_x, coordinate_y, creation_date, number_of_participants, music_genre, bands, user_login) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) 
            RETURNING id;
            """;

    String clearCollection = "delete from musicBands where (user_login = ?) returning id;";

    String deleteObject = "delete from musicBands where (user_login = ?) and (id = ?) returning id;";
    String selectNumberOfParticipants = "select number_of_participants from musicBands;";
    String removeLower = "delete from musicBands where (user_login = ?) and (number_of_participants < ?) returning id;";

    String updateObject = """
            update musicBands 
            set (name, coordinate_x, coordinate_y, number_of_participants, music_genre, bands) = (?, ?, ?, ?, ?, ?) where (user_login = ?) and (id = ?) returning id;
            """;

    String selectAllObjects = """
            select * from musicBands ;
            """;
    String selectObject = """
            select id, user_login from musicBands where (user_login = ?) and (id = ?);
            """;
    String selectById = """
            select user_login from musicBands where (id = ?);
            """;
    String deleteAll = "delete from musicBands;";
}
