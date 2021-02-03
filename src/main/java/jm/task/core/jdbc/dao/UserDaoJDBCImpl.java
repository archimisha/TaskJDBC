package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util = new Util();
    private PreparedStatement preStatement;
    private Statement statement;
    private ResultSet result;
    private Connection con = util.getCon();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String myTableName = "CREATE TABLE Users ("
                + "id INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(45),"
                + "lastName VARCHAR(45),"
                + "age INT(64))";
        try {
            statement = con.createStatement();
            statement.executeUpdate(myTableName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String deleteTable = "DROP TABLE Users";
        try {
            statement = con.createStatement();
            statement.executeUpdate(deleteTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUser = "INSERT INTO Users (name, lastName, age) VALUES(?, ?, ?)";
        try {
            preStatement = con.prepareStatement(insertUser);
            preStatement.setString(1, name);
            preStatement.setString(2, lastName);
            preStatement.setByte(3, age);

            preStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM Users WHERE id=?";
        try {
            preStatement = con.prepareStatement(removeUser);
            preStatement.setLong(1, id);

            preStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM Users";
        List<User> list = new ArrayList<User>();
        try {
            statement = con.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                User user = new User();
                user.setId((long) (result.getInt("id")));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge((byte) result.getInt("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  list;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM Users";
        try {
            statement = con.createStatement();
            statement.executeUpdate(clean);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
