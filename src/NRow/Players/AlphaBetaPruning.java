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
        if (depth == 0){
            int eval = heuristic.evaluateBoard(1, node.getState().getBoard());
            return eval;
        }
        else if (max) {
            // if you are maximizer, look at all direct children nodes and calculate value based 
            // on the minimzer's best move 
            node.createChildren(1);
            List<Node> children = node.getChildren();
            for(Node n:children){
                alpha= Math.max(alpha,alphaBetaEval(alpha,beta,n,depth-1,false));
                if (beta <= alpha)
                    return alpha; 
            }
            return alpha;
        }
        else {
            node.createChildren(1);
            List<Node> children = node.getChildren();
            for(Node n:children){
                beta= Math.min(beta,alphaBetaEval(alpha,beta,n,depth-1,true));
                if(beta <= alpha)
                    return beta;
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
        node.createChildren(1);
        int maxMove=0;
        int maxValue = 0;
        int counter =0;
        List<Node> children = node.getChildren();
        //Create and put the direct children of the root in a list 
        for(Node n:children){
            int eval = alphaBetaEval( Integer.MIN_VALUE, Integer.MAX_VALUE,n, depth -1, false);
            // check which child is the best based on what the other player (the minimizer)
            // wants to do (that is why parameter max is set to false)
            if(eval>=maxValue){
                //if there is a child that generates a higher value than the max value we have found so 
                //far, we store it as a new maxValue and our maxMove would becomes the counter which is
                //equal to the column of the move that would take us to that node
                maxValue = eval;
                maxMove = counter;
            }
            counter++;
        }
        return maxMove;
}
}
