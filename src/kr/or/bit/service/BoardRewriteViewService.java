package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardRewriteViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String subject = request.getParameter("subject"); // 답글의 제목으로 사용
		
		ActionForward forward = null;
		
		try {
			
			if(idx == null || subject == null || idx.trim().equals("") || subject.trim().equals("")){
				response.sendRedirect("list.board");
			}
			if(cpage == null || pagesize == null){
				cpage ="1";
				pagesize = "5";
			}
			
			request.setAttribute("idx", idx);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("subject", subject);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/board_rewrite.jsp");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
