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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.login(request,response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8"); // 防止乱码
        response.setContentType("text/html;charset=UTF-8");
        int flag = Integer.parseInt(request.getParameter("flag"));
        int res = 0;
        int back = 0;
        User user = new User();
        UserDao userDao = new UserDao();
        PrintWriter out = response.getWriter();
        user.setId(request.getParameter("id"));
        user.setPsd(request.getParameter("psd"));
        String sql;
        if (flag == 0){
            sql = "select * from admin where id = ? and password= ?";
            back = 1;
        }else if (flag == 1){
            sql = "select * from depadmin where id = ? and password= ?";
            back = 2;
        }else{
            sql = "select * from users where id = ? and psd= ?";
            back = 3;
        }
        res = userDao.login(user,sql) ;
        HttpSession session = request.getSession();
        if (res == 1){
            session.setAttribute("id", request.getParameter("id")); //将用户id通过session保存
            session.setAttribute("username", userDao.username); //将用户名通过session保存
            System.out.println(userDao.username);
            session.setAttribute("type", back); //将用户类型通过session保存
            out.print(back);//返回登录信息 1管理员 2部门管理员 3员工
        }else {
            out.print(res);
        }
        out.flush();
        out.close();
    }
}
