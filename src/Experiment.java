/**
 * TODO
 *
 * @author Jacob Ginn
 * @author Kevin Filanowski
 * @version April 13th, 2020
 */
public class Experiment implements Runnable {

    /** Our message passer between threads. */
    Channel<Integer> queue;

    /** The unique id of the experiment */
    int id;

    /** The number of needles to drop. */
    int numExperiments;

    /** The length of a needle. */
    double length;

    /** The distance between the lines. */
    double distance;

    /**
     * The constructor for the experiment class.
     * @param id - the unique id of the experiment
     * @param numExperiments - the number of experiments the user wants run.
     * @param length - The length of a needle.
     * @param distance - The distance between the lines.
     */
    public Experiment(int id, int numExperiments, double length, double distance, Channel<Integer> queue) {
        this.id = id;
        this.numExperiments = numExperiments;
        this.length = length;
        this.distance = distance;
        this.queue = queue;
    }


    /**
     *
     */
    @Override
    public void run() {

    }
}
