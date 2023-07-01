package Task2;

import java.util.Scanner;

public class Task2_2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("输入数字个数：");
        int n = input.nextInt();
        int[] a = new int[n];
        System.out.println("输入数字：");
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
        quickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) System.out.print(a[i] + " ");
        System.out.println();
    }


    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) return;
        int i = low, j = high;
        int key = arr[low];
        while (i < j) {
            while ((i < j) & (arr[j] > key)) j--;
            while ((i < j) & (arr[i] <= key)) i++;
            if (i < j) {
                int c = arr[i];
                arr[i] = arr[j];
                arr[j] = c;
            }
        }
        int c = arr[i];
        arr[i] = arr[low];
        arr[low] = c;
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }
}