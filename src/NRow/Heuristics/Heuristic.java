package NRow.Heuristics;

import NRow.Board;

/**
 * Abstract class defining Heuristic
 */
public abstract class Heuristic {
    protected int gameN;

    public Heuristic(int gameN) {
        this.gameN = gameN;
    }

    /**
     * Determines the best column for the next move
     * @param player the player for which to compute the heuristic values
     * @param board the board to evaluate
     * @return column integer
     */
    public int getBestAction(int player, Board board) {
        int[] utilities = evalActions(player, board);
        int bestAction = 0;
        for (int i = 0; i < utilities.length; i++) {
            bestAction = utilities[i] > utilities[bestAction] ? i : bestAction;
        }
        return bestAction;
    }

    /**
     * Helper function to determines the utility of each column
     * @param player the player for which to compute the heuristic values
     * @param board the board to evaluate
     * @return array of size boardWidth with utilities
     */
    public int[] evalActions(int player, Board board) {
        int[] utilities = new int[board.width];
        for (int i = 0; i < board.width; i++) {
            utilities[i] = evaluateAction(player, i, board);
        }
        return utilities;
    }

    /**
     * Helper function to assign a utility to an action
     * @param player the player for which to compute the heuristic values
     * @param action the action to evaluate
     * @param board the board to evaluate
     * @return the utility, negative 'infinity' if the move is invalid
     */
    protected int evaluateAction(int player, int action, Board board) {
        if (board.isValid(action)) {
            int value = evaluate(player, board.getNewBoard(action, player));
            return value;
        } else return Integer.MIN_VALUE;
    }

    public String toString() {
        return this.name();
    }

    protected abstract String name();

    /**
     * Implement this method in the heuristic classes
     * @param player the player for which to compute the heuristic value
     * @param board the board to evaluate
     * @return heuristic value for the board state
     */
    public abstract int evaluate(int player, Board board);
}
