package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//글번호, 비밀번호 받기
		String idx = request.getParameter("idx");
		String pwd = request.getParameter("pwd");
		
		ActionForward forward = null;
		
		try {
			BoardDao dao = new BoardDao();
			int result = dao.deleteOk(idx, pwd);
			
			String msg="";
			String url="";
			
			if(result > 0){
				msg="delete success";
				url="list.board";
			}else{
				msg="delete fail";
				url="list.board";
			}
			
			request.setAttribute("board_msg",msg);
			request.setAttribute("board_url",url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/redirect.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
		
		return forward;
		
	}

}
