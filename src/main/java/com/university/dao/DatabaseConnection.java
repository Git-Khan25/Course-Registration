package com.university.dao;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/course_db?useSSL=false&serverTimezone=UTC";
        private static final String USER = "root";       // Update with your MySQL username
        private static final String PASSWORD = "password"; // Update with your MySQL password

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
    