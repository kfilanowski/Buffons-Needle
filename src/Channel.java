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
     */
    void send(T data);

    /**
     * Receive Data.
     * @return The data to receive.
     */
    T receive();
}
