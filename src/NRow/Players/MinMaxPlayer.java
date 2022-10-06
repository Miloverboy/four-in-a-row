package NRow.Players;

import java.util.ArrayList;
import java.util.List;

import NRow.Board;
import NRow.Heuristics.Heuristic;
<<<<<<< HEAD
import NRow.Heuristics.SimpleHeuristic;
import NRow.Node;
import NRow.State;

=======
import NRow.Tree;
import NRow.State;
>>>>>>> 646dc487f5452bef607f0480b251cdaeaa20177d

public class MinMaxPlayer extends PlayerController {
    private int depth;
    public int move; 

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    public int max(Board board, int depth){
        int maxValue = 0;
        Node node = new Node(new State(board,1), null);
        node.createChildren();
        List<Node> children = new ArrayList<>();
        children.addAll(node.getChildren());
        if(depth == 0){
            // if we reached the final layer we wanna reach, we make simple eval and return the best one
            Heuristic simple = new SimpleHeuristic(gameN);
            for (Node n: children) {
                int eval = simple.evaluateBoard(1, n.getState().getBoard());
                if (eval > maxValue){
                        maxValue = eval; 
                    }
                }
        
            return maxValue;
        }
        else {
            //if there are still more depths to discover we make a move based on what the minimizer would ideally do in his next move
            //based on all the possible moves we have 
            for (Node n: children) {
                int eval = min(n.getState().getBoard(), depth-1);
                if (eval > maxValue){
                    maxValue = eval;
                }
            }
            return maxValue;
       }
}


public int min(Board board, int depth){
    int minValue = 9999;
    Node node = new Node(new State(board,2), null);
    node.createChildren();
    List<Node> children = new ArrayList<>();
    children.addAll(node.getChildren());
    if(depth == 0){
        // if we reached the final layer we wanna reach, we make simple eval and return the best one
        Heuristic simple = new SimpleHeuristic(gameN);
        for (Node n: children) {
            int eval = simple.evaluateBoard(1, n.getState().getBoard());
            if (eval < minValue){
                    minValue = eval; 
                }
            }
    
        return minValue;
    }
    else {
        //if there are still more depths to discover we make a move based on what the maximizer would ideally do in his next move 
        //based on all the possible moves we have 
        for (Node n: children) {
            int eval = max(n.getState().getBoard(), depth-1);
            if (eval < minValue){
                minValue = eval;
            }
        }
        return minValue;
   }
}

    /**
   * Implement this method yourself!
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        // TODO: implement minmax player!
        // HINT: use the functions on the 'board' object to produce a new board given a specific move
        // HINT: use the functions on the 'heuristic' object to produce evaluations for the different board states!
        
        State state = new State(gameBoard, 0);
        Tree tree = new Tree(state);
        tree.createTree(3);

        // Example: 
        int maxMove = 0;
        int depth = 4;
        int maxValue =0;
        int counter =0;
        Node node = new Node(new State(board,0), null);
        node.createChildren();
        List<Node> children = new ArrayList<>();
        children.addAll(node.getChildren());
        //Create children of a node based on the current board
        // If no moves have been made, then it means that player 1 (the maximiser will start)
        if (board.getMovesCount() == 0){
            for(Node n: children){
                //check for the best child and make the move based that
                int eval= min(n.getState().getBoard(),depth);
                if (eval>maxValue){
                    eval=maxValue; 
                    maxMove=counter;
                }
                counter++;
            }
        }
        // If an even number of moves have been made, then it means that player 1 (the maximiser will play a move)
        else if (board.getMovesCount() % 2 == 0){
            for(Node n: children){
                int eval= min(n.getState().getBoard(),depth);
                if (eval>maxValue){
                    eval=maxValue; 
                    maxMove=counter;
                }
                counter++;
            }
        }
        else{
            // If an odd number of moves have been made, then it means that player 2 (the minimiser will play a move)
            for(Node n: children){
                int eval= max(n.getState().getBoard(),depth);
                if (eval>maxValue){
                    eval=maxValue; 
                    maxMove=counter;
                }
                counter++;
            }
        }
        /*
        This is obviously not enough (this is depth 1)
        Your assignment is to create a data structure (tree) to store the gameboards such that you can evaluate a higher depths.
        Then, use the minmax algorithm to search through this tree to find the best move/action to take!
        */

        return maxMove;
    }
    
}
