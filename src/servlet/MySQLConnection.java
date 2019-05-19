package servlet;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    public Connection getConnection(){
        String driver = null;
        String url = null;
        String user = null;
        String psd = null;
        Connection conn = null;
        Properties pro = new Properties();   //创建一个Propertise实例，用于从DBConnection中获取数据库连接参数
        try {
            pro.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties")); //加载DBConfig文件。
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            psd = pro.getProperty("psd");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);   //加载jdbc驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url,user,psd); //获取数据库连接
            conn.setAutoCommit(false);
            return conn;       //返回一个数据库连接。
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
