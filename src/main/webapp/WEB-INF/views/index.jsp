<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.nisum.manage.persistence.repositories.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
<%
  List<User> users = (List<User>) request.getAttribute( "users" );
%>
<%
  for (User user : users) {

    user.getName();
%>
<div>sabra pepe</div>
<%
  }
%>
</body>
</html>
