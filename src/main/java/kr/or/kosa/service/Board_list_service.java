package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.utils.ThePager;

public class Board_list_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {


		List<Board> boardlist = new ArrayList<>();
		//DB작업
		try {
			BoardDao dao = new BoardDao();
			
			//boardlist = dao.list(int cpage, int pagesize);
			//머임 ??
			
			int totalboardcount = dao.totalBoardCount();
			
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			
			if(ps == null || ps.trim().equals("")) {
				ps = "5";
			}
			
			if(cp == null || cp.trim().equals("")) {
				cp = "1";
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize; //  20 << 100/5   //게시글 개수가 딱 맞아떨어지면
			}else {
				pagecount = (totalboardcount / pagesize) + 1;//딱 맞아떨어지지 않고 나머지가 있으면 한 페이지 더 추가
			}
			boardlist = dao.list(cpage, pagesize);
			
			ThePager pager = new ThePager(totalboardcount,cpage,pagesize,3,"board_list.do");
			
			request.setAttribute("pager", pager);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", boardlist);
			request.setAttribute("totalboardcount", totalboardcount);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		//데이터 저장
		
		 //뷰 설정하기
	    //viewpage = "/WEB-INF/views/register/register_welcome.jsp";
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false); //True하면 클라이언트가 새로운 페이지를 요청하게 할 거라는거임
	    forward.setPath("/board/board_list.jsp");
	    
		return forward;
	}

}
