package servlet;
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        System.out.println("alhff");
        if (session.getAttribute("id") == null || session.getAttribute("id") == "" ){
            request.getRequestDispatcher("/view/error403.html").forward(request,response);
            return;
        }else{
            System.out.println(session.getAttribute("id"));
            System.out.println("怎么回事");
            request.getRequestDispatcher("/view/home.html").forward(request,response);
        }
    }
}
