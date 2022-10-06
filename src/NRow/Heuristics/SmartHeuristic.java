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
        System.out.println(super.evaluate(player, board));
        int val = super.evaluate(opponent, board);
        //System.out.println(super.evaluate((player + 1) % 2, board));
        System.out.println(val);
        /*posScore = super.evaluate(player, board);
        if (posScore < Integer.MAX_VALUE)
        //negScore = super.evaluate((player + 1) % 2, board);*/

        return (super.evaluate(player, board) - super.evaluate(opponent, board));
    }
}
