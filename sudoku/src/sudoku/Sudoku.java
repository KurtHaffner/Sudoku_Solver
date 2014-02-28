package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Kurt Haffner
 */
public class Sudoku {

    public static char[][] grid;

    public static void main(String[] args) throws FileNotFoundException {

        File puzzle = null;
        if (0 < args.length) {
            puzzle = new File(args[0]);
        } else {
            //Print error if file not put in to command line.
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }

        Scanner gridFill;
        gridFill = new Scanner(puzzle);

        grid = new char[9][9];

        String nextLine;
        int iterate = 0;
        int gridNum;

        while (gridFill.hasNext()) {

            gridNum = 0;

            nextLine = gridFill.nextLine();

            for (int i = 0; i < 9; i++) {
                grid[iterate][i] = nextLine.charAt(gridNum);
                gridNum = gridNum + 2;
            }

            iterate++;
        }

        boolean finish = solver(0, 0);

        if (finish == true) {
            System.out.println("The solved sudoku puzzle is: ");

            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    System.out.print(grid[j][k] + " ");
                }
                System.out.println("");
                System.out.println("");
                System.out.println("");
            }
        }
    }

    public static boolean solver(int row, int column) {

        int test = 1;
        boolean valid = false;

        if (grid[row][column] == '0') {
            while (valid != true && test <= 9) {
                if (isValid(row, column, test) == true) {
                    grid[row][column] = (char) (((int) '0') + test);

                    if (row == 8 && column <= 8) {
                        valid = solver(row + 1, 0);
                    } else if (row <= 8 && column < 8) {
                        valid = solver(row, column + 1);
                    }
                    if (row == 8 && column == 8) {
                        return true;
                    }

                    if (valid == false) {
                        grid[row][column] = '0';
                    }
                } else {
                    grid[row][column] = '0';
                }
                test++;
            }
        } //Used if the position is already finished.
        else {

            if (row <= 8 && column == 8) {
                valid = solver(row + 1, 0);
            } else if (row <= 8 && column < 8) {
                valid = solver(row, column + 1);
            }
            if (row == 8 && column == 8) {
                return true;
            }
        }
        return valid;
    }

    public static boolean isValid(int row, int column, int number) {

        char temp = (char) (((int) '0') + number);
        int tempRow = row;
        int tempCol = column;

        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == temp) {
                return false;
            }
            if (grid[i][column] == temp) {
                return false;
            }
        }

        return true;
    }

}
