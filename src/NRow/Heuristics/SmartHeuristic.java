package NRow.Heuristics;

import NRow.Board;

public class SmartHeuristic extends SimpleHeuristic {

    public SmartHeuristic(int gameN) {
        super(gameN);
    }

    @Override
    protected String name() {
        return "Smart";
    }
    
    @Override
    protected int evaluate(int player, Board board) {
        int opponent = (player == 1) ? 2 : 1;
        int myScore = super.evaluate(opponent, board); // MAX_VALUE - MIN_VALUE produces weird results
        if (myScore == Integer.MAX_VALUE || myScore == Integer.MIN_VALUE) {
            return myScore;
        }               
        return (myScore - super.evaluate(opponent, board));
    }
}
