package Lab8;
import java.util.Scanner;
public class Lab8
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] a = new char[22][22];
        char[][] b = new char[22][22];
        for (int i = 1; i < 21; i++)
            for (int j = 1; j < 21; j++) a[i][j] = '-';
        for (int j = 0; j < 22; j++) a[0][j] = '.';
        for (int j = 0; j < 22; j++) a[21][j] = '.';
        for (int j = 1; j < 21; j++) a[j][0] = '.';
        for (int j = 1; j < 21; j++) a[j][21] = '.';
        a[1][2]=a[2][3]=a[3][1]=a[3][2]=a[3][3]='*';
        while (true) {
            String letter = input.nextLine();
            for (int i = 1; i < 21; i++) {
                for (int j = 1; j < 21; j++) System.out.print(a[i][j]);
                System.out.println();
            }
            for (int i = 1; i < 21; i++) {
                for (int j = 1; j < 21; j++) {
                 int t=0;
                 if (a[i-1][j-1]=='*')t++;
                 if (a[i-1][j]=='*')t++;
                 if (a[i-1][j+1]=='*')t++;
                    if (a[i][j-1]=='*')t++;
                    if (a[i][j+1]=='*')t++;
                    if (a[i+1][j-1]=='*')t++;
                    if (a[i+1][j]=='*')t++;
                    if (a[i+1][j+1]=='*')t++;
                    if ((t<2)|(t>3))b[i][j]='-';
                    if (t==2)b[i][j]=a[i][j];
                    if (t==3)b[i][j]='*';
                }
            }
            for (int i = 1; i < 21; i++)
                for (int j = 1; j < 21; j++) a[i][j]=b[i][j];

        }
    }
}