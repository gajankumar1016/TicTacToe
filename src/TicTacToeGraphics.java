import javafx.application.Application;
import javafx.scene.Scene;
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
  private TileCell[][] graphicsBoard = new TileCell[NUM_ROWS][NUM_COLS];
  private TicTacToe ticTacToeBoard = new TicTacToe();

  public static void main(String[] args) {
    launch(args);
  }

  public class TileCell extends StackPane {
    Text text = new Text();
    private String playerName;
    private int row;
    private int col;

    public TileCell(String playerName, int row, int col) {
      this.playerName = playerName;
      text.setFont(Font.font(70));
      setStyle("-fx-border-color:black");
      this.setPrefSize(200, 200);
      this.setOnMouseClicked(event -> handleMouseClick());
      this.getChildren().addAll(text);

    }

    private void handleMouseClick() {
      boolean wasSetBoardSuccessful = ticTacToeBoard.setBoardAt(row, col);
      text.setText("X");
      if (wasSetBoardSuccessful) {
        int turn = ticTacToeBoard.getTurn();
        if (turn % 2 == 0) {
          text.setText("X");
        } else {
          text.setText("O");
        }

        String winner = ticTacToeBoard.getWinner();

        if (winner != null) {

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

    Scene scene = new Scene(borderPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }
}
