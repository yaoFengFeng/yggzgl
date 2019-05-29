package dao;


import entity.Department;
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

public class UserDao {
        private Connection conn = MySQLConnection.getConnection();
        private PreparedStatement pstmt = null;
        private ResultSet rs = null;
        public  String username;
        public int login(User user,String sql){
            int flag = -1;      //-1 登录失败 1 登录成功 0 数据操作有误（catch中返回）
//            String sql = "select * from users where id = ? and psd= ?";
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
            List<Map<String,String>>  list;
            String sql = "select * from users";
            list = getUsers(sql);
            return list;
        }

        public int insertOrdeleteUser(String sql){
            int row = 0;
            try {
                pstmt = conn.prepareStatement(sql);
                row  = pstmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return row;
        }

        public int insertUsers(List<User> userList){
            int size = userList.size();
            List<Department> departments = new ArrayList<>();
            Department department;
            User user;
            String sql;
            int row = 0;
            for(int i=0;i < size;i++){
                user = userList.get(i);
                sql = "insert into users(id,username,sex,service_time,phone,department,title,address,status,note) values(?,?,?,?,?,?,?,?,?,?)";
                String pV1 = user.getId();
                String pV2 = user.getUsername();
                String pV3 = user.getSex();
                int pV4 = user.getServiceTime();
                String pV5 = user.getPhone();
                String pV6 = user.getDepartment();
                String pV7 = user.getTitle();
                String pV8 = user.getAddress();
                String pV9 = user.getStatus();
                String pV10 = user.getNote();
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
                   pstmt.setObject(10,pV10);
                   row  += pstmt.executeUpdate();
                   conn.commit(); // 提交事务 没有这句话 不报错但就是不给你插入
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //需要取出插入员工中 相应部门当前插入员工的数量  以便更新部门表人员数量
                if (departments.size()!=0){  //判断list是否为空 是空的话直接add depment
                    boolean hasDep = false;
                    for (int j = 0;j<departments.size();j++){  //不为空 需要遍历list将 当前要插入到员工信息表中的dep 员工数量+1
                        department = departments.get(j);
                        if (department.getDepartment().equals(pV6) ){
                            int count = department.getNum();
                            count++;
                            department.setNum(count);
                            hasDep = true;
                            break;
                        }
                    }
                    if (!hasDep){
                        department = new Department();
                        department.setDepartment(pV6);
                        department.setNum(1);
                        departments.add(department);
                    }
                }else{
                    department = new Department();
                    department.setDepartment(pV6);
                    department.setNum(1);
                    departments.add(department);
                }
            }
            for (int i =0;i<departments.size();i++){
                department = departments.get(i);
                int num = department.getNum();
                String depName = department.getDepartment();
                sql = "update department set num = num + "+num +" where department = '"+depName+"'";
                try {
                    pstmt=conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return row;
        }

        public List<Map<String,String>> getUsersByDepartment(String dep){
            List<Map<String,String>>  list ;
            String sql = "select * from users WHERE department = '"+dep+"'";
            list = getUsers(sql);
            return list;
        }

        public List<Map<String,String>> getUser(String str){
            List<Map<String,String>>  list ;
            String sql = "select * from users WHERE " + str;
            list = getUsers(sql);
            return list;
        }


        private List<Map<String,String>> getUsers(String sql) {
        List<Map<String,String>>  list = new ArrayList<>();
        Map<String,String> map ;
        User user = new User();
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
                user.setDescirbe(rs.getNString("describes"));
                user.setEmail(rs.getNString("email"));
                user.setTitles(rs.getNString("titles"));
                map.put("username",user.getUsername());
                map.put("id",user.getId());
                map.put("department",user.getDepartment());
                map.put("title",user.getTitle());
                map.put("service_time",user.getServiceTime() + "年");
                map.put("sex",user.getSex());
//                    map.put("psd",user.getPsd());
                map.put("address",user.getAddress());
                map.put("phone",user.getPhone());
//                    map.put("birthday",user.getBirthday().toString());
                map.put("status",user.getStatus());
                map.put("note",user.getNote());
                map.put("describes",user.getDescirbe());
                map.put("email",user.getEmail());
                map.put("titles",user.getTitles());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
