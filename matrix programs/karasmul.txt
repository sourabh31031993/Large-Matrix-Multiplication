import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.text.DecimalFormat;

public class Karasmul {
public static BigInteger[][] karatsuba(BigInteger x[][],BigInteger y[][])
{
int m=x.length; 
int mcol=x[0].length;
int p=y.length;
int pcol=y[0].length;
        int s1=getsize(x);
        int s2=getsize(y);
        int B = Math.max(s1,s2);
        BigInteger multiplyres[][]=multiply(x,y);
        if (B <= 2000) return multiplyres;                // optimize this parameter

        // number of bits divided by 2, rounded up
        B = (B/2) + (B%2);
BigInteger a[][]=new BigInteger[m][pcol];
BigInteger b[][]=new BigInteger[m][pcol];
BigInteger c[][]=new BigInteger[m][pcol];
BigInteger d[][]=new BigInteger[m][pcol];
BigInteger e[][]=new BigInteger[m][pcol];
BigInteger f[][]=new BigInteger[m][pcol];
BigInteger res[][]=new BigInteger[m][pcol];

for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{    
 a[i][j] = x[i][j].shiftRight(B);
 b[i][j] = x[i][j].subtract(a[i][j].shiftLeft(B));
 c[i][j] = y[i][j].shiftRight(B);
 d[i][j] = y[i][j].subtract(c[i][j].shiftLeft(B));
 e[i][j]=b[i][j].add(a[i][j]);
 f[i][j]=d[i][j].add(c[i][j]);
}}

        // compute sub-expressions
        BigInteger ac[][]    = karatsuba(b, d);
        BigInteger bd[][]    = karatsuba(a, c);
        BigInteger abcd[][]  = karatsuba(e, f);
for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{    
        res[i][j] =ac[i][j].add(abcd[i][j].subtract(ac[i][j]).subtract(bd[i][j]).shiftLeft(B)).add(bd[i][j].shiftLeft(2*B));
}}
return res;
}
    

public static BigInteger[][]  multiply(BigInteger a[][],BigInteger b[][])
{
     int rowsInA = a.length;
       int columnsInA = a[0].length; // same as rows in B
       int columnsInB = b[0].length;
       BigInteger[][] c = new BigInteger[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < rowsInA; j++){ 
                   c[i][j] = a[i][j].multiply(b[i][j]);
           }}
       return c;
}
public static int getsize(BigInteger num[][]){
int control=0;
for(int i=0;i<num.length;i++)
{    
for(int j=0;j<num[0].length;j++)
{
control=num[i][j].bitLength();
}
}
return control;

    }
private static BigInteger[][] readMatrix(Scanner scanner, int rows, int cols) {
    BigInteger[][] matrix = new BigInteger[rows][cols];
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            matrix[row][col] = scanner.nextBigInteger();
        }
    }
    return matrix;
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
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Girish/Desktop/in1.txt")));
    try (Scanner scanner = new Scanner(input)) {
        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();

        if (cols1 != rows2) {
            throw new IllegalArgumentException("The matrices have incompatible sizes: " 
                    + cols1 + " columns vs. " + rows2 + "rows");
        }

        BigInteger[][] first = readMatrix(scanner, rows1, cols1);
        BigInteger[][] second = readMatrix(scanner, rows2, cols2);
        BigInteger[][] result = karatsuba(first,second);
        
String res=format(result);
        System.out.println("The result is:");
        System.out.println((res));

        try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/MatrixMultiplication.txt"))) {
            pw.write("\nMatrix 1 is the " + rows1 + "x" + cols1 + " matrix");
            pw.write(format(first));
            pw.write("\nMatrix 2 is the " + rows2 + "x" + cols2 + " matrix");
            pw.write(format(second));
            pw.write("\nTheir product is the " + rows1 + "x" + cols2 + " matrix");
            pw.newLine();
            pw.write((res));
            long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
        }
    
}

  catch(IOException ex) {
    ex.printStackTrace();
}
}}