package Queen;

import java.util.Arrays;
import java.util.Scanner;

public class Queen回溯 {
    static int s = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("输入皇后个数：");
        int n = input.nextInt();
        boolean a[][] = new boolean[n][n];
        boolean[] h = new boolean[n];
        boolean[] x = new boolean[2 * n];
        boolean[] y = new boolean[2 * n];
        Arrays.fill(h, true);
        Arrays.fill(x, true);
        Arrays.fill(y, true);
        run(0, n, a, h, x, y);
        System.out.println(s);
    }

    public static void run(int i, int m, boolean a1[][], boolean h1[], boolean x1[], boolean y1[]) {
        for (int j = 0; j < m; j++) {
            if ((h1[j]) & (x1[i - j + m]) & (y1[i + j])) {
                h1[j] = x1[i - j + m] = y1[i + j] = false;
                a1[i][j] = true;
                if (i < m - 1) run(i + 1, m, a1, h1, x1, y1);
                else {
                    for (int k = 0; k < m; k++) {
                        for (int z = 0; z < m; z++)
                            if (a1[k][z]) System.out.print("* ");
                            else System.out.print("- ");
                        System.out.println();
                    }
                    System.out.println();
                    s++;
                }
                h1[j] = x1[i - j + m] = y1[i + j] = true;
                a1[i][j] = false;
            }
        }
    }
}