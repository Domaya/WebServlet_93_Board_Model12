package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;

public class board_replyok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("board_replyok_service 진입");
		
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		String idx = request.getParameter("idx");
		String userid = "empty";
		
		String msg = "";
		String url = "";
		
		try {
			BoardDao dao = new BoardDao();
			int idx_fk = Integer.parseInt(idx);
			
			int result = dao.replywrite(idx_fk, writer, userid, content, pwd);
			
			if(result > 0) { //댓글 달기 성공
				msg = "댓글 작성 성공";
				url = "board_content.do?idx=" + idx;
			}else {
				msg = "댓글 작성 실패";
				url = "board_content.do?idx=" + idx;
			}
			
		} catch (Exception e) {
			System.out.println("댓글 달기 오류 : " + e.getMessage());
		}
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/redirect.jsp");
		return forward;
	}

}
