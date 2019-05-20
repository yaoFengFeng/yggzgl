package servlet;

import dao.UserDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/GetSelfServlet")
public class GetSelfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        UserDao userDao = new UserDao();
        List<Map<String, String>> res;
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        String str = " id = '"+id+"'";
        res = userDao.getUser(str);
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
