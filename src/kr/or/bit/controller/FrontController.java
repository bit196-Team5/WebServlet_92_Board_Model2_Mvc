package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.BoardContentService;
import kr.or.bit.service.BoardDeleteService;
import kr.or.bit.service.BoardDeleteViewService;
import kr.or.bit.service.BoardEditService;
import kr.or.bit.service.BoardEditViewService;
import kr.or.bit.service.BoardListService;
import kr.or.bit.service.BoardReplyDeleteService;
import kr.or.bit.service.BoardReplyService;
import kr.or.bit.service.BoardRewriteService;
import kr.or.bit.service.BoardRewriteViewService;
import kr.or.bit.service.BoardWriteService;



@WebServlet("*.board") //*.html, *.jsp, *. 사용금지
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	//글쓰기
    	if(url_command.equals("/write.board")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/board/board_write.jsp");
    		
    	}else if(url_command.equals("/writeok.board")) {
    		action = new BoardWriteService();
    		forward = action.execute(request, response);
    		System.out.println("BoardWriteService 실행");
    	
    	//글수정
    	}else if(url_command.equals("/edit.board")) {
    		action = new BoardEditViewService();
    		forward = action.execute(request, response);
    		
    	}else if(url_command.equals("/editok.board")) {
    		action = new BoardEditService();
    		forward = action.execute(request, response);
    		System.out.println("BoardEditService 실행");
    	
    	//글삭제
    	}else if(url_command.equals("/delete.board")) {
    		action = new BoardDeleteViewService();
    		forward = action.execute(request, response);
    		System.out.println("BoardDeleteViewService 실행");
    		
    	}else if(url_command.equals("/deleteok.board")) {
    		action = new BoardDeleteService();
    		forward = action.execute(request, response);
    		System.out.println("BoardDeleteService 실행");
    	
    	//글상세
    	}else if(url_command.equals("/content.board")) {
    		action = new BoardContentService();
    		forward = action.execute(request, response);
    		System.out.println("BoardcontentService 실행");
    	
    	//글목록	
    	}else if(url_command.equals("/list.board")) {
    		action = new BoardListService();
    		forward = action.execute(request, response);
    		System.out.println("BoardListService 실행");
    	
    	//댓글등록, 삭제
    	}else if(url_command.equals("/replyok.board")) {
    		action = new BoardReplyService();
    		forward = action.execute(request, response);
    		System.out.println("BoardReplyService 실행");
    		
    	}else if(url_command.equals("/replydelete.board")) {
    		action = new BoardReplyDeleteService();
    		forward = action.execute(request, response);
    		System.out.println("BoardReplyDeleteService 실행");
    		    		
    	//답글
    	}else if(url_command.equals("/rewrite.board")) {
    		action = new BoardRewriteViewService();
    		forward = action.execute(request, response);
    		System.out.println("BoardRewriteViewService 실행");
    		
    	}else if(url_command.equals("/rewriteok.board")) {
    		action = new BoardRewriteService();
    		forward = action.execute(request, response);
    		System.out.println("BoardRewriteService 실행");
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		
    		}else { 
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
