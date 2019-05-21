package servlet;

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
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserDao userDao;
        int row = -1;
        int flag = Integer.parseInt(request.getParameter("flag"));
        switch (flag){
            case 0:
                //json字符串转成 JSONArray
                JSONArray arr = JSONArray.fromObject(request.getParameter("userlist"));
                if (arr.size() > 0) {
                    List<User> userList = new ArrayList<>();
                    User user;
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject job = arr.getJSONObject(i);
                        user = new User();
                        user.setUsername(job.getString("username"));
                        user.setAddress(job.getString("address"));
                        user.setPhone(job.getString("phone"));
                        user.setId(job.getString("id"));
                        user.setDepartment(job.getString("department"));
                        user.setNote(job.getString("note"));
                        user.setServiceTime(job.getInt("service_time"));
                        user.setSex(job.getString("sex"));
                        user.setTitlt(job.getString("title"));
                        user.setStatus(job.getString("status"));
                        userList.add(user);
                    }
                    userDao = new UserDao();
                    row = userDao.insertUsers(userList);
                }
                break;
            case 1:
                userDao = new UserDao();
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
