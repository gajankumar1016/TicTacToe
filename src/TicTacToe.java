/**
 * Class that keeps track of the board state and performs necessary calculations.
 */
public class TicTacToe {
	
  //perhaps in the future players could enter their own names to play
  public static final String PLAYER_1 = "X";
  public static final String PLAYER_2 = "O";

  private static final int ROWS = 3;
  private static final int COLS = 3;

  private String[][] board;
  private int turn = 0;
  
  private boolean gameOnGoing = true; // false if the game ended

  /**
   * Constructor that initializes each board space to an empty string.
   */
  public TicTacToe() {
    this.board = new String[ROWS][COLS];
    // fill all the spots as empty string.
    for (int row = 0; row < ROWS; row++) {
    		for (int col = 0; col < COLS; col++) {
    			this.board[row][col] = "";
    		}
    }
  }

  public int getTurn() {
    return turn;
  }

  /**
   * Tells whether game is still going.
   * @return true if still going, false if stalemate
   */
  public boolean isGameStillGoing() {
	  // checks if the game is on-going or not	  
	  if (turn == 8) { // maximum spots reached
		  gameOnGoing = false;
		  turn = 0; // reset to beginning
		  System.out.print("No more spots to fill in. The game will restart.");
	  }
      return gameOnGoing;
  }

  /**
   *Prints the player whose turn it is.
   */
  public void checkTurn() {
	// checks the turn of player
	  if (turn % 2 == 0) {
		  System.out.println("PLAYER_1's turn");
	  } else {
		  System.out.println("PLAYER_2's turn");
	  }
  }

  /**
   * Sets the board at a given location.
   * @param row row
   * @param col column
   * @return true if successful set, false otherwise
   */
  public boolean setBoardAt(int row, int col) {
    System.out.println("Args to setBoard" + "row: " + row + "   col: " + col);
    printBoard();

	  //checks for valid board spot
	  if (row >= ROWS || col >= COLS) {
		  System.out.println("Please click the spot within the range.");
		  return false;
	  }

    if (!this.board[row][col].equals("")) {
      return false;
    }
	  
    if (this.board[row][col].equals("") && turn % 2 == 0) {
		  this.board[row][col] = PLAYER_1;
		  turn++;
		  return true;
    }
      
    if (this.board[row][col].equals("") && turn % 2 == 1) {
		  this.board[row][col] = PLAYER_2;
		  turn++;
		  return true;
    }

    return  false;
  }

  /**
   * Returns the winner
   * @return Returns winner's string name if one is present, null otherwise
   */
  public String getWinner() {
    //break this up into horizontal, vertical, and diagonal
	  
    //case1: loop over each row
    for (int row =0; row < board.length; row++) {
        if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])
                && !board[row][0].equals("")) {
            gameOnGoing = false;
            return board[row][0];
        }
    }

    //case2: loop over each column
    for (int col=0; col < board[0].length; col++) {
        if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])
                && !board[0][col].equals("")) {
            gameOnGoing = false;
            return board[0][col];
        }
    }

    //case3: check the diagonals
    if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])
            && !board[0][0].equals("")) {
        gameOnGoing = false;
        return board[0][0];
    }

    if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])
            && !board[2][0].equals("")) {
	    gameOnGoing = false;
        return board[2][0];
	        }
    // return the statement below if game is not complete yet
  	return null;
  }

  /**
   * Prints the board for debugging purposes.
   */
  private void printBoard() {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        System.out.print(this.board[i][j]);
      }
      System.out.println();
    }
  }

  /**
   * Resets the board.
   */
  public void reset() {
    this.turn = 0;

    this.gameOnGoing = true;

    //reset board
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        this.board[row][col] = "";
      }
    }
  }
}
