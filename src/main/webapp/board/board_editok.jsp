<%@page import="kr.or.kosa.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  //1. board_edit.jsp > 입력값 받기 > board dto 객체에 담기
  
  //방법 1) request.getParameter("subject") > new Board > setter > request > return Board
  //가장 일반적인 방법 		  
		  
  //방법 2) usebean 액션태그 (property )통해서 setter  주입
  

  //방법 3) request 객체를 통으로 넘기는 방법 (이 방법) >> 실습코드 ...
  
  request.setCharacterEncoding("UTF-8");
  String idx = request.getParameter("idx");
  

%>
<jsp:forward page="redirect.jsp"></jsp:forward>