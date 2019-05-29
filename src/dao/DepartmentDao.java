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
    public List<Map<String,String>> getDepartmentNums() {
        List<Map<String, String>> list = new ArrayList<>();
        String sql = "select department,count(*) as num  from users group by department";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Map<String, String> map;
            while (rs.next()) {
                map = new HashMap<>();
                map.put("department", rs.getString("department"));
                map.put("num", rs.getString("num"));

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

    public void updateNum(int type,String depName){
        String sql2;
        if (type == 0){ //删除一个员工 更新num -1
             sql2 = "update department set num = num - 1 where department = '"+depName+"'";
        }else{
             sql2 = "update department set num = num + 1 where department = '"+depName+"'";
        }
        try {
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
