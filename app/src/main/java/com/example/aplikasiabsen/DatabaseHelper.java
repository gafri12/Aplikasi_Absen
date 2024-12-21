package com.example.aplikasiabsen;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    public static Connection connect() {
        Connection Database_Connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String URL = "jdbc:mysql:localhost:3306/absensi_db";
            final String USER = "root";
            final String PASSWORD = "";

            Database_Connection = DriverManager.getConnection(
                    URL, USER,PASSWORD);
            if (Database_Connection!=null){
                System.out.println("Database is successfully connected.");
            }else{
                System.out.println("Database not connected");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Database_Connection;
    }
}
//public class DatabaseHelper {
//    private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql:localhost:3306/absensi_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
//
//
//    public static Connection connect() throws SQLException{
//        try{
//
//        }
//    }
////    public static void main(String[] args) throws SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//
//        try{
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(URL,USER,PASSWORD);
//            System.out.println("Database is successfully connected.");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


//    public static Connection connect() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }


