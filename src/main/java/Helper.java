/**
 * This Helper class provides a matrix multiplication function.
 *
 * Author: Adam Botens
 */
public class Helper {
    /**
     * Performs matrix multiplication of two square matrices.
     *
     * @param N The size of the square matrices.
     * @param A The first matrix to be multiplied.
     * @param B The second matrix to be multiplied.
     * @return The result of the matrix multiplication.
     */
    public static int[][] multiply(int N, int[][] A, int[][] B) {
        int[][] C = new int[N][N];
        
        // Iterate through rows of A
        for(int i = 0; i < N; i++) {
            // Iterate through columns of B
            for(int j = 0; j < N; j++) {
                int sum = 0;
                // Iterate through elements of A and B to perform multiplication and summation
                for(int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum; // Store the result in the result matrix C
            }
        }
        return C;
    }
}
