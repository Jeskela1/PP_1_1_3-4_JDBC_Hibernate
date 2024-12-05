package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.conect;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connect = conect()) {
            Statement statement = connect.createStatement();
            statement.execute("CREATE TABLE users(" +
                    "userid SERIAL PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age INT" +
                    ");"
            );
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connect = conect()) {
            Statement statement = connect.createStatement();
            statement.execute("DROP TABLE IF EXISTS users;");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = conect()) {
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO users (name, lastname, age)" +
                    "VALUES (?,?,?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3,  age);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connect = conect()) {
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM users WHERE userid = ?;");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try (Connection connect = conect()) {
            Statement statement = connect.createStatement();
            ResultSet  resultSet = statement.executeQuery("SELECT * FROM users;");
            List<User> tempUsers = new ArrayList<>();

            while(resultSet.next()){
                User temtUser = new User();
                temtUser.setId(resultSet.getLong("userid"));
                temtUser.setName(resultSet.getString("name"));
                temtUser.setLastName(resultSet.getString("lastname"));
                temtUser.setAge(resultSet.getByte("age"));
                tempUsers.add(temtUser);
            }

            return tempUsers;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void cleanUsersTable() {
        try (Connection connect = conect()) {
            Statement statement = connect.createStatement();
            statement.execute("DELETE FROM users;");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
