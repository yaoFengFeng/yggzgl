package servlet;

import com.Massage;
import com.Observer;
import com.Receiver;
import dao.MsgDao;
import dao.RegistryDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        List<Map<String,String>> list;
        JSONArray jsonObj;
        MsgDao msgDao = new MsgDao();
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        list = msgDao.getMsg(id);
        jsonObj = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonObj.add(list.get(i));
        }
        out.print(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("flag") != null && Integer.parseInt(request.getParameter("flag")) == 2){
            int row =0;
            String id = request.getParameter("id");
            String  sql = "update msg set status = 1 where id = '"+id+"'";
            System.out.println(sql);
            MsgDao msgDao = new MsgDao();
            row =  msgDao.doSql(sql);
            out.print(row);
            out.flush();
            out.close();
        }
    }
}
