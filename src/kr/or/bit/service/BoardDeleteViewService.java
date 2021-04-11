package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardDeleteViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		
		String idx = request.getParameter("idx");//댓글의 원본 게시글 번호
		ActionForward forward = null;
		
		try {
			PrintWriter out = response.getWriter();
			
			if(idx == null || idx.trim().equals("")){
				out.print("<script>");
					out.print("alert('글번호가 넘어오지 않았습니다');");
					out.print("history.back();");
				out.print("</script>");
			}
			
			request.setAttribute("idx", idx);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/board_delete.jsp");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
