<%@page import="kr.or.kosa.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="board" class="kr.or.kosa.dto.Board">
	<jsp:setProperty property="*" name="board" />
</jsp:useBean>

<jsp:forward page="redirect.jsp"></jsp:forward>








