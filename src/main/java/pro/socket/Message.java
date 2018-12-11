package pro.socket;

import java.time.LocalDateTime;

/**
 * Created by george on 02.12.18.
 */
public class Message {

    private String from;
    private String text;
  //  private LocalDateTime dateTime;

    public Message() {
    }

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}