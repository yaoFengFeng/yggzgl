package servlet;

import dao.RulesDao;
import dao.UserDao;
import dao.WageDao;
import entity.Rules;
import entity.User;
import entity.Wage;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet ("/WageServlet")
public class WageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int flag = Integer.parseInt(request.getParameter("flag"));
        WageDao wageDao = new WageDao();
        PrintWriter out = response.getWriter();
        List<Map<String,String>> list;
        JSONArray jsonObj;
        switch (flag){
            case 1:
                RulesDao rulesDao= new RulesDao();
                List<Wage> listWages = new ArrayList<>();
                List<Map<String,String>> listRules = rulesDao.getRules();
                List<User> listUsers = wageDao.getAllUsers();
                int size = listUsers.size();
                int ruleSize = listRules.size();
                int row;
                Wage wage;
                Map<String,String> map;
                String dep = "departmentName";
                String title = "title";
                String bonus = "bonus";
                String basicSalary = "basicSalary";
                String basic_years_salary = "basic_years_salary";
                User user;
                boolean hasRules = false;
                for (int i =0;i<size;i++){
                    user = listUsers.get(i);
                    wage = new Wage();
                    hasRules = false;
                    //如果生成的工资单中存在 员工信息中的部门职位
                    // 在rules表中没有相应的部门职位情况即 hasRules为false 不允许插入（停止生成工资单）
                    for (int j =0;j<ruleSize;j++){
                        map = listRules.get(j);
                        if (map.get(dep).equals(user.getDepartment()) && map.get(title).equals(user.getTitle())){
                            wage.setBonus(Float.parseFloat(map.get(bonus)));
                            wage.setBasicSalary(Float.parseFloat(map.get(basicSalary)));
                            wage.setBasic_years_salary(Float.parseFloat(map.get(basic_years_salary))*user.getServiceTime());
                            wage.setCount(wage.getBasicSalary()+wage.getBonus()+wage.getBasic_years_salary());
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String date = sdf.format(d);
                            wage.setDate(date);
                            wage.setName(user.getUsername());
                            wage.setDepartment(user.getDepartment());
                            wage.setTitle(user.getTitle());
                            wage.setUserID(user.getId());
                            listWages.add(wage);
                            hasRules = true; //工资发放标准中存在相应部门和职位
                            break;
                        }
                    }
                    if (!hasRules){
                        return;
                    }
                }
                if (hasRules){
                    row = wageDao.insertWages(listWages);
                }else{
                    row = -1;
                }
                out.print(row);
                break;
            case 2:
                list = wageDao.getNewWages();
                jsonObj = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jsonObj.add(list.get(i));
                }
                out.print(jsonObj);
                break;
            case 3:
                list = wageDao.getHistoryWages();
                jsonObj = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jsonObj.add(list.get(i));
                }
                out.print(jsonObj);
                break;
            case 4:
                list = wageDao.getAllWages();
                jsonObj = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jsonObj.add(list.get(i));
                }
                out.print(jsonObj);
                break;
        }
        out.flush();
        out.close();
    }
}
