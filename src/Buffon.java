import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TODO
 *
 * @author Jacob Ginn
 * @author Kevin Filanowski
 * @version April 13th, 2020
 */
public class Buffon {

    /** Our message passer to communicate between threads. */
    Channel<Integer> queue;

    /** Scanner to read in user input. */
    Scanner in;

    /** The number of needles to drop. */
    int numExperiments;

    /** The number of threads the user wants to have to run the experiment on. */
    int numThreads;

    /** The length of a needle. */
    double length;

    /** The distance between the lines. */
    double distance;

    /**
     * Default Contructor for Buffon.
     */
    public Buffon() {
        this.in = new Scanner(System.in);
        queue = new MessageQueue<Integer>();
    }

    /**
     * Starts the experiment.
     */
    void start() {
        promptUser();
        runExperiment();

    }

    /**
     * Ask the user for input on the number of experiments, the number of threads,
     * the length of each needle and the distance between the lines.
     */
    void promptUser() {
        numExperiments = getIntFromUser("How many experiments would you like to run? -> ", "Please enter an integer for the number of experiments -> ");
        numThreads = getIntFromUser("How many threads would you like to have? -> ", "Please enter an integer for the number of threads -> ");
        length = getDoubleFromUser("Enter the length of each needle -> ", "Please enter an integer for the length of each needle -> ");

        // Check to make sure the distance is greater than the length of the needle.
        do {
            distance = getDoubleFromUser("Enter the distance between the lines -> ", "Please enter an integer for the distance between the lines -> ");
            if (distance <= length) {
                System.out.println("Please make sure the distance is greater than " + length + ", the length of the needle.");
            }
        } while (distance <= length);
    }

    /**
     * Runs the experiment to predict PI using the user specified parameters.
     */
    private void runExperiment() {
        for(int i = 0; i < numThreads; i++) {
            Thread worker = new Thread(new Experiment(i,numExperiments/numThreads, length, distance,queue));
            worker.start();
        }

        // Fill in last thread here
        // TODO
    }

    /**
     * @param prompt
     * @param error
     * @return
     */
    int getIntFromUser(String prompt, String error) {
        System.out.print(prompt);
        while (!in.hasNextInt()) {
            System.out.print(error);
            in.next();
        }
        return in.nextInt();
    }

    /**
     * @param prompt
     * @param error
     * @return
     */
    double getDoubleFromUser(String prompt, String error) {
        System.out.print(prompt);
        while (!in.hasNextDouble()) {
            System.out.print(error);
            in.next();
        }
        return in.nextDouble();
    }


    /**
     * Main method of the program starts here.
     *
     * @param args Command line arguments, unused.
     */
    public static void main(String[] args) {
        Buffon bf = new Buffon();
        bf.start();
    }
}
