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
        // if maximum depth has been reached or a winning state has been reached, 
        // meaning there are no further children, evaluate the current node.
        if (depth == 0 || node.getChildren().size() == 0) { 
            return this.heuristic.evaluateBoard(node.getState().getPlayer(), node.getState().getBoard());
        } // we want to maximize our score, choose the best child based on minimizers move
        if (maximizingPlayer) {
            int bestValue = Integer.MIN_VALUE + 1000;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, false);
                if (currentValue > bestValue) {
                    bestValue = currentValue;
                    // store which child is the best child in the parent node
                    node.getState().setBestMove(n.getState().getPrevMove()); 
                }
            }
            return bestValue;
        } else { // we want to minimize our score, choose the best child based on maximizers move
            int bestValue = Integer.MAX_VALUE - 1000;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, true);
                if (currentValue < bestValue) {
                    bestValue = currentValue;
                    // store which child is the best child in the parent node
                    node.getState().setBestMove(n.getState().getPrevMove()); 
                }
            } 
            return bestValue;
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
        
        State startState = new State(board, this.playerId, -1, this.gameN);
        //Create children of a node based on the current board
        Tree tree = new Tree(startState);
        tree.createTree(depth);
        Node node = tree.getRoot();
        int goal = minimax(node, depth, true);
        int maxMove = node.getState().getbestMove();
        return maxMove;
    }
    
}
