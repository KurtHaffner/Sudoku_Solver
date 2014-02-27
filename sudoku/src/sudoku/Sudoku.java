package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Kurt Haffner
 */
public class Sudoku {

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

        char[][] grid;
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

        solver(grid, 0, 0);
    }

    public static void solver(char sudoGrid[][], int row, int column) {

        if (row < 9 && column < 9) {
            if (sudoGrid[row][column] == '0') {
                for (int num = 1; num < 10; num++) {
                    for (int test = 0; test < 9; test++) {
                        if (sudoGrid[row][test] == num) {
                            
                        }
                        if (sudoGrid[test][column] == num) {
                            
                        }
                    }
                }
            } else if (column == 8) {
                solver(sudoGrid, row++, 0);
            } else {
                solver(sudoGrid, row, column++);
            }
        }

        System.out.println("The solved sudoku puzzle is: ");

        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                System.out.print(sudoGrid[j][k] + " ");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }

    }

}
