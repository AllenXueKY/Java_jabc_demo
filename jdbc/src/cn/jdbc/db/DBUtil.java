package cn.jdbc.db;

import java.sql.*;

public class DBUtil {
    private static Connection conn = null;

    static {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.获取数据库的连接(MySQL8.0以上的写法)
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest?useSSL=false&serverTimezone=UTC","root","991104");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
    public static Connection getConnection(){

        return conn;
    }

}
