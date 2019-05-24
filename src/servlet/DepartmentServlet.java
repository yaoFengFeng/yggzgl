package servlet;

import dao.DepartmentDao;
import dao.RulesDao;
import entity.Rules;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        DepartmentDao departmentDao = new DepartmentDao();
        List<Map<String,String>> list = departmentDao.getDepartments();
        PrintWriter out = response.getWriter();
        JSONArray jsonObj = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonObj.add(list.get(i));
        }
        out.print(jsonObj);
        out.flush();
        out.close();
    }
}
