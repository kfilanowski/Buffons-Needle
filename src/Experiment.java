import java.util.Random;

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

    /** The return value of a hit. */
    private final int HIT = 1;

    /** The return value of a miss.  */
    private final int MISS = 0;

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
        Random rand = new Random();
        double theta;
        double centerPoint;
        double nearestDistance;
        double hypot;
        double opp;
        int result = 0;

        for (int i = 0; i <numExperiments; i++) {
            theta = rand.nextDouble() * 180.0;
            if (theta > 90) {
                theta = 180 - theta;
            }
            centerPoint = rand.nextDouble() * this.distance;
            nearestDistance = Math.min(distance - centerPoint, centerPoint);

            hypot = length / 2;
            opp = hypot * Math.sin(Math.toRadians(theta));
            if (nearestDistance <= opp) {
                result++;
            }
        }
        queue.send(result);
        System.out.println("Sending from Thread " + Thread.currentThread().getId());
    }
}
