import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToeGraphics extends Application {

  private static final int NUM_ROWS = 3;
  private static final int NUM_COLS = 3;
  private boolean isWinnerPresent = false;
  private Label statusOfGameLabel = new Label("X's turn");
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
    statusOfGameLabel.setFont(Font.font(40));
    borderPane.setBottom(statusOfGameLabel);

    Scene scene = new Scene(borderPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }

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
      text.setFont(Font.font(80));
      setStyle("-fx-border-color:black");
      this.setPrefSize(200, 200);
      this.setOnMouseClicked(event -> handleMouseClick());
      this.getChildren().addAll(text);

    }

    private void handleMouseClick() {
      //Don't allow the game to continue if a player has won
      if (isWinnerPresent) {
        return;
      }

      boolean wasSetBoardSuccessful = ticTacToeBoard.setBoardAt(this.row, this.col);
      System.out.println("set board: " + wasSetBoardSuccessful);
      if (wasSetBoardSuccessful) {
        int turn = ticTacToeBoard.getTurn();
        if (turn % 2 == 1) {
          text.setText("X");
          statusOfGameLabel.setText("O's turn");
        } else {
          text.setText("O");
          statusOfGameLabel.setText("X's turn");
        }

        String winner = ticTacToeBoard.getWinner();

        if (winner != null) {
          System.out.println("There was a winner");
          isWinnerPresent = true;
          statusOfGameLabel.setText("The winner is: " + winner);
        }

        if (ticTacToeBoard.getTurn() == 9) {
          statusOfGameLabel.setText("Stalemate");
        }
      }
    }
  }
}
