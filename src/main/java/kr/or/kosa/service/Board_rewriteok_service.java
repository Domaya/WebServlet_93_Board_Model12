package kr.or.kosa.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class Board_rewriteok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Board_rewriteok_service.java 진입");
		
		String strIdx = request.getParameter("idx").trim();		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		
		String msg="";
	    String url="";
		
				
		try {
			int idx = Integer.parseInt(strIdx);
			
			Board board = new Board();
			
			board.setSubject(subject);
			board.setWriter(writer);
			board.setEmail(email);
			board.setHomepage(homepage);
			board.setContent(content);
			board.setPwd(pwd);
			board.setFilename(filename);
			board.setIdx(idx);
			
			System.out.println(board);

			BoardDao dao = new BoardDao();
			
			int result = dao.reWriteOk(board);

		    if(result > 0){
		    	msg ="rewrite insert success";
		    	url ="board_list.do";
		    }else{
		    	msg="rewrite insert fail";
		    	url="board_content.do?idx="+board.getIdx();
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/redirect.jsp");
		
		return forward;
	}

}
