package pro.socket.robot;

import org.springframework.web.socket.messaging.WebSocketStompClient;
import pro.socket.WebSocketStompClientCreator;

import java.util.Scanner;

public class StompRobotClient {

    private static final String URL = "ws://localhost:8080/robot";

    private static final String TOPIC_URL = "/topic/commands";

    public static void main(String[] args) {
        WebSocketStompClient stompClient = WebSocketStompClientCreator.createWithUrl(URL, TOPIC_URL);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}