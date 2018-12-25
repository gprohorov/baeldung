package pro.socket.user;

import org.springframework.web.socket.messaging.WebSocketStompClient;
import pro.socket.WebSocketStompClientCreator;

import java.util.Scanner;

public class StompUserClient {

    private static final String URL = "ws://localhost:8080/user";

    private static final String TOPIC_URL = "/topic/responses";

    public static void main(String[] args) {
        WebSocketStompClient stompClient = WebSocketStompClientCreator.createWithUrl(URL, TOPIC_URL);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}