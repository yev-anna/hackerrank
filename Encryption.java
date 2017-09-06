package hackerrank;

import java.util.Scanner;

public class Encryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        System.out.println(encrypt(text));
    }

    private static void encrypt_(String text) {
        double L = (double)text.length();
        int rows = (int)Math.floor(Math.sqrt(L));
        int columns = (int)Math.ceil(Math.sqrt(L));
        if (rows*columns < L) {
            rows++;
        }

        //System.out.println("length = " + text.length() + " rows = " + rows + " columns = " + columns);

        char[][] textMatrix = new char[rows][columns];
        int charPoint = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (charPoint == text.length())
                    break;
                textMatrix[i][j] = text.charAt(charPoint);
                charPoint++;
            }
        }

        StringBuilder newText = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                newText.append(textMatrix[i][j]);
            }
            if (j!=columns-1) // not the last word
                newText.append(" ");
        }
        System.out.println(newText);
    }

    private static String encrypt(String text) {
        double L = (double) text.length();
        int rows = (int) Math.floor(Math.sqrt(L));
        int columns = (int) Math.ceil(Math.sqrt(L));
        if (rows * columns < L) {
            rows++;
        }

        StringBuilder newText = new StringBuilder();
        int textPoint = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (textPoint == L)
                    break;
                newText.append(text.charAt(j*columns+i));
                textPoint++;
            }
            newText.append(" ");
        }
        return new String(newText);
    }

}
