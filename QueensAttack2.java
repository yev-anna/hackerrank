package hackerrank;

import java.util.*;

public class QueensAttack2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int boardSize = scanner.nextInt();
        int obsNum = scanner.nextInt();

        int rQueen = scanner.nextInt();
        int cQueen = scanner.nextInt();
        Queen queen = new Queen(rQueen, cQueen);

        List<Tuple> obstacles = new ArrayList<>(obsNum);
        for (int i = 0; i < obsNum; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            obstacles.add(new Tuple(r,c));
        }

        //System.out.println(obstacles);
        //System.out.println(queen.hasRowCross(obstacles, boardSize));
        //System.out.println(queen.hasColumnCross(obstacles, boardSize));
        //System.out.println(queen.hasLeftDiagonal(obstacles, boardSize));

        System.out.println(moves(queen,obstacles,boardSize));
    }

    private static int moves(Queen queen, List<Tuple> obstacles, int boardSize) {
        int rowCross =  queen.hasRowCross(obstacles, boardSize);
        int columnCross = queen.hasColumnCross(obstacles, boardSize);
        int rightDiagonal = queen.hasRightDiagonal(obstacles, boardSize);
        int leftDiagonal = queen.hasLeftDiagonal(obstacles,boardSize);

        //System.out.println(rowCross + " " + columnCross + " " + rightDiagonal + " " + leftDiagonal);

        return queen.hasColumnCross(obstacles, boardSize) + queen.hasRowCross(obstacles, boardSize)
                + queen.hasRightDiagonal(obstacles, boardSize) + queen.hasLeftDiagonal(obstacles,boardSize);
    }

}

class Queen extends Tuple{

    Queen(int row, int column) {
        super(row, column);
    }

    int hasRowCross(List<Tuple> obstacles, int boardSize) { // for row
        Collections.sort(obstacles, new TupleRowComparator());

        int maxColumnLeft = 0;
        for (int i = 0; i < obstacles.size() ; i++) {
            if (obstacles.get(i).row == row && obstacles.get(i).column < column)
                maxColumnLeft = obstacles.get(i).column;
            if (obstacles.get(i).row > row)
                break;
        }

        int minColumnRight = boardSize+1;
        for (int i = obstacles.size()-1; i >= 0 ; i--) {
            if (obstacles.get(i).row == row && obstacles.get(i).column > column)
                minColumnRight = obstacles.get(i).column;
            if (obstacles.get(i).row < row)
                break;
        }

        int left = column - maxColumnLeft - 1; // if queen is on 5th and obs is on 4th we have 5-4-1=0 left. If no obs left - 5-0-1 = 4;
        int right = minColumnRight - column -1; //if queen is on 5th and obs 6th we have 6-5-1=0; If no obs right - (8+1)-5-1=3;
        return left+right;
    }

    int hasColumnCross(List<Tuple> obstacles, int boardSize) {
        Collections.sort(obstacles, new TupleColumnComparator());

        int maxRowDown = 0;
        for (int i = 0; i < obstacles.size() ; i++) {
            if (obstacles.get(i).column == column && obstacles.get(i).row < row)
                maxRowDown = obstacles.get(i).row;
            if (obstacles.get(i).column > column)
                break;
        }

        int minRowUp = boardSize+1;
        for (int i = obstacles.size()-1; i >= 0 ; i--) {
            if (obstacles.get(i).column == column && obstacles.get(i).row > row)
                minRowUp = obstacles.get(i).row;
            if (obstacles.get(i).column < column)
                break;
        }

        //System.out.println("maxRowDown = " + maxRowDown + " minRowUp = " + minRowUp);


        int down = row - maxRowDown - 1; // if queen is on 5th and obs is on 4th we have 5-4-1=0 left. If no obs left - 5-0-1 = 4;
        int up = minRowUp - row -1; //if queen is on 5th and obs 6th we have 6-5-1=0; If no obs right - (8+1)-5-1=3;
        return down+up;
    }

