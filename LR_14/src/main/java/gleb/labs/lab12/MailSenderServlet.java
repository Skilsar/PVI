package main.java.gleb.labs.lab12;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/sendEmail")
public class MailSenderServlet extends HttpServlet {

    private String host;
    private String port;
    private String username;
    private String password;

    @Override
    public void init() {
        host = getServletContext().getInitParameter("host");
        port = getServletContext().getInitParameter("port");
        username = getServletContext().getInitParameter("username");
        password = getServletContext().getInitParameter("password");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String recipient = request.getParameter("mail");
        String messageText = request.getParameter("message");

        String result;

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(properties, authenticator);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Subject of the Email");
            message.setText(messageText);

            Transport.send(message);
            result = "Email good!";
        } catch (MessagingException e) {
            result = "Error Email: " + e.getMessage();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>" + result + "</h3>");
        out.println("</body></html>");
    }
}
