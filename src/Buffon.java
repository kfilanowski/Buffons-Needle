import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * This is the Buffon class. This class starts a thread pool that is the length of
 * how many threads that the user wanted. This starts a thread and gives it a
 * good share of the work which is the number of needles the user wanted.
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
     * Default Constructor for Buffon.
     */
    public Buffon() {
        in = new Scanner(System.in);
        queue = new MessageQueue<>();
    }

    /**
     * Starts the experiment.
     * Prompts the user for input,
     * runs the experiment,
     * and prints the results.
     */
    void start() throws InterruptedException {
        promptUser();
        runExperiment();
        printResult(calculateResult());
    }

    /**
     * Prints the results of the experiment.
     *
     * @param result - The estimate of PI.
     */
    void printResult(double result) {
        System.out.println("The Estimation if Pi is -> " + result);
    }

    /**
     * Collects the messages sent from the experiments and calculates the results.
     */
    double calculateResult() throws InterruptedException {
        // The total number of times an experiment hit.
        int total_hits = 0;
        // A counter to keep track of how long we must listen.
        int counter = 0;
        // The receiver of the messages.
        Integer receiver = 0;
        // Loop and get results.
        while (counter < numThreads) {
            receiver = queue.receive();
            if (receiver != null) {
                total_hits += receiver;
                counter++;
            }
        }

        // Calculate the result
        return (2 * length * numExperiments) / (distance * total_hits);
    }

    /**
     * Ask the user for input on the number of experiments, the number of threads,
     * the length of each needle and the distance between the lines.
     */
    void promptUser() {
        distance = getDoubleFromUser("Please enter the distance between lines -> ",
                "Please enter a positive integer for the distance between the lines -> ");
        // Check to make sure the distance is greater than the length of the needle.
        do {
            length = getDoubleFromUser("Enter the length of each needle -> ",
                    "Please enter a positive integer for the length of each needle -> ");
            if (distance <= length) {
                System.err.println("Please make sure the length of the needle is less than "
                        + distance + ", the distance between lines.");
            }

        } while (distance <= length);

        numExperiments = getIntFromUser("How many needles to drop overall? > ",
                "Please enter a positive integer for the number of experiments > ");
        do {
            numThreads = getIntFromUser("How many threads? > ",
                    "Please enter a positive integer for the number of threads > ");
            if (numExperiments < numThreads) {
                System.err.println("Please make sure the number of threads is less than "
                        + numExperiments + ", the number of needles to drop.");
            }
        } while (numExperiments < numThreads);
    }

    /**
     * Runs the experiment to predict PI using the user specified parameters.
     */
    private void runExperiment() {
        ExecutorService pool = java.util.concurrent.Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            if (i == 0) {
                pool.execute(new Experiment(i, numExperiments / numThreads + divisible(), length, distance, queue));
            } else {
                pool.execute(new Experiment(i, numExperiments / numThreads, length, distance, queue));
            }
        }
        pool.shutdown();
    }

    /**
     * Determines if the number of experiments is divisible by the number of threads.
     * If there is a remainder it will add the rest to the first thread that is executed.
     * We decided this because since it is the first thread executed it would have more
     * time than other threads.
     *
     * @return The remainder of the numExperiments/numthreads
     */
    int divisible() {
        return numExperiments % numThreads;
    }

    /**
     * Prompts the user for an integer. If the user enters something that is not an integer
     * it will ask the user again.
     * @param prompt - The prompt message to the user
     * @param error - The error message that is displayed if the user inputs
     *                an invalid value.
     * @return - The int that the user inputs
     */
    int getIntFromUser(String prompt, String error) {
        System.out.print(prompt);
        int answer;
        do {
            while (!in.hasNextInt()) {
                System.err.print(error);
                in.next();
            }
            answer = in.nextInt();
            if (answer <= 0) {
                System.err.print(error);
            }
        } while (answer <= 0);
        return answer;
    }

    /**
     * Prompts the user for a double. If the user enters something that is not a double
     * it will ask the user again.
     * @param prompt - The prompt message to the user
     * @param error - The error message that is displayed if the user inputs
     *                an invalid value.
     * @return - The double that the user inputs
     */
    double getDoubleFromUser(String prompt, String error) {
        System.out.print(prompt);
        double answer;
        do {
            while (!in.hasNextDouble()) {
                System.err.print(error);
                in.next();
            }
            answer = in.nextDouble();
            if (answer <= 0) {
                System.err.print(error);
            }
        } while (answer <= 0);
        return answer;
    }

    /**
     * Main method of the program starts here.
     *
     * @param args Command line arguments, unused.
     */
    public static void main(String[] args) {
        Buffon bf = new Buffon();
        try {
            bf.start();
        } catch (InterruptedException e){
            System.out.println("Cannot Receive Data!");
        }
    }
}
