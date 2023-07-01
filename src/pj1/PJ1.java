package pj1;

import java.io.*;
import java.util.Scanner;

public class PJ1 {
    static int vis[][] = new int[5][9];
    static int mx[] = {-1, 0, 1, 0};
    static int my[] = {0, 2, 0, -2};
    static int count;
    static char tetrisAll[][][] = new char[7][5][9];
    static int rank[] = new int[10];
    static String ranks[] = new String[10];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        stop1:
        while (true) {
            System.out.println(" --------------------------------------------------- ");
            System.out.println("|                       Tetris                      |");
            System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - |");
            System.out.println("|                        input                      |");
            System.out.println("|                    s: Start Game                  |");
            System.out.println("|                    q: Quit Game                   |");
            System.out.println("|                    r: Ranking List                |");
            System.out.println(" --------------------------------------------------- ");
            System.out.println("Please input your command('s' to start, 'q' to quit, 'r' to view the ranking list):");
            char[][] arr = new char[26][23];
            char[][] tetris1 = new char[5][9];
            char[][] tetris2 = new char[5][9];
            char[][] tetris3 = new char[5][9];
            boolean[][] f1 = new boolean[26][23];
            int sum = 0;
            int b = 0;
            double a = 0;
            int y = 0;
            double x = 0;
            boolean f4 = false;
            String letter = input.nextLine();
            while ((!letter.equals("q")) & (!letter.equals("s")) & (!letter.equals("r"))) {
                System.out.println("'" + letter + "'指令有误,请重新输入：");
                letter = input.nextLine();
            }
            if (letter.equals("q")) break;
            for (int i = 0; i < 10; i++) ranks[i] = "";
            try {
                FileInputStream fin = new FileInputStream("./rank.txt");
                for (int i = 0; i < 10; i++) {
                    int c = fin.read();
                    while (c != 13) {
                        ranks[i] = ranks[i] + (char) c;
                        c = fin.read();
                    }
                    rank[i] = fin.read();
                }
                fin.close();
            } catch (FileNotFoundException e) {
                for (int i = 0; i < 10; i++) ranks[i] = "null";
            } catch (IOException e) {
                for (int i = 0; i < 10; i++) ranks[i] = "null";
            }
            if (letter.equals("r")) {
                System.out.println("Ranking List");
                for (int i = 0; i < 10; i++) System.out.println((i + 1) + "、" + ranks[i] + ":" + rank[i]);
                System.out.println("Please input your command('s' to start, 'q' to quit):");
                letter = input.nextLine();
                while ((!letter.equals("q")) & (!letter.equals("s"))) {
                    System.out.println("'" + letter + "'指令有误,请重新输入：");
                    letter = input.nextLine();
                }
                if (letter.equals("q")) break;
            }
            System.out.println("请输入您的昵称：");
            String name = input.nextLine();
            boolean f5 = true;
            try {
                FileInputStream fin = new FileInputStream("./" + name + ".txt");
                for (int i = 0; i < 26; i++)
                    for (int j = 0; j <= 22; j++) arr[i][j] = (char) (fin.read());
                for (int i = 0; i < 26; i++)
                    for (int j = 0; j <= 22; j++)
                        if (fin.read() == 1) f1[i][j] = true;
                        else f1[i][j] = false;
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) tetris1[i][j] = (char) (fin.read());
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) tetris3[i][j] = (char) (fin.read());
                for (int k = 0; k < 7; k++)
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++)
                            tetrisAll[k][i][j] = (char) (fin.read());
                a = fin.read() / 2.0;
                b = fin.read();
                x = fin.read() / 2.0;
                y = fin.read();
                sum = fin.read();
                if (fin.read() == 1) f4 = true;
                else f4 = false;
                fin.close();
            } catch (FileNotFoundException e) {
                f5 = false;
            } catch (IOException e) {
                f5 = false;
            }
            if (f5) {
                File file = new File("./" + name + ".txt");
                file.delete();
                System.out.println(name + "玩家有未完成的游戏存档，是否继续？(y/n)");
                letter = input.nextLine();
                while ((!letter.equals("y")) & (!letter.equals("n"))) {
                    System.out.println("'" + letter + "'指令有误,请重新输入：");
                    letter = input.nextLine();
                }
                if (letter.equals("n")) f5 = false;
            } else System.out.println(name + "玩家没有存档，将开始新的游戏。");

            if (!f5) {
                for (int i = 0; i < 26; i++) {
                    for (int j = 1; j < 22; j++) {
                        arr[i][j] = ' ';
                        f1[i][j] = false;
                    }
                    arr[i][0] = arr[i][22] = '|';
                    f1[i][0] = f1[i][22] = true;
                }
                arr[25][0] = arr[25][22] = ' ';
                for (int i = 1; i < 22; i++) {
                    arr[25][i] = '-';
                    f1[25][i] = true;
                }
                System.out.println("方块是否能穿过左右边界？(y/n)");
                letter = input.nextLine();
                while ((!letter.equals("y")) & (!letter.equals("n"))) {
                    System.out.println("'" + letter + "'指令有误,请重新输入：");
                    letter = input.nextLine();
                }
                f4 = false;
                if (letter.equals("y")) f4 = true;
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++)
                        for (int k = 0; k < 7; k++) tetrisAll[k][i][j] = ' ';
                tetrisAll[0][0][0] = tetrisAll[0][1][0] = tetrisAll[0][1][2] = tetrisAll[0][1][4] = '*';
                tetrisAll[1][0][4] = tetrisAll[1][1][0] = tetrisAll[1][1][2] = tetrisAll[1][1][4] = '*';
                tetrisAll[2][0][2] = tetrisAll[2][1][0] = tetrisAll[2][1][2] = tetrisAll[2][0][4] = '*';
                tetrisAll[3][0][2] = tetrisAll[3][1][0] = tetrisAll[3][1][2] = tetrisAll[3][1][4] = '*';
                tetrisAll[4][0][0] = tetrisAll[4][1][2] = tetrisAll[4][1][4] = tetrisAll[4][0][2] = '*';
                tetrisAll[5][0][0] = tetrisAll[5][0][2] = tetrisAll[5][0][4] = tetrisAll[5][0][6] = '*';
                tetrisAll[6][0][0] = tetrisAll[6][0][2] = tetrisAll[6][1][2] = tetrisAll[6][1][0] = '*';
                System.out.println("方块是否随机？(y/n)");
                letter = input.nextLine();
                while ((!letter.equals("y")) & (!letter.equals("n"))) {
                    System.out.println("'" + letter + "'指令有误,请重新输入：");
                    letter = input.nextLine();
                }
                if (letter.equals("y")) {
                    System.out.println("随机方块为：");
                    for (int k = 0; k < 7; k++) {
                        int hang1, lie1;
                        while (true) {
                            int hang = (int) (Math.random() * 5);
                            int lie = (int) (Math.random() * 5) * 2;
                            if ((hang >= 3) & (lie >= 6)) continue;
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 9; j++) tetrisAll[k][i][j] = ' ';
                            int c = 0;
                            for (int i = 0; i <= hang; i++)
                                for (int j = 0; j <= lie; j = j + 2)
                                    if ((int) (Math.random() * 2) == 1) {
                                        tetrisAll[k][i][j] = '*';
                                        c++;
                                    }
                            if (tetrisAll[k][0][0] != '*') {
                                tetrisAll[k][0][0] = '*';
                                c++;
                            }
                            count = 0;
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 9; j++) vis[i][j] = 0;

                            start(tetrisAll[k], 0, 0);


                            if (c == count) {
                                boolean f = false;
                                if (k == 0) f = true;
                                for (int k1 = 0; k1 < k; k1++) {
                                    f = false;
                                    for (int i = 0; i < 5; i++)
                                        for (int j = 0; j < 9; j++)
                                            if (tetrisAll[k][i][j] != tetrisAll[k1][i][j]) {
                                                f = true;
                                                break;
                                            }
                                    if (!f) break;
                                }
                                if (f) break;
                            }
                        }
                        boolean f = true;
                        lie1 = getLie(tetrisAll[k]);
                        hang1 = getHang(tetrisAll[k]);
                        System.out.println((k + 1) + " ");
                        for (int i = 0; i < hang1; i++) {
                            for (int j = 0; j < lie1; j = j + 1) System.out.print(tetrisAll[k][i][j]);
                            System.out.println();
                        }
                        System.out.println();
                    }
                }
                int k = (int) (Math.random() * 7);
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) tetris3[i][j] = tetrisAll[k][i][j];
            }
            int lie3 = getLie(tetris3);
            int hang3 = getHang(tetris3);
            boolean f;
            boolean f3 = false;
            stop:
            while (!letter.equals("q")) {
                int lie, hang;
                boolean f2 = false;
                if (!f5) {
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) tetris1[i][j] = tetris3[i][j];
                    int k = (int) (Math.random() * 7);
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) tetris3[i][j] = tetrisAll[k][i][j];
                    lie = lie3;
                    hang = hang3;
                    lie3 = getLie(tetris3);
                    hang3 = getHang(tetris3);
                    if ((lie % 4 == 1) & (hang % 2 == 0)) {
                        y = (lie - 1) / 2;
                        x = hang / 2;
                    }
                    if ((lie % 4 == 3) & (hang % 2 == 1)) {
                        x = (hang - 1) / 2;
                        y = (lie - 2) / 2;
                    }
                    if ((lie % 4 == 1) & (hang % 2 == 1)) {
                        y = (lie - 1) / 2;
                        x = hang / 2;
                    }
                    if ((lie % 4 == 3) & (hang % 2 == 0)) {
                        x = (hang - 1) / 2.0;
                        y = (lie - 1) / 2;
                        f2 = true;
                    }
                    if (!f2) {
                        b = 10;
                        a = 4 - (hang - x) + 1;
                    }
                    if (f2) {
                        b = 11;
                        a = 4 - (hang - x) + 1;
                    }
                } else {
                    lie = getLie(tetris1);
                    hang = getHang(tetris1);
                    if ((lie % 4 == 3) & (hang % 2 == 0)) f2 = true;
                    f5 = false;
                }

                System.out.println();
                while (!letter.equals("q")) {
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (tetris1[(int) (i - (a - x))][j - b + y] == '*')) {
                                    f3 = true;
                                    break stop;
                                }
                                if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                            }
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = b - y + 20; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                            }
                    }
                    int x2 = 30;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int j = b - y; j <= b + lie - y - 1; j++) {
                            int x1 = 0;
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                            int i = (int) (a - x);
                            while (true) {
                                if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                i++;
                            }
                            i = i - 1;
                            if (i - x1 < x2) x2 = i - x1;
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22) {
                            for (int j = b - y; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                        if (b - y <= 0) {
                            for (int j = 2; j <= b + lie - y - 1; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = b - y + 20; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++)
                                if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = b - y + 20; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                    }
                    System.out.println("下一个方块是：");
                    for (int i = 0; i < hang3; i++) {
                        for (int j = 0; j < lie3; j++) System.out.print(tetris3[i][j]);
                        System.out.println();
                    }
                    System.out.println();
                    for (int i = 4; i < 26; i++) {
                        for (int j = 0; j < 23; j++) System.out.print(arr[i][j]);
                        System.out.println();
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (!f1[i][j])) arr[i][j] = ' ';
                                if (arr[i + x2][j] == '+') arr[i + x2][j] = ' ';
                            }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) arr[i][j] = ' ';
                                    if (arr[i + x2][j] == '+') arr[i + x2][j] = ' ';
                                }
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) arr[i][j] = ' ';
                                    if (arr[i + x2][j] == '+') arr[i + x2][j] = ' ';
                                }
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) arr[i][j] = ' ';
                                    if (arr[i + x2][j] == '+') arr[i + x2][j] = ' ';
                                }
                                for (int j = b - y + 20; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) arr[i][j] = ' ';
                                    if (arr[i + x2][j] == '+') arr[i + x2][j] = ' ';
                                }
                            }
                    }
                    System.out.println("你当前的分数为: " + sum);
                    System.out.println();
                    System.out.println("w: 旋转 s: 维持");
                    System.out.println("a: 左移 d: 右移");
                    System.out.println("x: 下坠 q: 退出");
                    System.out.println("请输入你的指令:");
                    letter = input.nextLine();
                    while ((!letter.equals("q")) & (!letter.equals("s")) & (!letter.equals("x")) & (!letter.equals("a")) & (!letter.equals("d")) & (!letter.equals("w"))) {
                        System.out.println("'" + letter + "'指令有误,请重新输入：");
                        letter = input.nextLine();
                    }
                    if (letter.equals("q")) break stop;
                    if (letter.equals("a")) {
                        f = true;
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2) {
                                if ((!f4) | ((f4) & (b - y + j > 2)))
                                    if ((arr[(int) (a + i - x)][b - y - 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                if ((f4) & (b - y + j <= 2))
                                    if ((arr[(int) (a + i - x)][b - y - 2 + 20 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                        if (f) b = b - 2;
                        if (b <= 0) b = b + 20;
                    }
                    if (letter.equals("d")) {
                        f = true;
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2) {
                                if ((!f4) | ((f4) & (b + j - y <= 18)))
                                    if ((arr[(int) (a + i - x)][b - y + 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                if ((f4) & (b + j - y > 18))
                                    if ((arr[(int) (a + i - x)][b + j - y + 2 - 20] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                        if (f) b = b + 2;
                        if (b >= 22) b = b - 20;
                    }
                    if (letter.equals("w")) {
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) tetris2[i][j] = ' ';
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2)
                                tetris2[(int) (x - (y - j) / 2.0 + 2 - x)][(int) (y + 2 * (x - i) + 4 - y)] = tetris1[i][j];
                        int lie1 = -1;
                        f = true;
                        while (f) {
                            lie1++;
                            for (int i = 0; i < 5; i++)
                                if (tetris2[i][lie1] == '*') {
                                    f = false;
                                    break;
                                }
                        }
                        int hang1 = -1;
                        f = true;
                        while (f) {
                            hang1++;
                            for (int i = 0; i < 9; i++)
                                if (tetris2[hang1][i] == '*') {
                                    f = false;
                                    break;
                                }
                        }
                        int hang2, lie2, y1;
                        double x1;
                        lie2 = hang * 2 - 1;
                        hang2 = (lie + 1) / 2;
                        if (f2) {
                            x1 = 1.5 - hang1;
                            y1 = 4 - lie1;
                        } else {
                            x1 = 2 - hang1;
                            y1 = 4 - lie1;
                        }
                        f = true;
                        if (!f4)
                            if ((b + lie2 - y1 - 1 <= 22) & (b - y1 >= 0))
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++)
                                    for (int j = b - y1; j <= b + lie2 - y1 - 1; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                        if ((!f4) & ((b + lie2 - y1 - 1 > 22) | (b - y1 < 0))) f = false;
                        if (f4) {
                            if (b + lie2 - y1 - 1 >= 22)
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++) {
                                    for (int j = b - y1; j <= 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                    for (int j = 2; j <= b + lie2 - y1 - 1 - 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1 + 20] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                }
                            if (b - y1 <= 0)
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++) {
                                    for (int j = b - y1 + 20; j <= 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1 - 20] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                    for (int j = 2; j <= b + lie2 - y1 - 1; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                }
                        }
                        if (f) {
                            hang = hang2;
                            lie = lie2;
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 9; j++) tetris1[i][j] = ' ';
                            for (int i = 0; i < hang; i++)
                                for (int j = 0; j < lie; j = j + 2) tetris1[i][j] = tetris2[i + hang1][j + lie1];
                            x = x1;
                            y = y1;
                        }
                    }
                    if (letter.equals("x")) {
                        a = a + x2;
                        break;
                    }
                    f = true;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2)
                                if ((arr[(int) (a + i + 1 - x)][b - y + j] != ' ') & (tetris1[i][j] != ' ')) {
                                    f = false;
                                    break;
                                }
                    if (f4) for (int j = 0; j <= hang - 1; j++) {
                        if (b + lie - y - 1 >= 22) {
                            for (int i = 0; i <= 20 - b + y; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                            for (int i = 22 - b + y; i <= lie - 1; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i - 20] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                        }
                        if (b - y <= 0) {
                            for (int i = 2 - b + y; i <= lie - 1; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                            for (int i = 0; i <= y - b; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i + 20] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                        }
                    }
                    if (f) a++;
                    else break;
                }
                int t = 0;
                if ((b + lie - y - 1 < 22) & (b - y > 0))
                    for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                        for (int j = b - y; j <= b + lie - y - 1; j = j + 2)
                            if (arr[i][j] == ' ') {
                                arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                if (arr[i][j] == '*') f1[i][j] = true;
                            }
                if (f4) {
                    if (b + lie - y - 1 >= 22)
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                            for (int j = b - y; j <= 20; j = j + 2)
                                if (arr[i][j] == ' ') {
                                    arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                    if (arr[i][j] == '*') f1[i][j] = true;
                                }
                            for (int j = 2; j <= b + lie - y - 1 - 20; j = j + 2)
                                if (arr[i][j] == ' ') {
                                    arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                                    if (arr[i][j] == '*') f1[i][j] = true;
                                }
                        }
                    if (b - y <= 0)
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                            for (int j = b - y + 20; j <= 20; j = j + 2)
                                if (arr[i][j] == ' ') {
                                    arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                                    if (arr[i][j] == '*') f1[i][j] = true;
                                }
                            for (int j = 2; j <= b + lie - y - 1; j = j + 2)
                                if (arr[i][j] == ' ') {
                                    arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                    if (arr[i][j] == '*') f1[i][j] = true;
                                }
                        }
                }
                for (int i = 4; i < 26; i++) {
                    f = true;
                    for (int j = 2; j < 22; j = j + 2)
                        if (arr[i][j] != '*') {
                            f = false;
                            break;
                        }
                    if (f) {
                        for (int m = i; m >= 4; m--)
                            for (int j = 2; j < 23; j = j + 2) {
                                arr[m][j] = arr[m - 1][j];
                                f1[m][j] = f1[m - 1][j];
                            }
                        t++;
                    }
                }
                sum = (int) (sum + Math.pow(2, t - 1) * t * 10);
            }
            if (f3) {
                System.out.println("游戏结束，你当前的分数：" + sum);
                f = false;
                int i1 = -1;
                for (int i = 0; i < 10; i++)
                    if (ranks[i].equals(name)) {
                        f = true;
                        i1 = i;
                        break;
                    }
                if (!f)
                    if (sum <= rank[9]) System.out.println("很遗憾，你未上榜，再接再厉。");
                    else {
                        int i = 0;
                        while (rank[i] >= sum) i++;
                        for (int j = 9; j > i; j--) {
                            rank[j] = rank[j - 1];
                            ranks[j] = ranks[j - 1];
                        }
                        rank[i] = sum;
                        ranks[i] = name;
                        System.out.println("恭喜你，分数排行第" + (i + 1) + "。");
                    }
                if (f) {
                    if (rank[i1] >= sum) System.out.println("你并未突破自己的最高排名。");
                    else {
                        int i = 0;
                        while (rank[i] >= sum) i++;
                        for (int j = i1; j > i; j--) {
                            rank[j] = rank[j - 1];
                            ranks[j] = ranks[j - 1];
                        }
                        rank[i] = sum;
                        ranks[i] = name;
                        System.out.println("恭喜你，刷新了自己的记录，当前分数排行第" + (i + 1) + "。");
                    }
                }
                try {
                    FileOutputStream fout = new FileOutputStream("./rank.txt");
                    for (int i = 0; i < 10; i++) {
                        char[] c = ranks[i].toCharArray();
                        for (int j = 0; j < c.length; j++) fout.write(c[j]);
                        fout.write(13);
                        fout.write(rank[i]);
                    }
                    fout.close();
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }
                System.out.println("是否要再来一局？(y/n)");
                letter = input.nextLine();
                while ((!letter.equals("y")) & (!letter.equals("n"))) {
                    System.out.println("'" + letter + "'指令有误,请重新输入：");
                    letter = input.nextLine();
                }
                if (letter.equals("y")) continue stop1;
                else break stop1;
            } else {
                try {
                    FileOutputStream fout = new FileOutputStream("./" + name + ".txt");
                    for (int i = 0; i < 26; i++)
                        for (int j = 0; j <= 22; j++) fout.write(arr[i][j]);
                    for (int i = 0; i < 26; i++)
                        for (int j = 0; j <= 22; j++)
                            if (f1[i][j]) fout.write(1);
                            else fout.write(0);
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) fout.write(tetris1[i][j]);
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) fout.write(tetris3[i][j]);
                    for (int k = 0; k < 7; k++)
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++)
                                fout.write(tetrisAll[k][i][j]);
                    fout.write((int) (2 * a));
                    fout.write(b);
                    fout.write((int) (2 * x));
                    fout.write(y);
                    fout.write(sum);
                    if (f4) fout.write(1);
                    else fout.write(0);
                    fout.close();
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }
            }
        }
        System.out.println("哼，不玩就不玩");
    }

    public static void start(char tetris3[][], int j, int k) {
        if (j < 0 || j > 4 || k < 0 || k > 8) return;
        if (tetris3[j][k] == ' ') return;
        if (vis[j][k] == 1) return;
        if (tetris3[j][k] == '*' && vis[j][k] == 0) {
            count++;
            vis[j][k] = 1;
        }
        for (int i = 0; i < 4; i++) {
            j += mx[i];
            k += my[i];
            start(tetris3, j, k);
            j -= mx[i];
            k -= my[i];
        }
    }

    public static int getHang(char a[][]) {
        int hang = 5;
        boolean f = true;
        while (f) {
            hang--;
            for (int i = 0; i < 9; i++)
                if (a[hang][i] == '*') {
                    f = false;
                    break;
                }
        }
        return hang + 1;
    }

    public static int getLie(char a[][]) {
        int lie = 9;
        boolean f = true;
        while (f) {
            lie--;
            for (int i = 0; i < 5; i++)
                if (a[i][lie] == '*') {
                    f = false;
                    break;
                }
        }
        return lie + 1;
    }
}