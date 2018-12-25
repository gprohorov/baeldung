package pro.socket.proxy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import pro.socket.WebSocketStompClientCreator;
import pro.socket.proxy.service.handlers.AutoCommandSessionHandler;

/**
 * The service has {@link AutoCommandSessionHandler} that sends commands automatically to specified topic.
 *
 * @author shrralis
 * @see <a href="https://t.me/Shrralis">https://t.me/Shrralis</a>
 */
@Service
public class SendAutoCommandsService {

    public WebSocketStompClient startSending(final String urlToSend) {
        return WebSocketStompClientCreator.createWithUrl(urlToSend);
    }
}
