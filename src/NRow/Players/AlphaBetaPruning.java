package NRow.Players;

import java.util.ArrayList;
import java.util.List;

import NRow.Board;
import NRow.Heuristics.Heuristic;
import NRow.Heuristics.SimpleHeuristic;
import NRow.Node;
import NRow.State;
import NRow.Tree;

public class AlphaBetaPruning extends PlayerController {
    private int depth;
    public int move; 

    public AlphaBetaPruning(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    

    public int alphaBetaEval (int alpha, int beta, Node node, int depth, boolean max){
        int currentValue;
        if (depth == 0){
            int eval = heuristic.evaluateBoard(1, node.getState().getBoard());
            return eval;
        }
        else if (max) { // if you are maximizer, look at all direct children nodes and calculate value based 
                        // on the minimzer's best move 
            node.createChildren(1);
            List<Node> children = node.getChildren();
            for(Node n:children){
                currentValue = alphaBetaEval(alpha,beta,n,depth-1,false);
                if (currentValue > alpha) {
                    alpha = currentValue;
                    // store which child is the best child in the parent node
                    node.getState().setBestMove(n.getState().getPrevMove()); 
                    // if beta <= alpha, we don't need to look at the other children
                    if(beta <= alpha)
                    return alpha;
                }
            }
            return alpha;
        }
        else { // if you are minizer, look at all direct children nodes and calculate value based 
               // on the maximzer's best move
            node.createChildren(1);
            List<Node> children = node.getChildren();
            for(Node n:children){
                currentValue = alphaBetaEval(alpha,beta,n,depth-1,true);
                if (currentValue < beta) {
                    beta = currentValue;
                    // store which child is the best child in the parent node
                    node.getState().setBestMove(n.getState().getPrevMove()); 
                    // if beta <= alpha, we don't need to look at the other children
                    if(beta <= alpha)
                    return beta;
                }
            }
             return beta;

        }

    }

    /**
   * Implement this method yourself!
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        State startState = new State(board, this.playerId, -1, this.gameN);
        Node node = new Node(startState,null);
        node.createChildren(depth);
        int maxMove=0;
        int goal = alphaBetaEval( Integer.MIN_VALUE, Integer.MAX_VALUE, node, depth, true);
        maxMove = node.getState().getbestMove();
        return maxMove;
}
}
