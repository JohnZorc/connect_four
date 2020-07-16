import java.util.Scanner;

public class connect_four
{

    public static void printBoard(char[][] array) // call upon this method to print the board
    {
        for (int i=array.length - 1; i>-1; i--)
        {
            for (int j=0; j<array[i].length; j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n"); // Starts a new line.
        }
    }

    public static void initializeBoard(char[][] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            for (int j = 0; j<array[i].length; j++)
            {
                array[i][j] = '-';
            }
        }

    }

	//Insert in next available spot in collumn
    public static int insertChip(char[][] array, int col, char chipType)
    {
        int i;
        for (i = 0; i < array.length; i++)
            if (array[i][col] == '-')
            {
                array[i][col] = chipType;
                break;
            }

        return i; 
    }


    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) //used
            //to check if the game should end because we have found a winner or not
    {
        int count = 0;
        char tempChar = chipType;
        for (int i = 0; i < array.length; i++) // checks to see if the userCol has 4 in a row
        {
            if (array[i][col] == tempChar) // if the index is equal to the users
                //chip then the count increases
            {
                count++;
                if (count == 4) //this is connect four and the game is over
                    return true;
            }
            else
            {
                count = 1; // count restarts because the index is not the same
                //as the last
            }
            tempChar = array[i][col];//sets a temp char to be equal to the
            //last index so you can check for continuity in next iteration
            if (tempChar == '-')
                break; // do this so the player doesn't win with four '-' characters
        }

        count = 0; //reset to 0 since it wasn't a local variable for only the last loop
        tempChar = chipType;
        for (int i = 0; i < array[0].length; i++) // checks to see if the user row has 4 in a row
        {
            if (array[row][i] == tempChar) //checks to see if it is last players chip
            {
                count++; // count increases if the players chip is the chip in the
                //row last played in
                if (count == 4)
                    return true; // the player has connect four and wins the game
            }
            else
            {
                count = 1; //restart the count since the chain of the same type
                //chips has been broken
            }
            tempChar = array[row][i]; //sets tempChar = to the last char in the row then
            //the loop moves onto the next char and checks this var for continuity
            if (tempChar == '-')
                break; // do this so the player doesn't win with four '-' characters
        }
        return false; // only executes if there isn't 4 in a column or row
    }

    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        int rows = 0;
        int cols = 0; 

        int userCol;
        int userRow;
		boolean gotDimensions = false;

        System.out.print("What would you like the width and height to be?");
		while(!gotDimensions) {
			if(scnr.hasNextInt())
			{
				rows = scnr.nextInt();
				cols = rows;
				gotDimensions = true;
			}
			else 
				System.out.println("Please input a number");
			scnr.nextLine();
		}

        char[][] board = new char[rows][cols];

        initializeBoard(board); // sets all board values to '-'

        printBoard(board);

        System.out.println("\nPlayer 1: x");
        System.out.println("Player 2: o");
		System.out.println("The left-most collumn is collumn 0");

        int maxMoves = rows * cols; //Max amount of moves the players can make
        int i = 0;

        while (i < maxMoves)
        {
            System.out.print("\nPlayer 1 (x) Which column would you like to choose? ");
            userCol = scnr.nextInt();

            userRow = insertChip(board, userCol, 'x'); //'x' is input for player one

            printBoard(board);

            if (checkIfWinner(board, userCol, userRow, 'x'))
            {
                System.out.println();
                System.out.println("Player 1 won the game!");
                break; 
            }
            i++;

            if (i >= maxMoves) 
            {
				System.out.println();
				System.out.println("Draw. Nobody wins.");
                break;
				
            }

            System.out.print("\nPlayer 2 (o) Which column would you like to choose? ");
            userCol = scnr.nextInt();

            userRow = insertChip(board, userCol, 'o');

            printBoard(board);

            if (checkIfWinner(board, userCol, userRow, 'o'))
            {
                System.out.println();
                System.out.println("Player 2 won the game!");
                break; 
            }
			if (i >= maxMoves) 
            {
				System.out.println();
				System.out.println("Draw. Nobody wins.");
                break;
				
            }
        }
    }
}