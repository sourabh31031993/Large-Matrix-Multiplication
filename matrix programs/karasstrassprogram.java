package karasstrass;

import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Girish
 */
public class Karasstrass {

/**
 *
 * @author Girish
 */
public BigInteger [][] multiply(BigInteger[][] A, BigInteger[][] B)
    {        
    
    
        int n = A.length;
        BigInteger R[][]= kmm(A,B);
        /** base case **/
        if (n == 1)
        return R;
        
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
    public static BigInteger[][] kmm(BigInteger a[][],BigInteger b[][])
{
int c,d,k;
int m=a.length; 
int mcol=a[0].length;
int p=b.length;
int pcol=b[0].length;
int s1=getsize(a);
int s2=getsize(b);
int base=Math.max(s1, s2);
BigInteger ten=new BigInteger("10");
BigInteger n=(BigInteger)ten.pow(base);
BigInteger mul=(BigInteger)ten.pow(2*base);
BigInteger multiplyres[][]=callmultiply(a,b);
if(base<10)
    return multiplyres;
BigInteger split1[][]=new BigInteger[m][pcol];
BigInteger split2[][]=new BigInteger[m][pcol];
BigInteger split3[][]=new BigInteger[m][pcol];
BigInteger split4[][]=new BigInteger[m][pcol];
BigInteger z3[][]=new BigInteger[m][pcol];
BigInteger z4[][]=new BigInteger[m][pcol];
BigInteger result[][]=new BigInteger[m][pcol];
base=(base/2)+(base%2);
for(int i=0;i<m;i++)
{
    BigInteger sub[]=a[i];
    
for(int j=0;j<sub.length;j++)
{
    split1[i][j]=a[i][j].divide(n);
}
}
for(int i=0;i<m;i++)
{
    BigInteger sub[]=b[i];
for(int j=0;j<sub.length;j++)
{
    split2[i][j]=b[i][j].divide(n);
}
}
for(int i=0;i<m;i++)
{
    BigInteger sub[]=a[i];
for(int j=0;j<sub.length;j++)
{
    split3[i][j]=a[i][j].subtract((a[i][j].divide(n)).multiply(n));
}
}
for(int i=0;i<m;i++)
{
    BigInteger sub[]=b[i];
for(int j=0;j<sub.length;j++)
{
    split4[i][j]=b[i][j].subtract((b[i][j].divide(n)).multiply(n));;
}
}
for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{
 z3[i][j]=split1[i][j].add(split3[i][j]);
}
}
for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{
 z4[i][j]=split2[i][j].add(split4[i][j]);
}
}
BigInteger z2[][]=kmm(split1,split2);
BigInteger z0[][]=kmm(split3,split4);
BigInteger z1[][]=kmm(z3,z4);
BigInteger z2mullong[][]=new BigInteger[m][pcol];
BigInteger z1minusz2[][]=new BigInteger[m][pcol];
BigInteger z1minusz2minusz0[][]=new BigInteger[m][pcol];
BigInteger z1minusz2minusz0muln[][]=new BigInteger[m][pcol];
BigInteger z1minusz2minusz0mulnadd[][]=new BigInteger[m][pcol];
 for ( c = 0 ; c < m ; c++ )
         {
            for ( d = 0 ; d < pcol ; d++ )
            {     
              z2mullong[c][d]=z2[c][d].multiply(mul);
              z1minusz2[c][d]=z1[c][d].subtract(z2[c][d]);
              z1minusz2minusz0[c][d]=z1minusz2[c][d].subtract(z0[c][d]);
              z1minusz2minusz0muln[c][d]=z1minusz2minusz0[c][d].multiply(n);
              z1minusz2minusz0mulnadd[c][d]=z1minusz2minusz0muln[c][d].add(z2mullong[c][d]);
                 result[c][d]=(BigInteger)(z0[c][d].add(z1minusz2minusz0mulnadd[c][d])); 
               }
            }
 
return result; 
}


public static BigInteger[][]  callmultiply(BigInteger a[][],BigInteger b[][])
{
    
     int rowsInA = a.length;
       int columnsInA = a[0].length; // same as rows in B
       int columnsInB = b[0].length;
       BigInteger[][] c = new BigInteger[rowsInA][columnsInB];
       BigInteger cmul[][]=new BigInteger[rowsInA][columnsInB];
       //BigInteger res[][]=new BigInteger[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < rowsInA; j++){ 
                   c[i][j] = a[i][j].multiply(b[i][j]);
           }}
       return c;
}
public static int getsize(BigInteger num[][]){
int control=0,convert;
for(int i=0;i<num.length;i++)
{    
for(int j=0;j<num[0].length;j++)
{
convert=num[i][j].intValue();    
control=(int)Math.log10(convert)+1;    
}
}
return control;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        
    BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Girish/Desktop/Karasstrassin.txt")));
    try (Scanner scan = new Scanner(input)) {
        System.out.println("Strassen Multiplication Algorithm Test\n");
        Karasstrass s = new Karasstrass();
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
 try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/Karasstrassout.txt"))) {
            pw.write("\nProduct of matrices A and  B :              " );
            pw.newLine();
            pw.write("\n"+format(C));
        }}
    catch(IOException ex) {
    ex.printStackTrace();
long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
}
    
}
    }
