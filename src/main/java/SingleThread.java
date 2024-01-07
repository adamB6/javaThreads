import java.util.Random;

/**
 * This program measures the performance of matrix multiplications using a single thread.
 *
 * Author: Adam Botens
 */
public class SingleThread {
    public static void main(String[] args) {
        // Define an array of matrix sizes to test
        int[] n = {10, 25, 50, 100, 250, 500};
        
        // Define an array of the number of multiplications to perform for each matrix size
        int[] numMultiplications = {10, 50, 100};
        
        // Initialize variables for time measurements
        double sum = 0;
        double average = 0;
        
        // Loop through matrix sizes
        for (int N : n){
            // Loop through the number of multiplications
            for (int num : numMultiplications){
                sum = 0;
                
                // Perform the test 10 times and calculate the average time
                for(int i = 0; i < 10; i++){
                    double start = System.currentTimeMillis(); // Record start time
                    performTest(N, num); // Perform the matrix multiplication test
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

    // Method to perform the matrix multiplication test
    public static void performTest(int N, int numMultiplications) {
        Random r = new Random();

        for(int i = 0; i < numMultiplications; i++){
            int[][] A = new int[N][N];
            int[][] B = new int[N][N];

            // Fill matrices A and B with random values
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    A[j][k] = r.nextInt(0, 11);
                    B[j][k] = r.nextInt(0, 11);
                }
            }
            
            // Perform matrix multiplication
            Helper.multiply(N, A, B);
        }
    }
}