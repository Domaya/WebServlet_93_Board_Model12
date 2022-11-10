package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class Board_delete_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Board_delete_service.java 진입 (삭제컨트롤러)");

		String msg = "";
		String url = "";
		
		//삭제글 처리 (글번호 받기)
		String idx = request.getParameter("idx"); // 아래로 다 null
		String cpage = request.getParameter("cp"); // current page 
		String pagesize = request.getParameter("ps"); // pagesize
		String referer = (String)request.getHeader("Referer");
		System.out.println("인덱스 : " + idx);
		ActionForward forward = null;
		
		if(idx == null || idx.trim().equals("")){
				msg ="글번호가 넘어오지 않았습니다";
				url = "board_content.do?idx=" + idx;
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				request.setAttribute("idx", idx);
				request.setAttribute("cp", cpage);
				request.setAttribute("ps", pagesize);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/board/redirect.jsp");
				
			} else {
				request.setAttribute("idx", idx);
				request.setAttribute("cp", cpage);
				request.setAttribute("ps", pagesize);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/board/board_delete.jsp");
			}

			return forward;
	}

}
