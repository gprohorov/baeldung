package pro.socket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import pro.socket.proxy.service.handlers.AutoCommandSessionHandler;

/**
 * @author shrralis
 * @see <a href="https://t.me/Shrralis">https://t.me/Shrralis</a>
 */
public class WebSocketStompClientCreator {

    private static final String BASE_URL = "localhost:8080";

    private WebSocketStompClientCreator() {
    }

    public static WebSocketStompClient createWithUrl(final String url, final String topicUrl) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler(topicUrl);
        stompClient.connect(url, sessionHandler);
        return stompClient;
    }

    public static WebSocketStompClient createWithUrl(final String url) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new AutoCommandSessionHandler("/app" + url);
        stompClient.connect("ws://" + BASE_URL + url, sessionHandler);
        return stompClient;
    }
}
