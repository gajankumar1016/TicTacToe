import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class that handles the graphics of tic tac toe.
 */
public class TicTacToeGraphics extends Application {
  private static final int NUM_ROWS = 3;
  private static final int NUM_COLS = 3;
  private static final int MAX_TURNS = 9;
  private static final String PLAYER_1_TURN = TicTacToe.PLAYER_1 + "'s turn";
  private static final String PLAYER_2_TURN = TicTacToe.PLAYER_2 + "'s turn";
  private static final int PLAYER_FONT_SIZE = 80;
  private static final int BOTTOM_MESSAGE_FONT_SIZE = 40;
  private static final int RESET_BUTTON_TEXT_SIZE = 40;
  private static final int SCENE_DIMENSION_X = 750;
  private static final int SCENE_DIMENSION_Y = 900;
  private static final int TILE_DIMENSION = 250;
  private boolean isWinnerPresent = false;
  private Label statusOfGameLabel = new Label(PLAYER_1_TURN);
  private TileCell[][] graphicsBoard = new TileCell[NUM_ROWS][NUM_COLS];
  private TicTacToe ticTacToeBoard;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpNewGame(primaryStage);
  }

  private void setUpNewGame(Stage primaryStage) {
    ticTacToeBoard = new TicTacToe();
    GridPane tilePane = new GridPane();
    for (int row = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        graphicsBoard[row][col] = new TileCell(" ", row, col);
        tilePane.add(graphicsBoard[row][col], row, col);
      }
    }

    Button resetButton = new Button("RESET");
    resetButton.setFont(Font.font(RESET_BUTTON_TEXT_SIZE));
    resetButton.setMaxSize(TILE_DIMENSION, TILE_DIMENSION);
    resetButton.setOnMouseClicked(event -> resetGame());

    tilePane.add(resetButton, NUM_COLS - 1, NUM_ROWS);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(tilePane);
    statusOfGameLabel.setFont(Font.font(BOTTOM_MESSAGE_FONT_SIZE));
    borderPane.setBottom(statusOfGameLabel);

    Scene scene = new Scene(borderPane, SCENE_DIMENSION_X, SCENE_DIMENSION_Y);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TIC TAC TOE");
    primaryStage.show();
  }

  /**
   * Resets the game by reinitializing the necessary components.
   */
  private void resetGame() {
    ticTacToeBoard.reset();
    //reset the text at each board square
    for (int row = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        graphicsBoard[row][col].setText("");
      }
    }
    isWinnerPresent = false;
    statusOfGameLabel.setText(PLAYER_1_TURN);
  }

  /**
   * Class to represent a board square in a tic tac toe game.
   */
  private class TileCell extends StackPane {
    private Text text = new Text();
    private String playerName;
    private int row;
    private int col;

    //made constructor private so it can only be used within the TicTacToeGraphics class
    private TileCell(String playerName, int row, int col) {
      this.playerName = playerName;
      this.row = row;
      this.col = col;
      text.setFont(Font.font(PLAYER_FONT_SIZE));
      setStyle("-fx-border-color:black");
      this.setPrefSize(TILE_DIMENSION, TILE_DIMENSION);
      //Uses Java 8 Lambda expression to handle event by executing our custom handleMouseClick() method
      this.setOnMouseClicked(event -> handleMouseClick());
      this.getChildren().addAll(text);
    }

    private void setText(String text) {
      this.text.setText(text);
    }

    /**
     * Handles what should happen to a square if a player click's on it.
     */
    private void handleMouseClick() {
      //Don't allow the game to continue if a player has won
      if (isWinnerPresent) {
        return;
      }

      boolean wasSetBoardSuccessful = ticTacToeBoard.setBoardAt(this.row, this.col);
      if (wasSetBoardSuccessful) {
        int turn = ticTacToeBoard.getTurn();
        if (turn % 2 == 1) {
          text.setText(TicTacToe.PLAYER_1);
          statusOfGameLabel.setText(PLAYER_2_TURN);
        } else {
          text.setText(TicTacToe.PLAYER_2);
          statusOfGameLabel.setText(PLAYER_1_TURN);
        }

        String winner = ticTacToeBoard.getWinner();

        if (winner != null) {
          isWinnerPresent = true;
          statusOfGameLabel.setText("The winner is: " + winner);
        }

        if (ticTacToeBoard.getTurn() == MAX_TURNS && !isWinnerPresent) {
          statusOfGameLabel.setText("Stalemate");
        }
      }
    }
  }
}
