package servlet;

import com.Massage;
import com.Observer;
import dao.RegistryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet( "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        RegistryDao registryDao = new RegistryDao();
        PrintWriter out = response.getWriter();
        int flag = Integer.parseInt(request.getParameter("flag"));
        int row = 0;
        if (flag == 2){
            HttpSession session = request.getSession();
            String id = session.getAttribute("id").toString();
            row = registryDao.isUserBeObserver(id);
            out.print(row);
        }else if(flag == 1){
            List<Observer> list = registryDao.getObserver();
            Massage massage = new Massage();
            Observer receiver;
            for (int i=0;i<list.size();i++){
                receiver = list.get(i);
                massage.addObserver(receiver);
            }
            String msg = request.getParameter("msg");
            String title = request.getParameter("title");
            massage.setInfomation(msg,title);
        }else if (flag == 3){
            row =0;
            HttpSession session = request.getSession();
            String id = session.getAttribute("id").toString();
            String sql = "delete from registry where user_id = '" + id +"'";
            row = registryDao.doSql(sql);
            out.print(row);
        }else{
            row =0;
            HttpSession session = request.getSession();
            String id = session.getAttribute("id").toString();
            String sql = "insert into registry (user_id) values( '" + id +"')";
            System.out.println(sql);
            row = registryDao.doSql(sql);
            out.print(row);
        }
        out.flush();
        out.close();
    }
}
