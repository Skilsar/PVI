<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HGG_08</title>
</head>
<body>
Index
<%
    String ur1 = (String)  request.getServletContext().getInitParameter("URL1");
    String ur2 = (String)  request.getServletContext().getInitParameter("URL2");
%>
<div>
    <%= "Value: " + ur1%>
</div>
<div>
    <%= "Value: " + ur2%>
</div>

</body>
</html>