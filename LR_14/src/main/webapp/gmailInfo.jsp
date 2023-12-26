<%@ page import="java.util.Properties, jakarta.mail.*, jakarta.mail.internet.*" %>
<%@ page import="java.io.IOException, java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Почтовый ящик</title>
</head>
<body>
<h1>Список писем</h1>

<%
    String host = config.getServletContext().getInitParameter("host");
    String username = config.getServletContext().getInitParameter("username");
    String password = config.getServletContext().getInitParameter("password");
    String email = request.getParameter("email");

    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", "587");

    Session sessionInfo = Session.getInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Store store = sessionInfo.getStore("imaps");
        store.connect(host, email, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        // Отображаем список писем (от новых в старым)
        out.println("<ul>");
        for (int i = messages.length - 1; i >= 0; i--) {
            out.println("<li><a href='readMail.jsp?messageIndex=" + i + "&email=" + email + "'>" + messages[i].getSubject() + "</a></li>");
        }
        out.println("</ul>");

        // Отображаем список писем в обратном порядке
        /*out.println("<ul>");
        for (int i = 0; i < messages.length; i++) {
            out.println("<li><a href='readMail.jsp?messageIndex=" + i + "&email=" + email + "'>" + messages[i].getSubject() + "</a></li>");
        }
        out.println("</ul>");*/

        inbox.close(false);
        store.close();
    } catch (MessagingException e) {
        out.println("Ошибка: " + e.getMessage());
    }
%>
</body>
</html>
