package pro.socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import pro.socket.proxy.domain.Message;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger LOG = LogManager.getLogger(MyStompSessionHandler.class);

    private final String topicUrl;

    public MyStompSessionHandler(final String topicUrl) {
        this.topicUrl = topicUrl;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        LOG.info("New session established : " + session.getSessionId());

        session.subscribe(topicUrl, this);

        LOG.info("Subscribed to " + topicUrl);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
                                Throwable exception) {
        LOG.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        LOG.info("Received : " + msg.getText() + " from : " + msg.getFrom());
    }
}