package NRow;

import NRow.Heuristics.Heuristic;
import NRow.Heuristics.SimpleHeuristic;
import NRow.Heuristics.SmartHeuristic;
import NRow.Players.PlayerController;

public class Game {
  private int gameN;
  private PlayerController[] players;
  private Board gameBoard;
  private int winner;

  /**
   * Create a new game
   * @param gameN N in a row required to win
   * @param boardWidth Width of the board
   * @param boardHeight Height of the board
   * @param players List of players
   */
  Game(int gameN, int boardWidth, int boardHeight, PlayerController[] players) {
    assert (boardWidth % 2 != 0) : "Board width must be odd!";
    this.gameN = gameN;
    this.players = players;
    this.gameBoard = new Board(boardWidth, boardHeight);
  }

  

  public void testGame() {

    State TestBoard;
    State state = new State(gameBoard, 0, -1);
    Tree tree = new Tree(state);
    tree.createTree(3);

    // Create a testing board, a board after a few moves.

    Node tTree = tree.root.getChildren().get(0).getChildren().get(0).getChildren().get(1);
    tTree.createChildren(4);
    tTree = tTree.getChildren().get(1).getChildren().get(0).getChildren().get(1).getChildren().get(2);
    System.out.println(tTree.getState().getBoard());

    SimpleHeuristic siH = new SimpleHeuristic(gameN);
    SmartHeuristic saH = new SmartHeuristic(gameN);
    System.out.println(saH.evaluateBoard(1, tTree.getState().getBoard()));


    //tTree.createChildren();
  }

    
    /*Node node = new Node(state, null);

    node.createChildren();
    node = node.getChildren().get(0);

    node.createChildren();
    node = node.getChildren().get(3);

    node.createChildren();
    node = node.getChildren().get(6);

    node.createChildren();
    node = node.getChildren().get(1);

    node.createChildren();
    node = node.getChildren().get(1);
    
    System.out.println(node.getState().getBoard()); 
  } */

  /**
   * Starts the game
   * @return the playerId of the winner
   */
  public int startGame() {
    System.out.println("Start game!");
    int currentPlayer = 0;

    while (!this.isOver()) {
      // turn player can make a move
      gameBoard.play(players[currentPlayer].makeMove(gameBoard), players[currentPlayer].playerId);
      System.out.println(gameBoard);
      try { 
        Thread.sleep(2000); 
      } catch(InterruptedException e) {
        System.out.println(e);
      }
      
      // other player can make a move now
      currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }


    System.out.println(gameBoard);
    if(winner < 0) {
      System.out.println("Game is a draw!");
    } else {
      System.out.println("Player " + players[winner - 1] + " won!");
    }
    System.out.println("Player " + players[0] + " evaluated a boardstate " + players[0].getEvalCount() + " times.");
    System.out.println("Player " + players[1] + " evaluated a boardstate " + players[1].getEvalCount() + " times.");
    return winner;
  }

  /**
   * Determine whether the game is over
   * @return true if game is over
   */
  public boolean isOver() {
    winner = winning(gameBoard.getBoardState(), this.gameN);
    return winner != 0;
  }

  /**
   * Determines whether a player has won, and if so, which one
   * @param board the board to check
   * @param gameN N in a row required to win
   * @return 1 or 2 if the respective player won, or 0 if neither player has won
   */
  public static int winning(int[][] board, int gameN) {
    int player;

    // Vertical Check
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < (board[i].length - (gameN - 1)); j++) {
        if (board[i][j] != 0) {
          player = board[i][j];
          for (int x = 1; x < gameN; x++) {
            if (board[i][j + x] != player) {
              player = 0;
              break;
            }
          }
          if (player != 0) {
            return player;
          }
        }
      }
    }
    
    // Horizontal Check
    for (int i = 0; i < (board.length - (gameN - 1)); i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0) {
          player = board[i][j];
          for (int x = 1; x < gameN; x++) {
            if (board[i + x][j] != player) {
              player = 0;
              break;
            }
          }
          if (player != 0) {
            return player;
          }
        }
      }
    }
    
    // Ascending Diagonal Check
    for (int i = 0; i < (board.length - (gameN - 1)); i++) {
      for (int j = board[i].length - 1; j > gameN; j--) {
        if (board[i][j] != 0) {
          player = board[i][j];
          for (int x = 1; x < gameN; x++) {
            if (board[i + x][j - x] != player) {
              player = 0;
              break;
            }
          }
          if (player != 0) {
            return player;
          }
        }
      }
    }
    
    // Descending Diagonal Check
    for (int i = 0; i < (board.length - (gameN - 1)); i++) {
      for (int j = 0; j < (board[i].length - (gameN - 1)); j++) {
        if (board[i][j] != 0) {
          player = board[i][j];
          for (int x = 1; x < gameN; x++) {
            if (board[i + x][j + x] != player) {
              player = 0;
              break;
            }
          }
          if (player != 0) {
            return player;
          }
        }
      }
    }

    // Check for a draw (full board)
    for (int i = 0; i < board.length; i++) {
      if(board[i][0] == 0) {
        return 0; // board is not full, game not over
      }
    }
    
    return -1; // Game is a draw
  }

}
