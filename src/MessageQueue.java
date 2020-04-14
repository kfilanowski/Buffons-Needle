import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *The Message Queue Class that allows for communication between our Buffon to our
 * Experiment class. Experiment will return the number of hits it gets.
 * @author Jacob Ginn
 * @author Kevin Filanowski
 * @version April 13th, 2020
 */
public class MessageQueue<T> implements Channel<T> {

    /** The blocking queue that is used for the message passing. */
    private BlockingQueue<T> queue;

    /**
     * The constructor for the MessageQueue
     */
    public MessageQueue() {
        queue = new ArrayBlockingQueue<>(10);
    }

    /**
     * This method puts the data into the ArrayBlockingQueue. If the queue is full it
     * will wait until there is a open index to input it.
     * @param data The data to send.
     * @throws InterruptedException Thrown when the thread is interrupted.
     */
    @Override
    public void send(T data) throws InterruptedException {
        queue.put(data);
    }

    /**
     * Receives the data that is sent back from the Experiment. This calls take
     * which will wait until there is something in a queue to receive it. This stops
     * us from having to poll to see if something is there.
     * @return - The the data that is sent back from the experiment.
     * @throws InterruptedException Thrown when the thread is interrupted.
     */
    @Override
    public T receive() throws InterruptedException {
        return queue.take();
    }
}