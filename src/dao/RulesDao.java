package dao;

import entity.Rules;
import servlet.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesDao {
    private Connection conn = MySQLConnection.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //获取所有规则
    public List<Map<String,String>> getRules(){
        List<Map<String,String>> list = new ArrayList<>();
        String sql = "select * from rules";
        Map<String,String> map;
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()){
                map = new HashMap<>();
                map.put("departmentName",rs.getString("department"));
                map.put("title",rs.getString("title"));
                map.put("basicSalary",rs.getString("basic_salary"));
                map.put("bonus",rs.getString("bonus"));
                map.put("basic_years_salary",rs.getString("basic_years_salary"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertRules(List<Rules> rulesList){
        int size = rulesList.size();
        Rules rules;
        int row = 0;
        for(int i =0 ;i<size;i++){
            rules = rulesList.get(i);
            String sql = "insert into rules(department,title,basic_salary,bonus,basic_years_salary) values(?,?,?,?,?)";
            String pv1 = rules.getDepartment();
            String pv2 = rules.getTitle();
            float pv3 = rules.getBasicSalary();
            float pv4 = rules.getBonus();
            float pv5 = rules.getBasicYearsSalary();
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,pv1);
                pstmt.setObject(2,pv2);
                pstmt.setObject(3,pv3);
                pstmt.setObject(4,pv4);
                pstmt.setObject(5,pv5);
                row = pstmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    public int insertDelUpdateRuleBysql(String sql){
        int row = 0;
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
