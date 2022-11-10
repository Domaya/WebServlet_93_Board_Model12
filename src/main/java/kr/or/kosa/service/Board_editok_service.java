package kr.or.kosa.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class Board_editok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String homepage = request.getParameter("homepage");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
//		String filename = request.getParameter("filename");
		
		System.out.println("Board_editok_service.java 진입");
		
		try {
			BoardDao dao = new BoardDao();
			int result = dao.boardEdit(request);
			
			String idx = request.getParameter("idx");
			String msg="";
			String url="";

			  if(idx == null || idx.trim().equals("")){
				  	msg = "글번호 입력 오류";
					url = "board_list.do";
			  }
			  
				if(result > 0){
					msg="edit success";
					url="board_list.do";
				}else{
					msg="edit fail";
					url="board_edit.do?idx="+idx;
				}
				
				request.setAttribute("board_msg",msg);
				request.setAttribute("board_url",url);

			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		forward = new ActionForward();
   	  	forward.setRedirect(false);
   	  	forward.setPath("/board/redirect.jsp");
		
		
		return forward;
	}

}
