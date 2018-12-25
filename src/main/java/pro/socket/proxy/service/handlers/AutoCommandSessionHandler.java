package pro.socket.proxy.service.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import pro.socket.MyStompSessionHandler;
import pro.socket.proxy.domain.Message;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shrralis
 * @see <a href="https://t.me/Shrralis">https://t.me/Shrralis</a>
 */
@ComponentScan
public class AutoCommandSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger LOG = LogManager.getLogger(MyStompSessionHandler.class);

    private final String urlToSend;

    public AutoCommandSessionHandler(final String urlToSend) {
        this.urlToSend = urlToSend;
    }

    @Override
    public void afterConnected(final StompSession session, final StompHeaders connectedHeaders) {
        LOG.debug("New session established : " + session.getSessionId());

        final AtomicInteger i = new AtomicInteger();

        do {
            session.send(urlToSend, getSampleMessage());
            try {
                Thread.sleep(3000);
            } catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
            LOG.info("Message sent to websocket server " + i);
        } while (i.incrementAndGet() < 12);
        session.disconnect();
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

    /**
     * A sample message instance.
     *
     * @return instance of <code>Message</code>
     */
    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setFrom("Auto-command");
        msg.setText("Time is " + LocalDateTime.now().toString());
        return msg;
    }
}
