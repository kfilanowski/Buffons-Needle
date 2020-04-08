import java.util.Vector;

/**
 *
 * @author Jacob Ginn
 * @author Kevin Filanowski
 * @version April 13th, 2020
 */
public class MessageQueue<T> implements Channel<T> {

    /** TODO */
    private Vector<T> queue;

    /**
     * TODO
     */
    public MessageQueue() {
        queue = new Vector<T>();
    }

    /**
     *
     * @param data The data to send.
     */
    @Override
    public void send(T data) {
        queue.add(data); // TODO lookup add vs add element
    }

    /**
     *
     * @return
     */
    @Override
    public T receive() {
        if (queue.size() == 0) {
            return null;
        } else {
            return queue.remove(0);
        }
    }
}
