package Lab4;

import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the year(>=2000)");
        int inputyear = input.nextInt();
        System.out.println("Please input the month");
        int inputmonth = input.nextInt();
        int year = 2000, month = 1, day = 1, sum = 0;
        while ((year != inputyear) | (month != inputmonth)) {
            int m;
            if ((month == 1) | (month == 3) | (month == 5) | (month == 7) | (month == 8) | (month == 10) | (month == 12))
                m = 31;
            else if ((month == 4) | (month == 6) | (month == 9) | (month == 11)) m = 30;
            else if ((year % 4 == 0) & (year % 100 != 0) | (year % 400 == 0)) m = 29;
            else m = 28;
            if (day < m) day++;
            else if (month < 12) {
                day = 1;
                month++;
            } else {
                day = 1;
                month = 1;
                year++;
            }
            sum++;
        }
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        int a = (6 + sum % 7) % 7;
        for (int i = 1; i <= a; i++) System.out.print("    ");
        int m;
        if ((month == 1) | (month == 3) | (month == 5) | (month == 7) | (month == 8) | (month == 10) | (month == 12))
            m = 31;
        else if ((month == 4) | (month == 6) | (month == 9) | (month == 11)) m = 30;
        else if ((year % 4 == 0) & (year % 100 != 0) | (year % 400 == 0)) m = 29;
        else m = 28;
        while (day <= m) {
            if (day <= 9) System.out.print(day + "   ");
            else System.out.print(day + "  ");
            a = (a + 1) % 7;
            day++;
            if (a == 0) System.out.println();
        }
    }
}
