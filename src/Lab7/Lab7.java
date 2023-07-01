package Lab7;

import java.util.Scanner;

public class Lab7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] arr = new char[20][27];
        char[][] tetris1 = new char[3][5];
        char[][] tetris2 = new char[3][5];
        boolean[][] f1 = new boolean[20][27];
        for (int i = 0; i < 19; i++) {
            for (int j = 1; j < 26; j++) {
                arr[i][j] = ' ';
                f1[i][j] = false;
            }
            arr[i][0] = arr[i][26] = '|';
            f1[i][0] = f1[i][26] = true;
        }
        arr[19][0] = arr[19][26] = ' ';
        f1[19][0] = f1[19][26] = false;
        for (int i = 1; i < 26; i++) {
            arr[19][i] = '-';
            f1[19][i] = true;
        }
        stop:
        while (true) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 5; j++) tetris1[i][j] = ' ';
            tetris1[0][0] = tetris1[1][2] = tetris1[1][4] = tetris1[0][2] = '*';
            int hang = 2, lie = 5;
            int x = 1, y = 2;
            int a = 4 - (hang - x) + 1, b = 12;
            while (true) {
                for (int i = (a - x); i <= a + hang - x - 1; i++)
                    for (int j = b - y; j <= b + lie - y - 1; j++) {
                        if ((arr[i][j] == '*') & (tetris1[(int) (i - (a - x))][j - b + y] == '*')) break stop;
                        if (arr[i][j] == ' ') arr[i][j] = tetris1[(i - (a - x))][j - b + y];
                    }
                for (int i = 4; i < 20; i++) {
                    for (int j = 0; j < 27; j++)
                        System.out.print(arr[i][j]);
                    System.out.println();
                }
                for (int i = (a - x); i <= a + hang - x - 1; i++)
                    for (int j = b - y; j <= b + lie - y - 1; j++) if ((arr[i][j] == '*') & (!f1[i][j]))arr[i][j] = ' ';
                String letter = input.nextLine();
                boolean f;
                if (letter.equals("a")) {
                    f = true;
                    for (int i = 0; i <= hang - 1; i++)
                        for (int j = 0; j <= lie - 1; j = j + 2)
                                if ((arr[(int) (a + i - x)][b - y - 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                    f = false;
                                    break;
                                }
                    if (f) b = b - 2;
                }
                if (letter.equals("d")) {
                    f = true;
                    for (int i = 0; i <= hang - 1; i++)
                        for (int j = 0; j <= lie - 1; j = j + 2)
                                if ((arr[(int) (a + i - x)][b - y + 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                    f = false;
                                    break;
                                }
                    if (f) b = b + 2;
                }
                if (letter.equals("w")) {
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 5; j++) tetris2[i][j] = ' ';
                    for (int i = 0; i <= hang - 1; i++)
                        for (int j = 0; j <= lie - 1; j = j + 2)
                            tetris2[x - (y - j) / 2 + 1 - x][y + 2 * (x - i) + 2 - y] = tetris1[i][j];
                    int lie1 = -1;
                    f = true;
                    while (f) {
                        lie1++;
                        for (int i = 0; i < 3; i++) {
                            if (tetris2[i][lie1] == '*') {
                                f = false;
                                break;
                            }
                        }
                    }
                    int hang1 = -1;
                    f = true;
                    while (f) {
                        hang1++;
                        for (int i = 0; i < 5; i++) {
                            if (tetris2[hang1][i] == '*') {
                                f = false;
                                break;
                            }
                        }
                    }
                    int hang2, lie2, x1, y1;
                    lie2 = hang * 2 - 1;
                    hang2 = (lie + 1) / 2;
                    x1 = 1 - hang1;
                    y1 = 2 - lie1;
                    f = true;
                    for (int i = a - x1; i <= a + hang2 - x1 - 1; i++)
                        for (int j = b - y1; j <= b + lie2 - y1 - 1; j++) {
                            if ((arr[i][j] != ' ') & (tetris2[i - (a - x1) + hang1][j - b + y1 + lie1] != ' ')) {
                                f = false;
                                break;
                            }
                        }
                    if (f) {
                        hang = hang2;
                        lie = lie2;
                        for (int i = 0; i < 3; i++)
                            for (int j = 0; j < 5; j++) tetris1[i][j] = ' ';
                        for (int i = 0; i < hang; i++)
                            for (int j = 0; j < lie; j = j + 2) tetris1[i][j] = tetris2[i + hang1][j + lie1];
                        x = x1;
                        y = y1;
                    }
                    f = true;
                    for (int i = 0; i <= lie - 1; i++)
                        if ((arr[a + hang - x-1][b - y + i] != ' ') & (tetris1[hang - 1][i] != ' ')) {
                            f = false;
                            break;
                        }
                    if (!f) break;
                }
                if (letter.equals("s")) {
                    f = true;
                    for (int j=0;j<=hang-1;j++)
                    for (int i = 0; i <= lie - 1; i++)
                        if ((arr[a + j+1 - x][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                            f = false;
                            break;
                        }
                    if (f) a++;
                    else break;
                }
            }
            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                for (int j = b - y; j <= b + lie - y - 1; j = j + 2)
                    if (arr[i][j] == ' ') {
                        arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                        if (arr[i][j] == '*') f1[i][j] = true;
                    }
            for (int i = 4; i < 20; i++) {
               boolean f = true;
                for (int j = 2; j < 26; j = j + 2)
                    if (arr[i][j] != '*') {
                        f = false;
                        break;
                    }
                if (f)
                    for (int m = i; m >= 4; m--)
                        for (int j = 2; j < 27; j = j + 2) {
                            arr[m][j] = arr[m - 1][j];
                            f1[m][j] = f1[m - 1][j];
                        }
                }
        }
    }
}