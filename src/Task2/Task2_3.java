package Task2;

import java.util.Scanner;

public class Task2_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("输入最高次项次数：");
        int n = input.nextInt();
        double[] a = new double[n + 1];
        for (int i = n; i >= 0; i--) {
            System.out.println("输入" + i + "次项系数：");
            a[i] = input.nextDouble();
        }
        System.out.println("输入求解范围：");
        double x = input.nextDouble(), y = input.nextDouble();
        double dx = 0.0000001;
        boolean f = false;
        while (x <= y) {
            double s = a[0];
            for (int i = 1; i <= n; i++) s = s + a[i] * Math.pow(x, i);
            if (Math.abs(s) <= 0.0000001) {
                System.out.print("x=" + (int) (x * 1000) / 1000.0 + " ");
                x = x + 0.001;
                f = true;
            }
            x = x + dx;
        }
        if (f) System.out.println("为方程的解");
        else System.out.println("方程在范围内无解");
    }
}
