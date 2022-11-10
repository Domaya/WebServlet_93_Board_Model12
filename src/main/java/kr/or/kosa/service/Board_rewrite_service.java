package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class Board_rewrite_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("board_rewrite_service.java 진입");
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String subject = request.getParameter("subject"); // 답글의 제목으로 사용

		try {
			if (idx == null || subject == null || idx.trim().equals("") || subject.trim().equals("")) {
				response.sendRedirect("board_list.do");
				return null;
			}
			if (cpage == null || pagesize == null) {
				cpage = "1";
				pagesize = "5";
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		request.setAttribute("idx", idx);
		request.setAttribute("cp", cpage);
		request.setAttribute("ps", pagesize);
		request.setAttribute("subject", subject);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/board_rewrite.jsp");
		//forward.setPath("/WEB-INF/views/board/board_rewrite.jsp");
		
		
		return forward;
	}

}
