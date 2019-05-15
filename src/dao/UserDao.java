package dao;

import com.mysql.cj.xdevapi.JsonArray;
import entity.User;
import servlet.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
