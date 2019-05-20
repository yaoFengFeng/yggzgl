package servlet;

import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-Type","text/html;charset=utf-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null){
            request.getRequestDispatcher("/view/error403.html").forward(request,response);
        }else{
            request.getRequestDispatcher("/view/home.html").forward(request,response);
        }
    }
}
