package kr.or.kosa.service;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class Board_writeok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			BoardDao dao = new BoardDao();
	
			String subject = request.getParameter("subject");
			String writer = request.getParameter("writer");
			String pwd = request.getParameter("pwd");
			String email = request.getParameter("email");
			String homepage = request.getParameter("homepage");
			String content = request.getParameter("content");
			String filename = request.getParameter("filename");
			
			Board board = new Board();
			board.setSubject(subject);
			board.setWriter(writer);
			board.setPwd(pwd);
			board.setEmail(email);
			board.setHomepage(homepage);
			board.setContent(content);
			board.setFilename(filename);
			
			int result = dao.writeok(board);
			
			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "insert success";
				url = "board_list.do";
			} else {
				msg = "insert fail";
				url = "board_write.do";
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
	   	  
		} catch (NamingException e) {
			e.printStackTrace();
		}
		forward = new ActionForward();
   	  	forward.setRedirect(false);
   	  	forward.setPath("/board/redirect.jsp");
		
		return forward;
	}

}
