package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Kurt Haffner
 */
public class Sudoku {

    //Grid to be used for the puzzle.
    public static char[][] grid;

    public static void main(String[] args) throws FileNotFoundException {

        //Make a puzzle file and fill it with the command line arguement.
        File puzzle = null;
        if (0 < args.length) {
            puzzle = new File(args[0]);
        } else {
            //Print error if file not put in to command line.
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }

        //Set up a scanner that reads puzzle.
        Scanner gridFill;
        gridFill = new Scanner(puzzle);

        //Set grid equal to a sudoku grid size.
        grid = new char[9][9];

        //Make a string, iterator, and gridNum to fill grid.
        String nextLine;
        int iterate = 0;
        int gridNum;

        //While there is a next line, continue to fill.
        while (gridFill.hasNext()) {

            //Set gridNum to 0 each time through.
            gridNum = 0;

            //Set nextLine to the next line of the file.
            nextLine = gridFill.nextLine();

            //Go through and fill the grid. Adjust gridNum to +2 to
            //remove the spaces.
            for (int i = 0; i < 9; i++) {
                grid[iterate][i] = nextLine.charAt(gridNum);
                gridNum = gridNum + 2;
            }

            //Add one to iterate for grid row.
            iterate++;
        }

        //Set finish equal to solver at position (0,0). This will do the 
        //recursion to solve the puzzle.
        boolean finish = solver(0, 0);

        //If the puzzle is solved, print out the puzzle row by row.
        if (finish == true) {
            System.out.println("The solved sudoku puzzle is: ");
            System.out.println("");
            System.out.println("");

            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    System.out.print(grid[j][k] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
        else
            System.out.println("This puzzle appears unsolvable.");
    }

    //Method to do all the recursion.
    public static boolean solver(int row, int column) {

        //Set test to 1 and valid to false. Will occur for each recursive call.
        int test = 1;
        boolean valid = false;

        //Go here if the current position is not filled.
        if (grid[row][column] == '0') {
            //Run through the numbers, 1-9.
            while (valid != true && test <= 9) {
                //Call isValid with the current numer.
                //If true, continue.
                if (isValid(row, column, test) == true) {
                    //Set grid position to current test. First set the char '0'
                    //to a int, then add test. Finally, cast it all back to a
                    //char, so that the grid will accept it.
                    grid[row][column] = (char) (((int) '0') + test);

                    //Make reursive calls based on the current row and column.
                    if (row < 8 && column == 8) {
                        valid = solver(row + 1, 0);
                    } else if (column < 8) {
                        valid = solver(row, column + 1);
                    }
                    //If at last position, return true as puzzle is finished.
                    if (row == 8 && column == 8) {
                        return true;
                    }

                    //If valid is false, reset position to 0.
                    if (valid == false) {
                        grid[row][column] = '0';
                    }
                } 
                //If false, reset position to 0.
                else {
                    grid[row][column] = '0';
                }
                //Increase test by one to get the next possible number.
                test++;
            }
        } //Used if the position is already finished.
        else {

            //Make recursive calls based on row and column positions.
            if (row < 8 && column == 8) {
                valid = solver(row + 1, 0);
            } else if (column < 8) {
                valid = solver(row, column + 1);
            }
            //If at last position, return true as puzzle is complete.
            if (row == 8 && column == 8) {
                return true;
            }
        }
        //Return boolean valid at the end.
        return valid;
    }

    //Method to check validity.
    public static boolean isValid(int row, int column, int number) {

        //Make a temp character out of the number being checked.
        char temp = (char) (((int) '0') + number);
        //Make temp row and col to use in square check.
        int tempRow;
        int tempCol;
        
        //Set tempRow and tempCol equal to one of three spots based on where 
        //the current row and column are.
        if (row == 0 || row == 1 || row == 2){
            tempRow = 0;
        }
        else if (row == 3 || row == 4 || row == 5){
            tempRow = 3;
        }
        else{
            tempRow = 6;
        }
        
        if (column == 0 || column == 1 || column == 2){
            tempCol = 0;
        }
        else if (column == 3 || column == 4 || column == 5){
            tempCol = 3;
        }
        else{
            tempCol = 6;
        }

        //Check the row and column of the current position and return false
        //if temp already exists.
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == temp) {
                return false;
            }
            if (grid[i][column] == temp) {
                return false;
            }
        }
        
        //Check the grid the current temp is in and return false if it already
        //exists.
        for(int j = tempRow; j < tempRow + 3; j++){
            for(int k = tempCol; k < tempCol + 3; k++){
                if(grid[j][k] == temp){
                    return false;
                }
            }
        }

        //Return true if no conflicts are found.
        return true;
    }

}
