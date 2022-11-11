package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Reply;

public class Board_content_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("board_content_service.java 진입"); //진입은 되네...
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");

//		System.out.println("idx: " + idx + " / cpage : " + cpage + " / pagesize : " + pagesize);
		
		ActionForward forward = null;
		Board board = new Board();
		List<Reply> replyList = new ArrayList<>();
		boolean isread = false;
		
		try {
			BoardDao dao = new BoardDao();
			if(idx == null || idx.trim().equals("")) {
				response.sendRedirect("board_list.jsp");
				return null;
			}
			
			idx = idx.trim();
			
			if(cpage == null || cpage.trim().equals("")) {
				//default
				cpage = "1";
			}
			
			if(pagesize == null || pagesize.trim().equals("")) {
				//default
				pagesize = "5";
			}
			
			//조회수 증가
			isread = dao.getReadNum(idx);
			if(isread) { //조회수 증가가 성공했으면
				board = dao.getContent(Integer.parseInt(idx)); //게시글 받아옴
				replyList = dao.replylist(idx); //댓글 받아옴
			}
			
			//System.out.println(board);
			
			request.setAttribute("board", board);
			request.setAttribute("filesystemname", board.getFilesystemname());
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("replyList", replyList);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/board/board_content.jsp");

		return forward;
	}

}
