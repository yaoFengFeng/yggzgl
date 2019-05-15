package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.login(request,response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = new User();
        UserDao userDao = new UserDao();
        request.setCharacterEncoding("UTF-8"); // 防止乱码
        user.setId(request.getParameter("id"));
        user.setPsd(request.getParameter("psd"));
        int res = userDao.login(user);
        if (res == 1){
            HttpSession session = request.getSession();
            session.setAttribute("id", user.getId());
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(res);//返回登录信息
        out.flush();
        out.close();
    }
}
