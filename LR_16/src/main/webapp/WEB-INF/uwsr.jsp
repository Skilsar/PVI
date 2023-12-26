<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SR</title>
    <link rel="icon" href=${pageContext.request.contextPath}/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="theme-color" content="#000000"/>
    <meta name="description" content="Web site created using create-react-app"/>
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/logo192.png"/>
    <link rel="manifest" href="${pageContext.request.contextPath}/manifest.json"/>

    <%-- Replace script and css names below with those that will appear in the "react/build/static" folder after executing "prod" script
     (or open "react/build/index.html" and use script and css names from there) --%>
    <script defer="defer" src="${pageContext.request.contextPath}/static/js/main.replace.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/main.replace.css" rel="stylesheet"/>
</head>
<body>
<noscript>You need to enable JavaScript to run this app.</noscript>
<div id="root"></div>
</body>
</html>