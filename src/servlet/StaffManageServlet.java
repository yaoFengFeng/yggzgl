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
import java.util.List;
import java.util.Map;

@WebServlet("/StaffManageServlet")
public class StaffManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDao userDao = new UserDao();
        List<Map<String,String>> res = userDao.getAllUser();
        response.setContentType("text/html;charset=utf-8");
//        JSONObject jsonObj = new JSONObject();
        JSONArray jsonObj = new JSONArray();
        for (int i = 0; i < res.size(); i++) {
            jsonObj.add(res.get(i));
        }
        PrintWriter out = response.getWriter();
        out.print(jsonObj);
        out.flush();
        out.close();
//        response.sendRedirect("/view/manageStaff.html");
    }
}
