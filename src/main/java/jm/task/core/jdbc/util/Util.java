package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    public static Connection conect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "newpassword");


    }
}