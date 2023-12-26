<%@ page import="java.util.Properties, jakarta.mail.*, jakarta.mail.internet.*" %>
<%@ page import="java.io.IOException, java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Чтение письма</title>
</head>
<body>
<h1>Содержимое письма</h1>

<%
  String host = config.getServletContext().getInitParameter("host");
  String username = config.getServletContext().getInitParameter("username");
  String password = config.getServletContext().getInitParameter("password");

  int messageIndex = Integer.parseInt(request.getParameter("messageIndex"));
  String email = request.getParameter("email");

  Properties properties = new Properties();
  properties.put("mail.smtp.host", host);
  properties.put("mail.smtp.auth", "true");
  properties.put("mail.smtp.starttls.enable", "true");
  properties.put("mail.smtp.port", "587"); // Укажите порт SMTP сервера

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

    // Получение выбранного письма
    Message selectedMessage = messages[messageIndex];


    // Получение содержимого сообщения
    Object content = selectedMessage.getContent();

// Проверка типа содержимого и преобразование в строку, если возможно
    String messageContent = null;

    if (content instanceof String) {
      // Если содержимое является строкой, просто присвоим её переменной
      messageContent = (String) content;
    } else if (content instanceof MimeMultipart) {
      // Если содержимое является MimeMultipart, используем предыдущий код для анализа частей
      MimeMultipart mimeMultipart = (MimeMultipart) content;
      int count = mimeMultipart.getCount();
      StringBuilder messageContentBuilder = new StringBuilder();

      for (int i = 0; i < count; i++) {
        BodyPart bodyPart = mimeMultipart.getBodyPart(i);

        // Получение содержимого части
        Object partContent = bodyPart.getContent();

        // Добавление содержимого к общей строке
        if (partContent instanceof String) {
          // Если содержимое части - строка, добавляем её к результату
          messageContentBuilder.append((String) partContent);
        } else {
          // Обработка других типов частей (вложений, изображений и т. д.)
          // Здесь вы можете добавить логику обработки других типов, если необходимо
          messageContentBuilder.append("Тип содержимого: ").append(bodyPart.getContentType()).append("\n");
        }
      }

      // Присвоение строки общего содержимого
      messageContent = messageContentBuilder.toString();
    }


    out.println("<h3>Subject: " + selectedMessage.getSubject() + "</h3>");
    out.println("<p>From: " + selectedMessage.getFrom()[0] + "</p>");
    out.println("<p>To: " + selectedMessage.getAllRecipients()[0] + "</p>");
    out.println("<p>Date: " + selectedMessage.getSentDate() + "</p>");
    out.println("<hr>");
    out.println("<pre>" + messageContent + "</pre>");

    inbox.close(false);
    store.close();
  } catch (MessagingException e) {
    out.println("Ошибка: " + e.getMessage());
  }
%>
</body>
</html>
