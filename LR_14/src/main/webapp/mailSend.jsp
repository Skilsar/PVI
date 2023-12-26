<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Отправка почты</title>
</head>
<body>
<h1>Отправка почты</h1>

<form action="/HGG_14/sendEmail" method="post">
  <label for="recipient">Получатель:</label>
  <input type="email" id="recipient" name="mail" required><br><br>

  <label for="message">Сообщение:</label><br>
  <textarea id="message" name="message" rows="4" cols="50" required></textarea><br><br>

  <input type="submit" value="Отправить">
</form>
</body>
</html>