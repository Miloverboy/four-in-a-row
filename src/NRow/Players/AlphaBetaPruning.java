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
            return heuristic.evaluateBoard(1, node.getState().getBoard());
        }
        else if (max) {
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
                beta= Math.min(alpha,alphaBetaEval(alpha,beta,n,depth-1,false));
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
        Node node = new Node(new State(board,1),null);
        node.createChildren(1);
        int maxMove=0;
        int maxValue = 0;
        int counter =0;
        List<Node> children = node.getChildren();
        for(Node n:children){
            int eval = alphaBetaEval(100,-100,n, depth -1, true);
            if(eval>maxValue){
                maxValue = eval;
                maxMove = counter;
            }
            counter++;
        }
        return maxMove;
    }
    

}
