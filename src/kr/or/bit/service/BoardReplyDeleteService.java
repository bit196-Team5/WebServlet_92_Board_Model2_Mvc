package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardReplyDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
		String no = request.getParameter("no");//댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");//댓글의 암호
		
		System.out.println(idx_fk + "/" + no + "/" + pwd + "/");
		
		ActionForward forward = null;
		
		try {
			PrintWriter out = response.getWriter();
			
			if(idx_fk == null || no == null || pwd == null || no.trim().equals("")){	
				out.print("<script>");
					out.print("alert('글번호가 넘어오지 않았습니다');");
					out.print("history.back();");
				out.print("</script>");
			}
			
			BoardDao dao = new BoardDao();
			int result = dao.replyDelete(no, pwd);
			
			// 처리하는 코드
			String msg = "";
			String url = "";

			if (result > 0) {
				msg = "댓글 삭제 성공";
				url = "content.board?idx=" + idx_fk;
			} else {
				msg = "댓글 삭제 실패";
				url = "content.board?idx=" + idx_fk;
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
