<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import=" main.java.gleb.labs.lab12.ChoseFile" %>
<%@ page import="java.io.File" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<%
    String d = (String) config.getServletContext().getInitParameter("doc-dir");
    ChoseFile ch = new ChoseFile(d, "doc");
    String ll = null;
    if (ch != null) {
        for (int i = 0; i < ch.list.length; i++) {
            ll = ch.list[i];
%>
<br/>
<a href="./Sss_LR_13?file=<%=ll%>"><%=ll%>
</a>
<%
        }
    }
%>
</body>
</html>