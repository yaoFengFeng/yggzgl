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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        RulesDao rulesDao = new RulesDao();
        JSONArray arr = JSONArray.fromObject(request.getParameter("ruledata"));
        List<Rules> rulesList = new ArrayList<>();
        Rules rules;
        for (int i = 0; i < arr.size(); i++ ){
            JSONObject job = arr.getJSONObject(i);
            rules = new Rules();
            rules.setDepartment(job.getString("departmentName"));
            rules.setTitle(job.getString("title"));
            rules.setBasicSalary((float) job.getDouble("basicSalary"));
            rules.setBonus((float) job.getDouble("bonus"));
            rules.setBasicYearsSalary((float) job.getDouble("basic_years_salary"));
            rulesList.add(rules);
        }
        int row = rulesDao.insertRules(rulesList);
        out.print(row);
        out.flush();
        out.close();

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
