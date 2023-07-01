package Lab6;

import java.util.Scanner;

public class Lab6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input:");
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
        System.out.println();
        System.out.println("Output:");
        for (int i = n - 1; i >= 0; i--) {
            for (int j = (i - 1) / 2; j >= 0; j--) {
                if (j * 2 + 2 <= i) {
                    if ((a[j * 2 + 1] <= a[j * 2 + 2]) & (a[j * 2 + 1] <= a[j])) {
                        int c = a[j * 2 + 1];
                        a[j * 2 + 1] = a[j];
                        a[j] = c;
                    }
                    if ((a[j * 2 + 2] <= a[j * 2 + 1]) & (a[j * 2 + 2] <= a[j])) {
                        int c = a[j * 2 + 2];
                        a[j * 2 + 2] = a[j];
                        a[j] = c;
                    }
                }
                if (j * 2 + 2 > i) if (a[j * 2 + 1] <= a[j]) {
                    int c = a[j * 2 + 1];
                    a[j * 2 + 1] = a[j];
                    a[j] = c;
                }
            }
            System.out.print(a[0] + " ");
            a[0] = a[i];
        }
        System.out.println();
    }
}
