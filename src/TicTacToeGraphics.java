import javafx.application.Application;
import javafx.scene.Scene;
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
  private static final int SCENE_DIMENSION = 900;
  private static final int TILE_DIMENSION = 300;
  private boolean isWinnerPresent = false;
  private Label statusOfGameLabel = new Label(PLAYER_1_TURN);
  private TileCell[][] graphicsBoard = new TileCell[NUM_ROWS][NUM_COLS];
  private TicTacToe ticTacToeBoard = new TicTacToe();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    GridPane tilePane = new GridPane();
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        graphicsBoard[i][j] = new TileCell(" ", i, j);
        tilePane.add(graphicsBoard[i][j], i, j);
      }
    }

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(tilePane);
    statusOfGameLabel.setFont(Font.font(BOTTOM_MESSAGE_FONT_SIZE));
    borderPane.setBottom(statusOfGameLabel);

    Scene scene = new Scene(borderPane, SCENE_DIMENSION, SCENE_DIMENSION);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TIC TAC TOE");
    primaryStage.show();
  }

  /**
   * Class to represent a board square in a tic tac toe game.
   */
  public class TileCell extends StackPane {
    Text text = new Text();
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

        if (ticTacToeBoard.getTurn() == MAX_TURNS) {
          statusOfGameLabel.setText("Stalemate");
        }
      }
    }
  }
}
