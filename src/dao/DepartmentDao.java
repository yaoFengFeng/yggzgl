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

    public List<String> getDepsName(){
        String sql = "select department from department";
        List<String> deps = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String dep = rs.getString("department");
                deps.add(dep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deps;
    }

    public int inserOrUpdateDep(String sql){
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            row = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  row;
    }

    public int deleteDep(String sql){
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            row = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  row;
    }

    //删除部门时 删除该部门所有员工
//    public void deleteUser(){
//
//    }
}
