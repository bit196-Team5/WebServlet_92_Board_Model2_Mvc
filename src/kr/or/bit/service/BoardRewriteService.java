package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String idx = request.getParameter("idx");
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String filename = request.getParameter("filename");
		String pwd = request.getParameter("pwd"); 
		
		ActionForward forward = null;
		
		try {
			Board board = new Board();
			
			board.setIdx(Integer.parseInt(idx));
			board.setWriter(writer);
			board.setEmail(email);
			board.setHomepage(homepage);
			board.setPwd(pwd);
			board.setSubject(subject);
			board.setContent(content);
			board.setFilename(filename);
			
			
			//게시물 상세조회  > 답글 쓰기(rewriteok)
			BoardDao dao = new BoardDao();
			int result = dao.reWriteOk(board);
			
			String cpage = request.getParameter("cp"); //current page
			String pagesize = request.getParameter("ps"); //pagesize
			
			String msg = "";
		    String url = "";
		    
		    if(result > 0){
		    	msg = "rewrite insert success";
		    	url = "list.board";
		    }else{
		    	msg = "rewrite insert fail";
		    	url = "content.board?idx=" +board.getIdx();
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    
		    forward = new ActionForward();
		    forward.setRedirect(false);
		    forward.setPath("/WEB-INF/board/redirect.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
