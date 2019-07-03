package dao;

import java.sql.SQLException;

public interface UsersDao {
    void addUser(String login, String password) throws SQLException;

    boolean isUserPresent(String login, String password) throws SQLException;
}
