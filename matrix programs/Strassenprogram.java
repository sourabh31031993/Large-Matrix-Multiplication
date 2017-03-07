package strassenbig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.math.BigInteger;

/**
 *
 * @author Girish
 */
public class StrassenBig {
public BigInteger [][] multiply(BigInteger[][] A, BigInteger[][] B)
    {        
        int n = A.length;
        BigInteger[][] R = new BigInteger[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = A[0][0].multiply(B[0][0]);
        else
        {
            BigInteger[][] A11 = new BigInteger[n/2][n/2];
            BigInteger[][] A12 = new BigInteger[n/2][n/2];
            BigInteger[][] A21 = new BigInteger[n/2][n/2];
            BigInteger[][] A22 = new BigInteger[n/2][n/2];
            BigInteger[][] B11 = new BigInteger[n/2][n/2];
            BigInteger[][] B12 = new BigInteger[n/2][n/2];
            BigInteger[][] B21 = new BigInteger[n/2][n/2];
            BigInteger[][] B22 = new BigInteger[n/2][n/2];
 
            /** Dividing matrix A into 4 halves **/
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
 
           
 
            BigInteger [][] M1 = multiply(add(A11, A22), add(B11, B22));
            BigInteger  [][] M2 = multiply(add(A21, A22), B11);
            BigInteger  [][] M3 = multiply(A11, sub(B12, B22));
            BigInteger  [][] M4 = multiply(A22, sub(B21, B11));
            BigInteger  [][] M5 = multiply(add(A11, A12), B22);
            BigInteger  [][] M6 = multiply(sub(A21, A11), add(B11, B12));
            BigInteger  [][] M7 = multiply(sub(A12, A22), add(B21, B22));
 
            
            BigInteger  [][] C11 = add(sub(add(M1, M4), M5), M7);
            BigInteger  [][] C12 = add(M3, M5);
            BigInteger  [][] C21 = add(M2, M4);
            BigInteger  [][] C22 = add(sub(add(M1, M3), M2), M6);
 
           
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }
        
        return R;
    }
public BigInteger[][] sub(BigInteger[][] A, BigInteger[][] B)
    {
        int n = A.length;
        BigInteger[][] C = new BigInteger[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j].subtract(B[i][j]);
        return C;
    }
   
    public BigInteger[][] add(BigInteger[][] A, BigInteger[][] B)
    {
        int n = A.length;
        BigInteger[][] C = new BigInteger[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j].add(B[i][j]);
        return C;
    }
    
    public void split(BigInteger[][] P, BigInteger[][] C, int iB, int jB) 
    {
        for(int i1=0,i2=iB;i1<C.length;i1++,i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
   
    public void join(BigInteger[][] C, BigInteger[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }  
    private static String format(BigInteger[][] matrix) {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < matrix.length; row++) {
        for (int col = 0; col < matrix[row].length; col++) {
            sb.append(matrix[row][col]);
            sb.append(' ');
        }
        sb.append(System.lineSeparator());
    }
    return sb.toString();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
      BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Girish/Desktop/Strassenin1.txt")));
    try (Scanner scan = new Scanner(input)) {
        System.out.println("Strassen Multiplication Algorithm Test\n");
        StrassenBig s = new StrassenBig();
        System.out.println("Enter order n :");
        int N = scan.nextInt();
        /** Accept two 2d matrices **/
        System.out.println("Enter N order matrix 1\n");
        BigInteger[][] A = new BigInteger[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextBigInteger();
 
        System.out.println("Enter N order matrix 2\n");
        BigInteger[][] B = new BigInteger[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                B[i][j] = scan.nextBigInteger();
 
        BigInteger[][] C = s.multiply(A, B);
 
        System.out.println("\nProduct of matrices A and  B : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(C[i][j] +" ");
            System.out.println();
        }
 try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/Strassen.txt"))) {
            pw.write("\nProduct of matrices A and  B :              " );
            pw.newLine();
            pw.write("\n"+format(C));
        }
    catch(IOException ex) {
    ex.printStackTrace();
long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
}
}
    }
}
