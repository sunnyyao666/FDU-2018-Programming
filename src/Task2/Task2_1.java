package Task2;

import java.util.Scanner;

public class Task2_1 {
    public static void main(String[] args) {
        int[] a = new int[365];
        Scanner input = new Scanner(System.in);
        System.out.println("输入人数：");
        int n = input.nextInt();
        System.out.println("输入循环次数：");
        int s = input.nextInt();
        double m = 0, m1 = 0;
        for (int j = 1; j <= s; j++) {
            for (int i = 0; i < 365; i++) a[i] = 0;
            boolean f = false;
            for (int i = 1; i <= n; i++) {
                int x=(int) (Math.random() * 365);
                a[x]++;
                if (a[x]>1){
                    f=true;
                    break;
                }
            }
            if (f) m++;
            m1++;
        }
        System.out.println("至少有两人生日相同的概率为：" + m / m1);
    }
}
