import java.util.Random;

/**
 * This is the Experiment class. This is the runnable class that our threads will
 * run when they are started. This class calculates whether a needle drop is
 * a miss or a hit. Once all of the calculations are complete it will send back
 * the total number of hits.
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

    /** DEBUG VARIABLE FOR DR. K */
    private final boolean DEBUG = false;

    /**
     * The constructor for the experiment class.
     * @param id - the unique id of the experiment
     * @param numExperiments - the number of experiments the user wants run.
     * @param length - The length of a needle.
     * @param distance - The distance between the lines.
     */
    public Experiment(int id, int numExperiments, double length,
                      double distance, Channel<Integer> queue) {
        this.id = id;
        this.numExperiments = numExperiments;
        this.length = length;
        this.distance = distance;
        this.queue = queue;
    }

    /**
     * The run method of our Runnable Object that each thread will run.
     * This will calculate if the needle that is dropped is a hit or a miss.
     * it will return the number of hits that it gets.
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

        for (int i = 0; i < numExperiments; i++) {
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
                if (DEBUG)
                    System.out.println("DEBUG --> Thread : " + Thread.currentThread().getId() + " got a hit!");
            }
        }
        try {
            queue.send(result);
            if (DEBUG)
                System.out.println("DEBUG --> Thread : " + Thread.currentThread().getId() + " is sending this number of hits: " + result);
        } catch (InterruptedException e) {
            System.out.println("Thread : "+ Thread.currentThread().getId() + " Cannot send data!");
            System.exit(1);
        }
    }
}
