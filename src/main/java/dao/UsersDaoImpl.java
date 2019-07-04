package dao;

import database.DBHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UsersDaoImpl implements UsersDao {
    private DBHandler dbHandler;
    private final String SEARCH_USER_IN_DB = "SELECT* FROM users WHERE login=? AND password=?";
    private final String ADD_USER = "INSERT INTO users(login, password) VALUES(?, ?)";

    public UsersDaoImpl() {
        dbHandler = new DBHandler();
    }

    @Override
    public void addUser(String login, String password) throws SQLException {
        PreparedStatement preparedStat = dbHandler.getConnection().prepareStatement(ADD_USER);
        preparedStat.setString(1, login);
        preparedStat.setString(2, password);
        preparedStat.execute();
        preparedStat.close();
    }

    @Override
    public boolean isUserPresent(String login, String password) throws SQLException {
        boolean isUserPresent = false;
        PreparedStatement preparedStat = dbHandler.getConnection().prepareStatement(SEARCH_USER_IN_DB);
        preparedStat.setString(1, login);
        preparedStat.setString(2, password);
        ResultSet result = preparedStat.executeQuery();
        if (Objects.nonNull(result)) {
            isUserPresent = true;
        }
        result.close();
        preparedStat.close();
        return isUserPresent;
    }
}
