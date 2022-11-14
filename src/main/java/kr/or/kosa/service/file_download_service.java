package kr.or.kosa.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class file_download_service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		System.out.println("file_download_service.java 진입 (파일 다운로드 서비스) ");
		
		//다운로드 할 파일명 얻기
		 String filesystemname = request.getParameter("filename");
		 
		 //서버에 올라간 경로를 가져옴
		 String savepath = "upload";
		 //String downloadpath = request.getRealPath(savepath); => 이제 더 안 쓰는 방식.
		 String downloadpath = request.getSession().getServletContext().getRealPath(savepath);
		 String FilePath = downloadpath + "\\" + filesystemname;
		 System.out.println("FilePath : " + FilePath);
		 //C:/KOSA_IT/Web(Back)/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebServlet_93_Board_Model12/upload/airplane
		 //\와 알파벳을 붙여 쓸 때 개행문자 등 주석처리가 되지 않는 유니코드 이스케이프가 있음...
		 
		   //IO작업 하기
		   //파일을 읽어서 출력
		   byte[] b = new byte[1024 * 1024 * 10];
		   FileInputStream in = null;
		   String mimeType = "";
		   String encoding = "";
		   try {
				in = new FileInputStream(FilePath);
				mimeType = request.getSession().getServletContext().getMimeType(FilePath);
				System.out.println("mimeType : " + mimeType); // image/jpeg
				  if(mimeType == null){
					     //알수 없는 형식의 파일은 
					     //application/octet-stream 기본값으로 (알수 없는 파일 형식)
					     mimeType = "application/octet-stream";
					  	}
				  //클라이언트 지정되는 형식을 지정(Type)
				  response.setContentType(mimeType);
				  
				  //인코딩처리(한글 : 파일이 가지고 있는 한글에 대한 처리)
					  try {
						encoding = new String(filesystemname.getBytes("utf-8"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				  //4.다운로드 기본 설정 구현
					  response.setCharacterEncoding("utf-8");
					  response.setHeader("Content-Disposition","attachment;filename="+encoding);
					  //5.파일 출력하기
					 
				      int numread;
				      try {
				    	  ServletOutputStream out2 = response.getOutputStream();
				    	  while ((numread = in.read(b, 0, b.length)) != -1) {
						         out2.write(b, 0, numread);
						      }

						      out2.flush();
						      out2.close();
						      in.close();
					} catch (Exception e) {
					}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
//			forward = new ActionForward();
//	   	  	forward.setRedirect(false);
//	   	  	forward.setPath("board_content.do");
//		 
//		return forward;
		   return null;
	}

}
