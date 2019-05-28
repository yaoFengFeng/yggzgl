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
        String str = request.getParameter("str");
        DepartmentDao departmentDao = new DepartmentDao();
        int row = departmentDao.inserOrUpdateDep(str);
        PrintWriter out = response.getWriter();
        out.print(row);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        int flag = Integer.parseInt(request.getParameter("flag"));
        if (flag == 1){
            DepartmentDao departmentDao = new DepartmentDao();
            List<Map<String,String>> list = departmentDao.getDepartments();
            JSONArray jsonObj = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jsonObj.add(list.get(i));
            }
            out.print(jsonObj);
        }else if (flag == 2){
            String str = request.getParameter("str");
            DepartmentDao departmentDao = new DepartmentDao();
            int row = departmentDao.deleteDep(str);
            out.print(row);
        }else if (flag == 3){
            DepartmentDao departmentDao = new DepartmentDao();
            List<String> list = departmentDao.getDepsName();
            JSONArray jsonObj = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jsonObj.add(list.get(i));
            }
            out.print(jsonObj);
        }else if (flag == 4){
            DepartmentDao departmentDao = new DepartmentDao();
            List<Map<String,String>> list = departmentDao.getDepartmentNums();
            JSONArray jsonObj = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jsonObj.add(list.get(i));
            }
            out.print(jsonObj);
        }

        out.flush();
        out.close();
    }
}
