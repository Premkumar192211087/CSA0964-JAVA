public class rtpattern {
    public static void triangle(int n)
    {
        int a,b;
        for(a=0;a<n;a++)
        {
            for(b=0;b<n;b++)
            {
                System.out.print("*");
            }
             System.out.println();
        }
    }
    public static void main(String [] args)
    {
        int k=5;
        triangle(k);
    }

}
