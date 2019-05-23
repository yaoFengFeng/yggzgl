package dao;

import servlet.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDao {
    private Connection conn = MySQLConnection.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public List<Map<String,String>> getDepartments(){
        List<Map<String,String>> list = new ArrayList<> ();
        String sql = "select * from department";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Map<String,String> map;
            while (rs.next()){
                map = new HashMap<>();
                map.put("id",rs.getString("id"));
                map.put("department",rs.getString("department"));
                map.put("num",rs.getString("num"));
                map.put("phone",rs.getString("phone"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
