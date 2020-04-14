/**
 * This interface defines a message passer.
 * @param <T> The data to send and receive.
 *
 * @author Jacob Ginn
 * @author Kevin Filanowski
 * @version April 13th, 2020
 */
public interface Channel<T> {
    /**
     * Send data.
     * @param data The data to send.
     * @throws InterruptedException Thrown when the thread is interrupted.
     */
    void send(T data) throws InterruptedException;

    /**
     * Receive Data.
     * @return The data to receive.
     * @throws InterruptedException Thrown when the thread is interrupted.
     */
    T receive() throws InterruptedException;
}
