package kr.or.bit.ajax;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BoardDao;

@WebServlet("/ReplyAdd")
public class Reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Reply() {
        super();
    }
	
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 받기
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		String idx_fk = request.getParameter("idx");
		String userid = "empty";
		
		System.out.println(writer +"/" +content +"/" +pwd +"/" +idx_fk);
		
		try {
			BoardDao dao = new BoardDao();
			int result = dao.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
