package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.Board_content_service;
import kr.or.kosa.service.Board_delete_service;
import kr.or.kosa.service.Board_deleteok_service;
import kr.or.kosa.service.Board_edit_service;
import kr.or.kosa.service.Board_editok_service;
import kr.or.kosa.service.Board_list_service;
import kr.or.kosa.service.Board_replydeleteok_service;
import kr.or.kosa.service.Board_rewrite_service;
import kr.or.kosa.service.Board_rewriteok_service;
import kr.or.kosa.service.Board_writeok_service;
import kr.or.kosa.service.board_replyok_service;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());
	
		Action action = null;
		ActionForward forward = null;
		System.out.println(urlcommand);
		if(urlcommand.equals("/board_list.do")) {///////////////////////완
			//System.out.println("도현정바보");
			//목록 조회.......
			//뷰를 제공하기 전에 데이터를 가져와야 하지??
			action = new Board_list_service();
			forward = action.execute(request, response);
			//board_list_service
			
		}else if(urlcommand.equals("/board_write.do")) {////////////////////////완
			//글 작성 페이지 (뷰만 제공)
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/board_write.jsp");
			
		}else if(urlcommand.equals("/board_writeok.do")) {/////////////////////완
			//글 실제 등록 (서비스)
			action = new Board_writeok_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/board_content.do")) {/////////////////////완
			//글 상세보기
			action = new Board_content_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/board_delete.do")) { /////////////////////완
			//글 삭제 (뷰만 제공)
			action = new Board_delete_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/board_deleteok.do")) { ////////////////////완
			//글 삭제 데베에 반영
			action = new Board_deleteok_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/board_edit.do")) { /////////////////////완
			//글 수정 (뷰 제공 + 기존 내용을 가져와야 함) ....
			//board_edit_Service에서 기존 내용을 가져와 함께 뷰에 보내야 함
			
			action = new Board_edit_service();
			forward = action.execute(request, response); 
			
		}else if(urlcommand.equals("/board_editok.do")) {	 /////////////////////완
			//글 수정 데베에 반영
			action = new Board_editok_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/board_replyok.do")) { /////////////////////////////완
			// 댓글 작성 반영
			action = new board_replyok_service();
			forward = action.execute(request, response);
		}else if(urlcommand.equals("/board_rewrite.do")) { ////////////////////////완
			//답글 페이지
			action = new Board_rewrite_service();
			forward = action.execute(request, response);

		}else if(urlcommand.equals( "/board_rewriteok.do")) { //////////////////완
			//답글 실제 등록
			action = new Board_rewriteok_service();
			forward = action.execute(request, response);
			
		}else if(urlcommand .equals("/boardreply_deleteOk.do")) { ///////////////////////완
			//댓글 삭제
			action = new Board_replydeleteok_service()	;
			forward = action.execute(request, response);
		}
		//else if(urlcommand == "redirect.do") {
			
		if(forward != null) {
			if(forward.isRedirect()) { //true 페이지 재요청(location.href="페이지"
					response.sendRedirect(forward.getPath());
			}else { //기본적으로 forward 방식을 지향 
						//1. UI 가 전달된 경우
						//2. UI + 로직
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "GET");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "POST");
	}

}
