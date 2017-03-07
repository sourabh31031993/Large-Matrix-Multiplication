package karasbig;

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
public class Karasbig {
public BigInteger karasalg(BigInteger x,BigInteger y)
{
int s1=getsize(x);
int s2=getsize(y);

int B=Math.max(s1, s2);
BigInteger ten=new BigInteger("10");
BigInteger m=(BigInteger)ten.pow(B);
BigInteger twomulb=(BigInteger)ten.pow(B*2);
BigInteger z=x.multiply(y);
if(B<10)
    return z;

B=(B/2)+(B%2);
BigInteger a=x.divide(m);
BigInteger b=y.divide(m);
BigInteger AmulM=a.multiply(m);
BigInteger BmulM=b.multiply(m);
BigInteger c=x.subtract(AmulM);
BigInteger d=y.subtract(BmulM);

BigInteger AplusC=a.add(c);
BigInteger BplusD=b.add(d);
BigInteger z2=karasalg(a,b);
BigInteger z0=karasalg(c,d);
BigInteger z1=karasalg(AplusC,BplusD);
BigInteger z1minusz2=z1.subtract(z2);
BigInteger z1minusz2minusz0=z1minusz2.subtract(z0);
BigInteger zmulm=z1minusz2minusz0.multiply(m);
BigInteger z2multwomulB=z2.multiply(twomulb);
BigInteger res1=zmulm.add(z2multwomulB);

return z0.add(res1);
}
public int getsize(BigInteger num){
int control=0;
BigInteger b=new BigInteger("10");
BigInteger zero=new BigInteger("0");
while(!num.equals(BigInteger.ZERO))
{
    control++;
    num=num.divide(b);
}
return control;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Girish/Desktop/karastuba.txt")));
         try (Scanner scanner = new Scanner(input)) {
        BigInteger n1=scanner.nextBigInteger();
        BigInteger n2=scanner.nextBigInteger();
        Karasbig k=new Karasbig();
        BigInteger result=k.karasalg(n1, n2);
        System.out.println("The result is:"+result);
        try (BufferedWriter pw = new BufferedWriter(new FileWriter("C:/Users/Girish/Desktop/Karasresult.txt"))) {
            pw.write("Karastuba's algorithm for faster multiplication:Welcome");
            pw.newLine();
            pw.write("First Number is:" + n1);
            pw.newLine();
            pw.write("Second Number is:" + n2);
            pw.newLine();
            pw.write("Result is:");
            pw.write(String.valueOf(result));
long starttime=System.currentTimeMillis();
            long endtime=System.currentTimeMillis();
            long timetaken=endtime-starttime;
            System.out.println(timetaken);
    }
        catch(IOException ex) {
    System.out.println("IOException:"+ex);
}
         }}}
