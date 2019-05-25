package dao;

import entity.User;
import entity.Wage;
import servlet.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WageDao {
    private Connection conn = MySQLConnection.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //获取员工 生成工资单时用
    public List<User> getAllUsers() {
        String sql = "select id,username,service_time,title,department from users";
        List<User>  list = new ArrayList<>();
        User user ;
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setUsername(rs.getNString("username"));
                user.setTitlt(rs.getNString("title"));
                user.setServiceTime(rs.getInt("service_time"));
                user.setDepartment(rs.getNString("department"));
                user.setId(rs.getNString("id"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //按月份
    public List<Map<String,String>> getHistoryWages() {
        List<Map<String,String>>  list;
        String sql = "select * from all_wage";
        list = getWages(sql);
        return list;
    }

    public List<Map<String,String>> getNewWages() {
        List<Map<String,String>>  list;
        String sql = "select * from wage";
        list = getWages(sql);
        return list;
    }

    public int insertOrdeleteWage(String sql){
        int row = 0;
        System.out.println(sql);
        try {
            pstmt = conn.prepareStatement(sql);
            row  = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public int insertWages(List<Wage> wageList){
        int size = wageList.size();
        Wage wage;
        String sql;
        int row = 0;
        for(int i=0;i < size;i++){
            wage = wageList.get(i);
            sql = "insert into wage (date,department,user_id,name,title,basic_salary,bonus,basic_years_salary,count) values(?,?,?,?,?,?,?,?,?)";
            String pV1 = wage.getDate();
            String pV2 = wage.getDepartment();
            String pV3 = wage.getUserID();
            String pV4 = wage.getName();
            String pV5 = wage.getTitle();
            float pV6 = wage.getBasicSalary();
            float pV7 = wage.getBonus();
            float pV9 = wage.getCount();
            float pV8 = wage.getBasic_years_salary();
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,pV1);
                pstmt.setObject(2,pV2);
                pstmt.setObject(3,pV3);
                pstmt.setObject(4,pV4);
                pstmt.setObject(5,pV5);
                pstmt.setObject(6,pV6);
                pstmt.setObject(7,pV7);
                pstmt.setObject(8,pV8);
                pstmt.setObject(9,pV9);
                row  += pstmt.executeUpdate();
                conn.commit(); // 提交事务 没有这句话 不报错但就是不给你插入
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    public List<Map<String,String>> getWageByDepartment(String dep){
        List<Map<String,String>>  list ;
        String sql = "select * from wage WHERE department = '"+dep+"'";
        list = getWages(sql);
        return list;
    }

    public List<Map<String,String>> getWage(String str){
        List<Map<String,String>>  list ;
        String sql = "select * from wage WHERE " + str;
        list = getWages(sql);
        return list;
    }

    private List<Map<String,String>> getWages(String sql) {
        List<Map<String,String>>  list = new ArrayList<>();
        Map<String,String> map ;
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                map = new HashMap<String,String>();
                map.put("id",rs.getInt("user_id")+"");
                map.put("department",rs.getNString("department"));
                map.put("title",rs.getNString("title"));
                map.put("name",rs.getNString("name"));
                map.put("bonus",rs.getFloat("bonus")+"");
                map.put("basic_salary",rs.getFloat("basic_salary")+"");
                map.put("basic_years_salary",rs.getFloat("basic_years_salary")+"");
                map.put("date",rs.getString("date"));
                map.put("others",rs.getString("others"));
                map.put("count",rs.getFloat("count")+"");
                map.put("note",rs.getString("note"));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}