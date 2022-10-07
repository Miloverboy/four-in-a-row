package NRow.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import NRow.Board;
import NRow.Heuristics.Heuristic;
import NRow.Heuristics.SimpleHeuristic;
import NRow.Node;
import NRow.State;
import NRow.Tree;

public class MinMaxPlayer extends PlayerController {
    private int depth;
    public int move; 

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    public int minimax(Node node, int depth, boolean maximizingPlayer) {
        if (depth == 0 || node.getChildren() == null) {
            return this.heuristic.evaluateBoard(node.getState().getPlayer(), node.getState().getBoard());
        }
        if (maximizingPlayer) {
            int bestValue = Integer.MIN_VALUE + 100;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, false);
                bestValue = Math.max(currentValue, bestValue);
                if (bestValue == currentValue) {
                    node.getState().setBestMove(n.getState().getPrevMove());
                }
            }
            return bestValue;

        } else {
            int bestValue = Integer.MAX_VALUE - 100;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, true);
                bestValue = Math.min(currentValue, bestValue);
                if (bestValue == currentValue) {
                    node.getState().setBestMove(n.getState().getPrevMove());
                }
            }
            return bestValue;
        }
    }

    /*public Node max(Node node, int depth){
        int maxValue = Integer.MIN_VALUE + 100;
        Node maxNode = null;
        List<Node> children = node.getChildren();

        // if there are no children, return itself.

        if (children == null) {
            return node;
        }

        if(depth <= 1){
            // if we reached the final layer we wanna reach, we make simple eval and return the best one
            for (Node n: children) {
                int eval = this.heuristic.evaluateBoard(node.getState().getPlayer(), n.getState().getBoard());
                n.getState().setAlpha(eval);
                System.out.print(eval + " ");
                if (eval > maxValue){
                        maxValue = eval; 
                        maxNode = n;
                    }
                }

            System.out.println(" Best value (max): " + maxValue);
        
            return maxNode;
        }
        else {
            //if there are still more depths to discover we make a move based on what the minimizer would ideally do in his next move
            //based on all the possible moves we have 

            
            
            for (Node n: children) {
                n = min(n, depth-1);
                int eval = n.getState().getAlpha();
                if (eval > maxValue){
                    maxValue = eval;
                    maxNode = n;
                }
            }
            return maxNode;
       }
}

public Node min(Node node, int depth){
    int minValue = Integer.MAX_VALUE - 100;
    Node minNode = null;
    if(depth == 0){
        // if we reached the final layer we wanna reach, we make simple eval and return the best one
        for (Node n: node.getChildren()) {
            int eval = this.heuristic.evaluateBoard(node.getState().getPlayer(), n.getState().getBoard());
            n.getState().setBeta(eval);
            System.out.print(eval + " ");
            if (eval < minValue){
                    minValue = eval; 
                    minNode = n;
                }
            }

        System.out.println(" Best value (min): " + minValue);
    
        return minNode;
    }
    else {
        
        //if there are still more depths to discover we make a move based on what the maximizer would ideally do in his next move 
        //based on all the possible moves we have 

        node.createChildren(depth);

        for (Node n: node.getChildren()) {
            int eval = n.getState().getBeta();
            if (eval < minValue){
                minValue = eval;
                minNode = n;
            }
        }
        return minNode;
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
        
        State startState = new State(board, 0, -1);
        Tree tree = new Tree(startState);
        tree.createTree(3);

        // Example: 
        int maxMove = 0;
        int depth = 4;
        int maxValue =0;
        int counter =0;
        Node node = new Node(startState, null);
        node.createChildren(3);
        List<Node> children = new ArrayList<>();
        children.addAll(node.getChildren());
        //Create children of a node based on the current board
        // If no moves have been made, then it means that player 1 (the maximiser will start)

        int goal = minimax(node, 3, true);
        maxMove = node.getState().getbestMove();
        
        //System.out.println(goal);

        /*
        if (this.playerId == 0){
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
        else if (this.playerId == 1){
            for(Node n: children){
                int eval= min(n.getState().getBoard(),depth);
                if (eval>maxValue){
                    eval=maxValue; 
                    maxMove=counter;
                }
                counter++;
            }
        } */
        /*
        This is obviously not enough (this is depth 1)
        Your assignment is to create a data structure (tree) to store the gameboards such that you can evaluate a higher depths.
        Then, use the minmax algorithm to search through this tree to find the best move/action to take!
        */

        return maxMove;
    }
    
}
