<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realPath = ("resources/upload");
	MultipartRequest mr = 
			new MultipartRequest(request, realPath,
					1024*1024*100, "utf-8", new DefaultFileRenamePolicy());
	
	File f = mr.getFile("upload");
	String f_name = null;
	if(f != null){
		f_name = f.getName();
	}
%>
{"img_url" : "<%= request.getContextPath()%>/upload/<%=f_name%>"}