<%@ page import="by.belstu.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HGG_07_Request</title>
</head>
<body>
<%
    CBean cBean = (CBean) request.getAttribute("atrCBean");
   var result = (cBean != null) ? cBean.getValue1() : null;
    var result2 = (cBean != null) ? cBean.getValue2() : null;
    var result3 = (cBean != null) ? cBean.getValue3() : null;
%>
<div>
    <div>
        <%= "Value1: " + result%>
    </div>
    <div>
        <%= "Value2: " + result2%>
    </div>
    <div>
        <%= "Value3: " + result3%>
    </div>
</div>

</body>
</html>