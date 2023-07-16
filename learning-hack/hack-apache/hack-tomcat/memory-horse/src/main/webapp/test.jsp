<%--
  Created by IntelliJ IDEA.
  User: xjj
  Date: 2023/7/11
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" import="java.util.Date"%>
<%
Date date = new Date();
%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  hello man
  <%=date.getTime() %>
  </body>
</html>
