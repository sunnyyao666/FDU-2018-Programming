package Queen;

import java.util.Arrays;

public class Queen穷举 {
    public static void main(String[] args) {
        int[][] queen = new int[8][8];
        int count = 0;
        for (int row1 = 0; row1 < queen.length; row1++)
            for (int row2 = 0; row2 < queen.length; row2++)
                for (int row3 = 0; row3 < queen.length; row3++)
                    for (int row4 = 0; row4 < queen.length; row4++)
                        for (int row5 = 0; row5 < queen.length; row5++)
                            for (int row6 = 0; row6 < queen.length; row6++)
                                for (int row7 = 0; row7 < queen.length; row7++)
                                    for (int row8 = 0; row8 < queen.length; row8++) {
                                        for (int i = 0; i < queen.length; i++)
                                            for (int j = 0; j < queen[i].length; j++)
                                                queen[i][j] = 0;
                                        queen[0][row1] = 2;
                                        judge(0, row1, queen);
                                        if (queen[1][row2] == 0) {
                                            queen[1][row2] = 2;
                                            judge(1, row2, queen);
                                            if (queen[2][row3] == 0) {
                                                queen[2][row3] = 2;
                                                judge(2, row3, queen);
                                                if (queen[3][row4] == 0) {
                                                    queen[3][row4] = 2;
                                                    judge(3, row4, queen);
                                                    if (queen[4][row5] == 0) {
                                                        queen[4][row5] = 2;
                                                        judge(4, row5, queen);
                                                        if (queen[5][row6] == 0) {
                                                            queen[5][row6] = 2;
                                                            judge(5, row6, queen);
                                                            if (queen[6][row7] == 0) {
                                                                queen[6][row7] = 2;
                                                                judge(6, row7, queen);
                                                                if (queen[7][row8] == 0) {
                                                                    queen[7][row8] = 2;
                                                                    count++;
                                                                    System.out.println(count);
                                                                    print(queen);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
    }

    public static void print(int[][] queen) {
        for (int i = 0; i < queen.length; i++) {
            for (int j = 0; j < queen[i].length; j++) {
                System.out.printf(queen[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void judge(int x, int y, int[][] queen) {
        for (int i = 0; i < queen.length; i++) {
            if (i != x)
                queen[i][y] = 1;
        }
        for (int j = 0; j < queen[0].length; j++) {
            if (j != y)
                queen[x][j] = 1;
        }
        for (int i = 1; x - i >= 0 && y - i >= 0; i++)
            queen[x - i][y - i] = 1;
        for (int i = 1; x + i < 8 && y + i < 8; i++)
            queen[x + i][y + i] = 1;
        for (int j = 1; x - j >= 0 && y + j < 8; j++)
            queen[x - j][y + j] = 1;
        for (int j = 1; x + j < 8 && y - j >= 0; j++)
            queen[x + j][y - j] = 1;
    }
}