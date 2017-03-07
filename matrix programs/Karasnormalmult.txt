package karasmul;

/**
 *
 * @author Girish
 */

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Karasmul {
public static double[][] kmm(double a[][],double b[][])
{
int c,d,k;
int m=a.length; 
int mcol=a[0].length;
int p=b.length;
int pcol=b[0].length;
int s1=getsize(a);
int s2=getsize(b);
int base=Math.max(s1, s2);
double n=(long)Math.pow(10, base);
double multiplyres[][]=multiply(a,b);
if(base<10)
    return multiplyres;
double split1[][]=new double[m][pcol];
double split2[][]=new double[m][pcol];
double split3[][]=new double[m][pcol];
double split4[][]=new double[m][pcol];
double z3[][]=new double[m][pcol];
double z4[][]=new double[m][pcol];
double result[][]=new double[m][pcol];
base=(base/2)+(base%2);
for(int i=0;i<m;i++)
{
    double sub[]=a[i];
    
for(int j=0;j<sub.length;j++)
{
    split1[i][j]=a[i][j]/n;
}
}
for(int i=0;i<m;i++)
{
    double sub[]=b[i];
for(int j=0;j<sub.length;j++)
{
    split2[i][j]=b[i][j]/n;
}
}
for(int i=0;i<m;i++)
{
    double sub[]=a[i];
for(int j=0;j<sub.length;j++)
{
    split3[i][j]=a[i][j]-((a[i][j]/n)*n);
}
}
for(int i=0;i<m;i++)
{
    double sub[]=b[i];
for(int j=0;j<sub.length;j++)
{
    split4[i][j]=b[i][j]-((b[i][j]/n)*n);
}
}
for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{
 z3[i][j]=split1[i][j]+split3[i][j];
}
}
for(int i=0;i<m;i++)
{
for(int j=0;j<pcol;j++)
{
 z4[i][j]=split2[i][j]+split4[i][j];
}
}
double z2[][]=kmm(split1,split2);
double z0[][]=kmm(split3,split4);
double z1[][]=kmm(z3,z4);
 
 for ( c = 0 ; c < m ; c++ )
         {
            for ( d = 0 ; d < pcol ; d++ )
            {     
              
                 result[c][d]=(long)(z0[c][d]+((z1[c][d]-z2[c][d]-z0[c][d])*n)+(z2[c][d]*(long)Math.pow(10, 2*base))); 
               }
            }
 
return multiplyres; 
}


public static double[][]  multiply(double a[][],double b[][])
{
     int rowsInA = a.length;
       int columnsInA = a[0].length; // same as rows in B
       int columnsInB = b[0].length;
       double[][] c = new double[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = (long)(c[i][j] + a[i][k] * b[k][j]);
               }
           }
       }
       return c;
}
public static int getsize(double num[][]){
int control=0;
for(int i=0;i<num.length;i++)
{    
for(int j=0;j<num[0].length;j++)
{
control=(int)Math.log10(num[i][j])+1;    
}
}
return control;

    }
private static double[][] readMatrix(Scanner scanner, int rows, int cols) {
    double[][] matrix = new double[rows][cols];
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            matrix[row][col] = scanner.nextDouble();
        }
    }
    return matrix;
}
private static String format(double[][] matrix) {
    StringBuilder sb = new StringBuilder();
    DecimalFormat decimalFormat=new DecimalFormat("#.#");
    for (int row = 0; row < matrix.length; row++) {
        for (int col = 0; col < matrix[row].length; col++) {
            sb.append(decimalFormat.format(matrix[row][col]));
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

        double[][] first = readMatrix(scanner, rows1, cols1);
        double[][] second = readMatrix(scanner, rows2, cols2);
DecimalFormat decimalFormat=new DecimalFormat("#.#");
        double[][] result = kmm(first,second);
        
String res=format(result);
        System.out.println("The result is:");
        System.out.println((res));

        try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/MatrixMultiplication.txt"))) {
            pw.write("\nMatrix 1 is the " + rows1 + "x" + cols1 + " matrix");
            pw.write(format(first));
            pw.write("\nMatrix 2 is the " + rows2 + "x" + cols2 + " matrix");
            pw.write(format(second));
            pw.write("\nTheir product is the " + rows1 + "x" + cols2 + " matrix");
            pw.write((res));
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
    
