package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO users (name, lastname, age)  VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем – %s добавлен в базу данных %n", name);
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }
}

