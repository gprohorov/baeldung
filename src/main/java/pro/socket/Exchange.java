package pro.socket;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by george on 11.12.18.
 */
@Component
public class Exchange {
    private Queue<String> queue = new ArrayBlockingQueue<String>(10);

@PostConstruct
void init(){
    queue.add("77777777777");
    System.out.println("==========================================================");
    System.out.println(queue.size());
}

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }
}
