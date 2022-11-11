package kr.or.kosa.service;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class Board_writeok_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		MultipartRequest multi = null;
		
		String msg = "";
		String url = "";
		//파일 업로드
		
		
		try {
			BoardDao dao = new BoardDao();
			
			try {
				multi =  new MultipartRequest(
						request, //클라이언가 서버로 요청하면 자동 생성되는 객체(정보)	
						request.getSession().getServletContext().getRealPath("upload"), //실 저장할 경로(배포경로)	
						1024 * 1024 * 10, //10M
						"UTF-8",
						new DefaultFileRenamePolicy() // 파일 이름 중복되면 (upload > 1.jpg > 1.jpg업로드 > 1_1.jpg)
					);
				String subject = multi.getParameter("subject");
				String writer = multi.getParameter("writer");
				String pwd = multi.getParameter("pwd");
				String email = multi.getParameter("email");
				String homepage = multi.getParameter("homepage");
				String content = multi.getParameter("content");
				//String filename = request.getParameter("filename");
				String filename = multi.getOriginalFileName("filename"); //원래 업로드 될 때 이름
				String filesystemname = multi.getFilesystemName("filename"); //renamepolicy에 영향을 받는 이름 (서버에 저장되는 파일명)
				
				
				

				Board board = new Board();
				board.setSubject(subject);
				board.setWriter(writer);
				board.setPwd(pwd);
				board.setEmail(email);
				board.setHomepage(homepage);
				board.setContent(content);
				board.setFilename(filename);
				board.setFilesystemname(filesystemname);
				
				int result = dao.writeok(board);

				if (result > 0) {
					msg = "insert success";
					url = "board_list.do";
				} else {
					msg = "insert fail";
					url = "board_write.do";
				}

				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				msg = "File Upload Error";
				url = "board_write.do";
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/board/redirect.jsp");
			}
	
	   	  
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		forward = new ActionForward();
   	  	forward.setRedirect(false);
   	  	forward.setPath("/board/redirect.jsp");
		
		return forward;
	}

}
