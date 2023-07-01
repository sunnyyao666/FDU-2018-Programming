package Lab3;

import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a, b;
        int lie = 5, hang = 2;
        int y = (lie - 1) / 2;
        int x = hang / 2;
        int w = 0;
        b = 11;
        a = 0 - (hang - x) + 1;
        System.out.println("Start...");
        while (true) {
            if (w == 0) {
                for (int i = 0; i < a - 1; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                if (a > 0) {
                    System.out.print('|');
                    for (int j = 0; j < b - 2; j++) {
                        System.out.print(' ');
                    }
                    System.out.print("* *");
                    for (int j = b + 1; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.print('|');
                for (int j = 0; j < b; j++) {
                    System.out.print(' ');
                }
                System.out.print("* *");
                for (int j = b + 3; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                for (int i = a + 1; i < 15; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.println(" ------------------------- ");
                System.out.print("Enter:");
                char letter = input.next().charAt(0);
                if (letter == 's') a++;
                if ((letter == 'a') & (b >= 5)) b = b - 2;
                if ((letter == 'd') & (b <= 19)) b = b + 2;
                if (letter == 'w') {
                    if (a == 14) break;
                    else w = (w + 1) % 4;
                }
                if (a >= 15) break;
            }
            if (w == 1) {
                for (int i = 0; i < a - 1; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                if (a > 0) {
                    System.out.print('|');
                    for (int j = 0; j < b + 2; j++) {
                        System.out.print(' ');
                    }
                    System.out.print("*");
                    for (int j = b + 3; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.print('|');
                for (int j = 0; j < b; j++) {
                    System.out.print(' ');
                }
                System.out.print("* *");
                for (int j = b + 3; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                System.out.print('|');
                for (int j = 0; j < b; j++) {
                    System.out.print(' ');
                }
                System.out.print("*");
                for (int j = b + 1; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                for (int i = a + 2; i < 15; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.println(" ------------------------- ");
                System.out.print("Enter:");
                char letter = input.next().charAt(0);
                if (letter == 's') a++;
                if ((letter == 'a') & (b >= 3)) b = b - 2;
                if ((letter == 'd') & (b <= 19)) b = b + 2;
                if ((letter == 'w') & (b != 1)) w = (w + 1) % 4;
                if (a >= 14) break;
            }
            if (w == 2) {
                for (int i = 0; i < a; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                    System.out.print('|');
                    for (int j = 0; j < b-2; j++) {
                        System.out.print(' ');
                    }
                    System.out.print("* *");
                    for (int j = b + 1; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                System.out.print('|');
                for (int j = 0; j < b ; j++) {
                    System.out.print(' ');
                }
                System.out.print("* *");
                for (int j = b + 3; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                for (int i = a + 2; i < 15; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.println(" ------------------------- ");
                System.out.print("Enter:");
                char letter = input.next().charAt(0);
                if (letter == 's') a++;
                if ((letter == 'a') & (b >= 5)) b = b - 2;
                if ((letter == 'd') & (b <= 19)) b = b + 2;
                if (letter == 'w') w = (w + 1) % 4;
                if (a >= 14) break;
            }
            if (w == 3) {
                for (int i = 0; i < a - 1; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                if (a > 0) {
                    System.out.print('|');
                    for (int j = 0; j < b ; j++) {
                        System.out.print(' ');
                    }
                    System.out.print("*");
                    for (int j = b +1; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.print('|');
                for (int j = 0; j < b - 2; j++) {
                    System.out.print(' ');
                }
                System.out.print("* *");
                for (int j = b + 1; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                System.out.print('|');
                for (int j = 0; j < b-2; j++) {
                    System.out.print(' ');
                }
                System.out.print("*");
                for (int j = b -1; j < 25; j++) {
                    System.out.print(' ');
                }
                System.out.println('|');
                for (int i = a + 2; i < 15; i++) {
                    System.out.print('|');
                    for (int j = 0; j < 25; j++) {
                        System.out.print(' ');
                    }
                    System.out.println('|');
                }
                System.out.println(" ------------------------- ");
                System.out.print("Enter:");
                char letter = input.next().charAt(0);
                if (letter == 's') a++;
                if ((letter == 'a') & (b >= 5)) b = b - 2;
                if ((letter == 'd') & (b <= 21)) b = b + 2;
                if ((letter == 'w') & (b < 22)) w = (w + 1) % 4;
                if (a >= 14) break;
            }
        }
    }
}