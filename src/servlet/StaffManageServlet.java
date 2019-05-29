package servlet;

import dao.DepartmentDao;
import dao.UserDao;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/StaffManageServlet")
public class StaffManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DepartmentDao departmentDao;
        UserDao userDao;
        int row = -1;
        int flag = Integer.parseInt(request.getParameter("flag"));
        switch (flag){
            case 0:
                //json字符串转成 JSONArray
                JSONArray arr = JSONArray.fromObject(request.getParameter("userlist"));
                departmentDao = new DepartmentDao();
                List<String> deps = departmentDao.getDepsName();
                boolean hasDep = false; //用来判断插入的员工部门  在部门表中是否存在 若果不存在不能导入所有员工信息
                if (arr.size() > 0) {
                    List<User> userList = new ArrayList<>();
                    User user;
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject job = arr.getJSONObject(i);
                        hasDep = false;
                        for (String dep:deps) {
                            if (dep.equals(job.getString("department"))){
                                hasDep = true;
                                user = new User();
                                user.setUsername(job.getString("username"));
                                user.setAddress(job.getString("address"));
                                user.setPhone(job.getString("phone"));
                                user.setId(job.getString("id"));
                                user.setDepartment(job.getString("department"));
                                user.setNote(job.getString("note"));
                                int service_time = Integer.parseInt(job.getString("service_time").replace("年",""));
                                user.setServiceTime(service_time);
                                user.setSex(job.getString("sex"));
                                user.setTitlt(job.getString("title"));
                                user.setStatus(job.getString("status"));
                                userList.add(user);
                                break;
                            }
                        }
                        if (!hasDep){
                            out.print(-1);
                            out.flush();
                            out.close();
                            return;
                        }
                    }
                    userDao = new UserDao();
                    row = userDao.insertUsers(userList);
                }
                break;
            case 1:
                userDao = new UserDao();
                int type = Integer.parseInt(request.getParameter("type"));
                if (type != 2){ //不是更新员工 是删除或者添加员工的话需要去跟新相应部门的人数
                    String dep = request.getParameter("dep");
                    departmentDao = new DepartmentDao();
                    departmentDao.updateNum(type,dep);
                }
                row = userDao.insertOrdeleteUser(request.getParameter("str"));
                break;
        }
        out.print(row);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UserDao userDao = new UserDao();
        int flag = Integer.parseInt(request.getParameter("flag"));
        List<Map<String, String>> res;
        if (flag == 1) {
             res = userDao.getAllUser();
        }else if (flag == 2){
            String dep = request.getParameter("dep");
             res = userDao.getUsersByDepartment(dep);
        }else{
            String str = request.getParameter("str");
            res = userDao.getUser(str);
        }
        PrintWriter out = response.getWriter(); //这里有坑 上面的编码设置一定要在这句话前面 不然中文会乱码
        JSONArray jsonObj = new JSONArray();
        for (int i = 0; i < res.size(); i++) {
            jsonObj.add(res.get(i));
        }
        out.print(jsonObj);
        out.flush();
        out.close();
    }
}
