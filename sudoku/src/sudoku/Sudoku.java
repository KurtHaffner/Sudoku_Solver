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

            for (int i = 0; i < 9; i=i++) {
                    grid[iterate][i] = nextLine.charAt(gridNum);
                    gridNum = gridNum + 2;
            }

            iterate++;
        }

        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                System.out.print(grid[j][k]);
            }
            System.out.println("");
        }
    }

}
