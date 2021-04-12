package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		//첨부파일 <file>
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024*1024*10; //10M 네이버 계산기
		
		ActionForward forward = null;
		
		try {
			
			MultipartRequest multi = new MultipartRequest(
					request, //기존에 있는  request 객체의 주소값 
					uploadpath, //실 저장 경로 (배포경로)
					size, //10M
					"UTF-8",
					new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
					);
			
			String idx = multi.getParameter("idx");
			
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String email = multi.getParameter("email");
			String homepage = multi.getParameter("homepage");
			//String filename = multi.getParameter("filename");
			String pwd = multi.getParameter("pwd"); 
			
			//첨부파일 <file>
			Enumeration filenames = multi.getFileNames();
			String file = (String)filenames.nextElement();
			String filename = multi.getFilesystemName(file);
			
			System.out.println(writer+pwd+subject+content+email+homepage+filename);
			
			if(filename == null) {
				filename = "";
			}
			
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
