import java.util.Scanner;
public class EvenorOdd
{
  public static void main(String arr[])
  {
      Scanner Scanner= new Scanner(System.in);
      System.out.print("Enter a number: ");
      int n=Scanner.nextInt();
      if (n%2==0)
          System.out.println( n + " is even");
      else
          System.out.println( n + " is odd");
      Scanner.close();
  }
}