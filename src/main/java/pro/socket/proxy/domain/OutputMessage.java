package pro.socket.proxy.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OutputMessage {

    private final String from;

    private final String text;

    private final String time;

    public OutputMessage(final String from, final String text, final String time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    public OutputMessage(final Message message) {
        this.from = message.getFrom();
        this.text = message.getText();
        this.time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }
}