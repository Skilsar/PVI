package gleb.labs.lab12;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket-endpoint")
public class WebSocketServer {

    private Session session;
    private boolean running = true;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        new Thread(this::sendMessages).start();
    }

    @OnMessage
    public void onMessage(String message) {
    }

    @OnClose
    public void onClose() {
        running = false;
    }

    private void sendMessages() {
        try {
            while (running) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String message = "now time is: " + dateFormat.format(new Date());

                session.getBasicRemote().sendText(message);
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
