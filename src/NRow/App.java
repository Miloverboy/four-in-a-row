package NRow;

import NRow.Heuristics.SimpleHeuristic;
import NRow.Heuristics.SmartHeuristic;
import NRow.Players.AlphaBetaPruning;
import NRow.Players.HumanPlayer;
import NRow.Players.MinMaxPlayer;
import NRow.Players.PlayerController;

public class App {
    public static void main(String[] args) throws Exception {
        int gameN = 4;
        int boardWidth = 7;
        int boardHeight = 6;

        PlayerController[] players = getPlayers(gameN);

        Game game = new Game(gameN, boardWidth, boardHeight, players);
        game.startGame();
        //game.testGame();
    }

    /**
     * Determine the players for the game
     * @param n
     * @return an array of size 2 with two Playercontrollers
     */
    private static PlayerController[] getPlayers(int n) {
        SimpleHeuristic heuristic1 = new SimpleHeuristic(n);
        SimpleHeuristic heuristic2 = new SimpleHeuristic(n);

        SmartHeuristic smHeuristic1 = new SmartHeuristic(n);
        SmartHeuristic smHeuristic2 = new SmartHeuristic(n);

        PlayerController human = new HumanPlayer(1, n, heuristic1);
        PlayerController human2 = new HumanPlayer(2, n, heuristic2);

        PlayerController minMax1 = new MinMaxPlayer(1, n, 3, heuristic1);
        PlayerController minMax2 = new MinMaxPlayer(2, n, 3, heuristic2);

        //TODO: Implement other PlayerControllers (MinMax, AlphaBeta)
        PlayerController alpha = new AlphaBetaPruning(1, n, 3, heuristic1);
        PlayerController beta = new AlphaBetaPruning(2, n, 3, heuristic2);

        //PlayerController[] players = { human, human2 };
        PlayerController[] players = { minMax1, minMax2 };

        return players;
    }
}