    int hasRightDiagonal (List<Tuple> obstacles, int boardSize) {
        // like this /
        List<Tuple> diagonalObs = new ArrayList<>();
        for (int i = 0; i < obstacles.size(); i++) {
            if(obstacles.get(i).row - obstacles.get(i).column == row - column)
                diagonalObs.add(obstacles.get(i));
        }
        Collections.sort(diagonalObs, new TupleRowComparator());

        //System.out.println(diagonalObs);

        int maxDownRow = 0, maxDownColumn = 0;
        for (int i = 0; i < diagonalObs.size(); i++) {
            if (diagonalObs.get(i).row < row) {
                maxDownRow = diagonalObs.get(i).row;
                maxDownColumn = diagonalObs.get(i).column;
                //System.out.println("i = " + i + " maxDownRow = " + maxDownRow + " maxDownColumn = " + maxDownColumn);
            }
            if (diagonalObs.get(i).row > row)
                break;
        }
        //System.out.println("maxDownRow = " + maxDownRow + " maxDownColumn = " + maxDownColumn);

        int down = column - maxDownColumn -1 ; //if queen on 5x4 and obs on 4x3 -> 4-3-1. If no obs - 4-0-1=3

        int minUpRow = boardSize+1, minUpColumn = boardSize+1;
        for (int i = diagonalObs.size()-1; i > 0; i--) {
            if (diagonalObs.get(i).row > row) {
                minUpRow = diagonalObs.get(i).row;
                minUpColumn = diagonalObs.get(i).column;
            }
            if (diagonalObs.get(i).row < row)
                break;
        }
        //System.out.println("minUpRow = " + minUpRow + " minUpColumn = " + minUpColumn);

        int up = minUpRow - row - 1; //if queen on 5x4 and obs on 7x6 -> 6-4-1=1; if no obs (8+1)-5-1=3;

        return down + up;
    }

    int hasLeftDiagonal (List<Tuple> obstacles, int boardSize) {
        // like this \
        List<Tuple> diagonalObs = new ArrayList<>();
        for (int i = 0; i < obstacles.size(); i++) {
            if(obstacles.get(i).row + obstacles.get(i).column == row + column)
                diagonalObs.add(obstacles.get(i));
        }
        Collections.sort(diagonalObs, new TupleColumnComparator());

        //System.out.println(diagonalObs);

        int minUpRow = boardSize+1, minUpColumn = 0;
        for (int i = 0; i < diagonalObs.size(); i++) {
            if (diagonalObs.get(i).column < column) {
                minUpRow = diagonalObs.get(i).row;
                minUpColumn = diagonalObs.get(i).column;
            }
            if (diagonalObs.get(i).column > column)
                break;
        }
        //System.out.println("minUpRow = " + minUpRow + " minUpColumn = " + minUpColumn);

        int up = minUpRow - row - 1;
        // if queen on 5x4 and obs on 7x2 -> 7-5-1=1; if no obs = 9-5-1=3


        int maxDownRow = 0, maxDownColumn = boardSize+1;
        for (int i = diagonalObs.size()-1; i >0; i--) {
            if (diagonalObs.get(i).column > column) {
                maxDownRow = diagonalObs.get(i).row;
                maxDownColumn = diagonalObs.get(i).column;
                //System.out.println("i = " + i + " maxDownRow = " + maxDownRow + " maxDownColumn = " + maxDownColumn);
            }
            if (diagonalObs.get(i).column < column)
                break;
        }
        //System.out.println("maxDownRow = " + maxDownRow + " maxDownColumn = " + maxDownColumn);

        int down = maxDownColumn - column - 1 ; //if queen on 5x4 and obs on 3x6 -> 6-4-1. If no obs - (8+1)-4-1=4

        return down + up;
    }
}

class Tuple {
    protected int row;
    protected int column;

    Tuple(int row, int column) {
        this.row = row;
        this.column = column;
    }

    boolean isCross(Tuple tuple){
        if (row==tuple.row || column == tuple.column)
            return true;
        if ((row + column == tuple.row + tuple.column) || (row - column == tuple.row - tuple.column)) //diagonal
            return true;
        return false;
    }

    static class TupleRowComparator implements Comparator<Tuple> {

        @Override
        public int compare(Tuple t1, Tuple t2) {
            if (t1.row > t2.row) return 1;
            if (t1.row < t2.row) return -1;
            if (t1.row == t2.row) {
                if (t1.column > t2.column)
                    return 1;
                if (t1.column < t2.column)
                    return -1;
            }
            return 0;
        }
    }

    static class TupleColumnComparator implements Comparator<Tuple> {

        @Override
        public int compare(Tuple t1, Tuple t2) {
            if (t1.column > t2.column) return 1;
            if (t1.column < t2.column) return -1;
            if (t1.column == t2.column) {
                if (t1.row > t2.row)
                    return 1;
                if (t1.row < t2.row)
                    return -1;
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return "{"+row+";"+column+"}";
    }
}
