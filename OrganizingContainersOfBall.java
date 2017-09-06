package hackerrank;

import java.util.Scanner;

public class OrganizingContainersOfBall {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        for (int q = 0; q < queries; q++) {

            int n = scanner.nextInt();

            int[][] matrix = new int[n][n];
            int[] inBoxes = new int[n];
            int[] ofType = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                    inBoxes[i] += matrix[i][j];
                    ofType[j] += matrix[i][j];
                }
            }

            if(check(inBoxes, ofType))
                System.out.println("Possible");
            else
                System.out.println("Impossible");
        }
    }

    private static boolean check(int[]boxes, int[]types) {
        int n = boxes.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            for (; j < n; j++) {
                if (boxes[i] == types[j]) {
                    swap(types, i, j);
                    break;
                }
            }
            if (j == n)
                return false;
        }
        return true;
    }

    private static void swap(int[]array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
