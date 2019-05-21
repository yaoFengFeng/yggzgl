package servlet;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    private volatile static Connection conn = null;
    private MySQLConnection(){}
    public static Connection getConnection(){
        String driver = null;
        String url = null;
        String user = null;
        String psd = null;
//        Properties pro = new Properties();   //创建一个Propertise实例，用于从DBConnection中获取数据库连接参数
        try {
//            pro.load(MySQLConnection.getConnection().getClass().getClassLoader().getResourceAsStream("DBConfig.properties")); //加载DBConfig文件。
            driver="com.mysql.cj.jdbc.Driver";
            url="jdbc:mysql://localhost:3306/yggzgl?serverTimezone=UTC&characterEncoding=UTF-8";
            user="root";
            psd="123456";
//            driver = pro.getProperty("driver");
//            url = pro.getProperty("url");
//            user = pro.getProperty("user");
//            psd = pro.getProperty("psd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);   //加载jdbc驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //双重检验锁  单例模式
            if (conn == null){
                synchronized (MySQLConnection.class){
                    if (conn == null){
                        conn = DriverManager.getConnection(url,user,psd); //获取数据库连接
                        conn.setAutoCommit(false);
                    }
                }
            }
            return conn;       //返回一个数据库连接。
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
