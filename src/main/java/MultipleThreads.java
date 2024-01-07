import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This program measures the performance of matrix multiplications using single,
 * two, and ten threads. It calculates the average execution time for various
 * matrix sizes and numbers of multiplications.
 *
 * Author: Adam Botens
 */
public class MultipleThreads {
    // Define the matrix sizes and numbers of multiplications to be tested
    static final int[] n = {10, 25, 50, 100, 250, 500};
    static final int[] numMultiplications = {10, 50, 100};

    // Determine the number of available processors and reserve two for other tasks
    static final int AVAIL_PROCESSORS = Runtime.getRuntime().availableProcessors() - 2;
    // You can also set a fixed number of processors with this line:
    // static final int AVAIL_PROCESSORS = 2;

    public static void main(String[] args) {
        double sum = 0;
        double average = 0;

        System.out.println("Number of available processors: " + AVAIL_PROCESSORS);

        // Loop through matrix sizes
        for (int N : n) {
            // Loop through the number of multiplications
            for (int num : numMultiplications) {
                sum = 0;
                // Perform the test 10 times and calculate the average time
                for (int i = 0; i < 10; i++) {
                    double start = System.currentTimeMillis(); // Record start time
                    fillMatrices(N, num); // Perform the matrix multiplication test
                    double end = System.currentTimeMillis(); // Record end time
                    sum += (end - start) / 1000; // Calculate and accumulate the duration in seconds
                }
                // Calculate the average duration for the 10 tests
                average = sum / 10;
                // Print the results
                System.out.println("Matrix Size = " + N + "\tNum Multiplications = "
                        + String.format("%-3s", num) + "\tAverage Time = "
                        + String.format("%.4f seconds", average));
            }
        }
    }

    // Function to perform matrix multiplications using multithreading
    public static void fillMatrices(int N, int numMultiplications) {
        ExecutorService executor = Executors.newFixedThreadPool(AVAIL_PROCESSORS);

        for (int i = 0; i < numMultiplications; i++) {
            executor.submit(new MultCallable(N));
        }
        executor.shutdown();
        try {
            // Wait for all threads to finish, given a maximum time limit
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("This exception should not happen in this example");
            System.out.println("Exception message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

// Callable class to perform matrix multiplications
class MultCallable implements Callable<Void> {
    int N;

    public MultCallable(int N) {
        this.N = N;
    }

    @Override
    public Void call() throws Exception {
        Random r = new Random();
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        // Fill matrices A and B with random values
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                A[j][k] = r.nextInt(0, 11);
                B[j][k] = r.nextInt(0, 11);
            }
        }
        // Perform matrix multiplication
        Helper.multiply(N, A, B);
        return null;
    }
}
