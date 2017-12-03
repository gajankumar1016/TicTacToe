public class TicTacToe {
  //perhaps in the future players could enter their own names to play
  private static final String PLAYER_1 = "X";
  private static final String PLAYER_2 = "O";

  private static final int NUM_ROWS = 3;
  private static final int NUM_COLS = 3;

  private String[][] board;
  private int turn;

  public TicTacToe() {
    this.board = new String[NUM_ROWS][NUM_COLS];
  }

  public int getTurn() {
    return turn;
  }

  public String[][] getBoard() {
    //return copy of board
    return null;
  }

  public boolean setBoardAt(int row, int col) {
    //this method should utilize the turn number to set the correct player
    return  false;
  }

  public String getWinner() {
    //break this up into horizontal, vertical, and diagonal
    return null;
  }
}
