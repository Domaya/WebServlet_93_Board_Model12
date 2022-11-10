package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class Board_edit_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		//인덱스를 얻어와서
		//해당 인덱스가 유효하다면
		//dao를 통해 해당 인덱스의 글을 받아오기?
		
		//혹은 그냥 파라미터로 내용 자체를 받아올 수 있나 ??
		
		System.out.println("board_edit_service.java 진입");
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		
		String msg="";
		String url="";
		
		try {
			BoardDao dao = new BoardDao();
			Board board = dao.getEditContent(idx);
			
			
			//인덱스가 잘못됐으면
			if(idx == null || idx.trim().equals("")){
				response.sendRedirect("board_list.do"); //cpage=1 , ps=5 <-이게 먼소리지?
				return null;
			}
			//수정하려는 board가 널이면
			if(board == null){
				msg ="데이터 오류";
				url ="board_list.do";
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/board/redirect.jsp");
				
			}
			
			//보드가 잘 받아와졌으면
			System.out.println("board : " + board);
			request.setAttribute("idx", idx);
			request.setAttribute("board", board);

		} catch (Exception e) {
			System.out.println("board_edit_service 에러 : " + e.getMessage());
			
		}
		
		forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/board/board_edit.jsp");

		//System.out.println("보드에딧 서비스 액션 함수의 끝"); //여기까지 실행 잘됨....
		
		return forward;
	}

}
