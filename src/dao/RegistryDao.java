package dao;

import com.Observer;
import com.Receiver;
import servlet.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistryDao {
    private Connection conn = MySQLConnection.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void insert(String id){
        String sql = "insert into registry(user_id) values('"+id+"')";
        try {
            pstmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Observer> getObserver(){
        List<Observer> list = new ArrayList<Observer>();
        Receiver receiver;
        String sql = "select user_id from registry";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                receiver = new Receiver(rs.getString("user_id"));
                list.add(receiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int isUserBeObserver(String id){
        int row = 0;
        String sql = "select * from registry where user_id = '"+id+"'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next() && rs!=null){
                row = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  row;
    }

    public int doSql(String sql){
        int row =0;
        try {
            pstmt = conn.prepareStatement(sql);
            row = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
