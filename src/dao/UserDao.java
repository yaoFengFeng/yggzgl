package dao;

import com.mysql.cj.xdevapi.JsonArray;
import entity.User;
import servlet.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao extends MySQLConnection {
        private Connection conn = getConnection();
        private PreparedStatement pstmt = null;
        private ResultSet rs = null;
        public  String username;
        public int login(User user){
            int flag = -1;      //-1 登录失败 1 登录成功 0 数据操作有误（catch中返回）
            String sql = "select * from user where id = ? and psd= ?";
            String pV1 = user.getId();
            String pV2 = user.getPsd();
            try {
                this.pstmt = this.conn.prepareStatement(sql);
                pstmt.setObject(1,pV1);
                pstmt.setObject(2,pV2);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    username =rs.getString("username");
                    flag = 1;
                    return flag;
                }else return flag;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = 0;
                return flag;
            }
        }

        public List<Map<String,String>> getAllUser() {
            List<Map<String,String>>  list = new ArrayList<>();
            Map<String,String> map ;
            User user = new User();
            String sql = "select * from user";
            try {
                this.pstmt = this.conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    map = new HashMap<String,String>();
                    user.setUsername(rs.getNString("username"));
                    user.setPsd(rs.getNString("psd"));
                    user.setAddress(rs.getNString("address"));
                    user.setPhone(rs.getNString("phone"));
                    user.setId(rs.getNString("id"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setDepartment(rs.getNString("department"));
                    user.setNote(rs.getNString("note"));
                    user.setServiceTime(rs.getInt("service_time"));
                    user.setSex(rs.getNString("sex"));
                    user.setTitlt(rs.getNString("title"));
                    user.setStatus(rs.getNString("status"));
                    map.put("username",user.getUsername());
                    map.put("id",user.getId());
                    map.put("department",user.getDepartment());
                    map.put("title",user.getTitlt());
                    map.put("service_time",user.getServiceTime() + "年");
                    map.put("sex",user.getSex());
//                    map.put("psd",user.getPsd());
                    map.put("address",user.getAddress());
                    map.put("phone",user.getPhone());
//                    map.put("birthday",user.getBirthday().toString());
                    map.put("status",user.getStatus());
                    map.put("note",user.getNote());
                    System.out.println(map);
                    list.add(map);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }


}
