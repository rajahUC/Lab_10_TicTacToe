import java.util.Random;
import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Random rnd = new Random();
        int rowMove = -1;
        int colMove = -1;
        boolean continueGame = true;
        String setPlayer = "X";

            do {
                // Clear the board
                clearBoard();
                // Show the board
                display(rowMove, colMove, setPlayer);
                // initialize a move counter
                int moveCnt = 0;
                // Set player to X
                if (setPlayer.equals("O"))
                {
                    setPlayer = "X";
                }

                do {
                    // Get coordinates with rangedint (1-3)
                    rowMove = SafeInput.getRangedInt(in, "Enter the row number: ", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Enter the column number: ", 1, 3);

                    // Convert to (0-2) by substracting 1

                    rowMove --;
                    colMove --;

                } while (!isValidMove(rowMove, colMove));

                // show the change on the board
                if (setPlayer.equals("X"))
                {
                    board[rowMove][colMove] = "X";
                }
                else
                {
                    board[rowMove][colMove] = "O";
                }
                moveCnt ++;

                // If move counter is > 4, check for a win
                if (moveCnt > 4)
                {
                    if (isWin(setPlayer))
                    {
                        if (setPlayer.equals("X"))
                        {
                            System.out.println("Player X wins!");
                        }
                        else
                        {
                            System.out.println("Player O wins!");
                        }
                        continueGame = SafeInput.getYNConfirm(in, "Play again? ");
                    }
                }


                // If move counter is 9, check for a tie
                // If there is a tie, announce it, and prompt players to play again?

                // Toggle the player
                if (setPlayer.equals("X"))
                {
                    setPlayer = "O";
                }
                else
                {
                    setPlayer = "X";
                }


            } while (continueGame);

    }

    private static void clearBoard() // sets all board elements to a space
    {
            for(int row=0; row < ROW; row++) {
                for (int col = 0; col < COL; col++) {
                    board[row][col] = " "; // make this cell a space
                }
            }
    }

    private static void display(int rowMove, int colMove, String setPlayer) // sets all board elements to a space
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                if (col != 0)
                {
                    System.out.print("|"); // print a vertical bar between columns
                }
                if (row == rowMove && col == colMove)
                {
                    if (setPlayer.equals("X"))
                    {
                        board[row][col] = "X";
                    }
                    if (setPlayer.equals("O"))
                    {
                        board[row][col] = "O";
                    }
                }
                else
                {
                    board[row][col] = " "; // make this cell a space
                }

                System.out.print(board[row][col]);
            }
            System.out.println();
            if (row != ROW - 1) {
                System.out.println("-----"); // print horizontal bars between rows
            }
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for(int row=0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
    return false; // no row win
    }

    private static boolean isColWin(String player)
    {
        for(int col=0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[0][col].equals(player) && board[0][col].equals(player)) {
                return true;
            }
        }
        return false; // no column win
    }

    private static boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || board[0][2].equals(player) && board[1][1].equals(player) && board[2][1].equals(player))
        {
            return true;
        }
        return false; // no diagonal win
    }

}