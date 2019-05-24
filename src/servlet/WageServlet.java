package servlet;

import dao.RulesDao;
import dao.UserDao;
import dao.WageDao;
import entity.Rules;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet ("/WageServlet")
public class WageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        WageDao wageDao = new WageDao();
        RulesDao rulesDao= new RulesDao();
        List<Map<String,String>> listWages = new ArrayList<>();
        List<Map<String,String>> listRules = rulesDao.getRules();
        List<User> listUsers = wageDao.getAllUsers();
        int size = listUsers.size();
        int ruleSize = listRules.size();
        Rules rules = new Rules();
        Map<String,String> map;
        String dep = "department";
        String title = "title";
        User user;
        for (int i =0;i<size;i++){
            user = listUsers.get(i);
            map = new HashMap<>();
            for (int j =0;j<ruleSize;j++){
                map = listRules.get(j);
                if (map.get(dep) == user.getDepartment() && map.get(title) == user.getTitle()){

                }
            }
        }
    }
}
