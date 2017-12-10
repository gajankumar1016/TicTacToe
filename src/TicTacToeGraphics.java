import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToeGraphics extends Application {

  private static final int NUM_ROWS = 3;
  private static final int NUM_COLS = 3;
  private TileCell[][] graphicsBoard = new TileCell[NUM_ROWS][NUM_COLS];
  private TicTacToe ticTacToeBoard = new TicTacToe();

  public static void main(String[] args) {
    launch(args);
  }

  public class TileCell extends Pane {

    private String playerName;
    private int row;
    private int col;

    public TileCell(String playerName, int row, int col) {
      this.playerName = playerName;
      setStyle("-fx-border-color:black");
      this.setPrefSize(300, 300);
      this.setOnMouseClicked(e -> handleMouseClick());

    }

    private void handleMouseClick() {
      boolean wasSetBoardSuccessful = ticTacToeBoard.setBoardAt(row, col);
      this.setAccessibleText("X");
      if (wasSetBoardSuccessful) {
        return;
      } else {
        int turn = ticTacToeBoard.getTurn();
        if (turn % 2 == 0) {
          this.setAccessibleText("X");
        } else {
          this.setAccessibleText("O");
        }

      }
    }

    public String getPlayerName() {
      return playerName;
    }

    public void setPlayerName(String playerName) {
      this.playerName = playerName;
    }
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

    Scene scene = new Scene(borderPane, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }
}
