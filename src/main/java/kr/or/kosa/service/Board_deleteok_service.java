package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class Board_deleteok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("board_delete_ok.java 진입");
		String msg = "";
		String url = "";
		
		
		String idx = request.getParameter("idx");
		String pwd = request.getParameter("pwd");
		
		try {
			BoardDao dao = new BoardDao();
			
			int result = dao.deleteOk(idx, pwd);
			
			if(result > 0){
				msg="delete success";
				url="board_list.do";
			}else{
				msg="delete fail";
				url="board_list.do";
			}
			
			
		} catch (Exception e) {
			System.out.println("board_deleteok에서의 에러 : " + e.getMessage());
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/redirect.jsp");
		return forward;
	}

}
