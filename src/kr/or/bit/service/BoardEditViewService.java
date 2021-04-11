package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardEditViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html; charset=UTF-8");
		
		//글번호 받기
		String idx = request.getParameter("idx");
		ActionForward forward = null;
		
		try {
			if(idx == null || idx.trim().equals("")){
				response.sendRedirect("list.board"); //cpage=1 , ps=5
			}
			
			BoardDao dao = new BoardDao();
			Board board = dao.getEditContent(idx);
			
			PrintWriter out = response.getWriter();
			
			if(board == null){
				out.print("데이터 오류");
				out.print("<hr><a href='list.board'>목록가기</a>");
			}
			
			request.setAttribute("idx", idx);
			request.setAttribute("board", board);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/board_edit.jsp");
			
		} catch (NamingException | IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
