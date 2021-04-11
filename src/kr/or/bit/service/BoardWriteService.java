package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String subject = request.getParameter("subject");
    	String writer = request.getParameter("writer");
    	String email = request.getParameter("email");
    	String homepage = request.getParameter("homepage");
    	String content = request.getParameter("content");
    	String pwd = request.getParameter("pwd");
    	String filename = request.getParameter("filename");
		
    	Board board = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
    	
    	ActionForward forward = null;
    	
		try {
			BoardDao dao = new BoardDao();
	    	int result = dao.writeok(board);

	    	String msg="";
	        String url="";
	        if(result > 0){
	        	msg ="insert success";
	        	url ="list.board";
	        }else{
	        	msg="insert fail";
	        	url="write.board";
	        }
	        
	        request.setAttribute("board_msg", msg);
	        request.setAttribute("board_url", url);
	        
	        forward = new ActionForward();
	        forward.setRedirect(false);
	        forward.setPath("/WEB-INF/board/redirect.jsp");
	        
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
		
		return forward;
	}

}
