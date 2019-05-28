package dao;

import servlet.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MsgDao {
    private Connection conn = MySQLConnection.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void insert(String id,String msg,String title){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);  //工资年月
        String sql = "insert into msg(user_id,date,msg,title) values('"+id+"','"+date+"','"+msg+"','"+title+"')";
        System.out.println(sql);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String,String>> getMsg(String id){
        String sql = "select * from msg where user_id = '"+id+"'";
        List<Map<String,String>> list = new ArrayList<>();
        try {
            pstmt= conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Map<String,String> map;
            while (rs.next()){
                map = new HashMap<>();
                map.put("id",rs.getString("id"));
                map.put("date",rs.getString("date"));
                map.put("title",rs.getString("title"));
                map.put("msg",rs.getString("msg"));
                map.put("status",rs.getString("status"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
