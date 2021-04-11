package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		//글번호 받기
		String idx = request.getParameter("idx");

		ActionForward forward = null;
		
		try {
			PrintWriter out = response.getWriter();

			if (idx == null || idx.trim().equals("")) {
				out.print("<script>");
					out.print("alert('글번호 입력 오류')");
					out.print("location.href='board_list.jsp'");
				out.print("</script>");
			}

			BoardDao dao = new BoardDao();
			int result = dao.boardEdit(request);

			String msg = "";
			String url = "";
			
			if (result > 0) {
				msg = "edit success";
				url = "list.board";
			} else {
				msg = "edit fail";
				url = "edit.board?idx=" + idx;
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/redirect.jsp");

		} catch (IOException | NamingException e) {
			e.printStackTrace();

		}

		return forward;
	}

}
