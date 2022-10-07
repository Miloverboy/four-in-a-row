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
        int bestDepth = depth;
        if (depth == 0 || node.getChildren().size() == 0) {
            return this.heuristic.evaluateBoard(node.getState().getPlayer(), node.getState().getBoard());
        }
        if (maximizingPlayer) {
            int bestValue = Integer.MIN_VALUE + 1000;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, false);
                if (currentValue > bestValue) {
                    bestValue = currentValue;
                    bestDepth = n.getMaxDepth();
                    node.getState().setBestMove(n.getState().getPrevMove());
                }/*
                if (currentValue == bestValue && n.getMaxDepth() < bestDepth) {
                    bestDepth = n.getMaxDepth();
                    bestValue = currentValue;
                    node.getState().setBestMove(n.getState().getPrevMove());
                }*/
            }
            return bestValue;

        } else {
            int bestValue = Integer.MAX_VALUE - 1000;
            int currentValue;
            for (Node n: node.getChildren()) {
                currentValue = minimax(n, depth - 1, true);
                if (currentValue < bestValue) {
                    bestValue = currentValue;
                    bestDepth = n.getMaxDepth();
                    node.getState().setBestMove(n.getState().getPrevMove());
                }
                /*if (currentValue == bestValue && n.getMaxDepth() < bestDepth) {
                    bestDepth = n.getMaxDepth();
                    bestValue = currentValue;
                    node.getState().setBestMove(n.getState().getPrevMove());
                }*/
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
        Tree tree = new Tree(startState);
        tree.createTree(3);

        // Example: 
        int maxMove = 0;
        int maxValue =0;
        int counter =0;
        Node node = tree.getRoot();

        //Create children of a node based on the current board
        // If no moves have been made, then it means that player 1 (the maximiser will start)

        int goal = minimax(node, depth, true);
        maxMove = node.getState().getbestMove();
        System.out.println(goal);
        
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
