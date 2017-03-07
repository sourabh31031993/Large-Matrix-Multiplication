package gauss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Girish
 */
public class Gauss {
 private static final double EPSILON = 1e-10;

    // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] A, double[] b) {
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
private static String format(double[] matrix) {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < matrix.length; row++) {
            sb.append(matrix[row]);
            sb.append(' ');
        }
        sb.append(System.lineSeparator());
    return sb.toString();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        

        
        BufferedReader input=new BufferedReader(new FileReader("C:/Users/Girish/Desktop/Gaussinput.txt"));
        try (Scanner scan = new Scanner(input)) {
        
        System.out.println("Gaussian Elimination Algorithm Test\n");
        
        Gauss ge = new Gauss();
 
        
        int N = scan.nextInt();
 
        double[] B = new double[N];
        double[][] A = new double[N][N];
 
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextDouble();
 
        
        for (int i = 0; i < N; i++)
            B[i] = scan.nextDouble();
        double x[]=lsolve(A,B);
         try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/Gaussoutput.txt"))) {
            pw.write("\n"+format(x));
        }
    catch(IOException ex) {
    ex.printStackTrace();
long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
}

        }}}
    