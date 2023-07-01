package Task1;

import java.util.Scanner;

public class Task1_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input:");
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.println("gcd(" + a + "," + b + ")="+ gcd(a, b));
    }

    public static int gcd(int a1,int b1){
        if (a1%b1==0) return(b1);
        else return(gcd(b1,a1%b1));
    }
}