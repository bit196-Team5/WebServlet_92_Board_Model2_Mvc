package kr.or.bit.service;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class BoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//글번호 받기
		String idx= request.getParameter("idx"); 
		
		ActionForward forward = null;
		
		try {
			
			//글 번호를 가지고 오지 않았을 경우 예외처리
			if(idx == null || idx.trim().equals("")){
				response.sendRedirect("list.board");
			}
			
			idx = idx.trim();
			//http://192.168.0.12:8090/WebServlet_5_Board_Model1_Sample/board/board_content.jsp?idx=19&cp=1&ps=5
			//board_content.jsp?idx=19&cp=1&ps=5  //다시 목록으로 갔을때  ... cp , ps 가지고 ...
			//why: 목록으로 이동시 현재 page 유지하고 싶어요
			String cpage = request.getParameter("cp"); //current page
			String pagesize = request.getParameter("ps"); //pagesize
			
			//List 페이지 처음 호출
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			
			BoardDao dao = new BoardDao();
			
			boolean isread = dao.getReadNum(idx);
			
			if(isread) System.out.println("조회증가 : " + isread);
			
			//데이터 조회 (1건 (row))
			Board board = dao.getContent(Integer.parseInt(idx));
			
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("idx", idx);
			request.setAttribute("board", board);
			
			//댓글 목록
			List<Reply> replylist = dao.replylist(idx); //참조하는 글번호
			
			request.setAttribute("replylist", replylist);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/board/board_content.jsp");
			
		} catch (NamingException | IOException e) {
			e.printStackTrace();
			
		}
		
		return forward;
	}

}
