package NRow;

public class State {
    private static final int MIN_VALUE = -2147483648;
    private static final int MAX_VALUE = 2147483647;
    int gameN;
    Board board;
    int player;
    int prevMove;
    int bestMove;

    public State (Board board, int player, int prevMove, int gameN){
        this.board= board;
        this.player= player;
        this.prevMove = prevMove;
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer() {
        return player;
    }

    public void setBestMove(int i) {
        this.bestMove = i;
    }

    public int getbestMove(){
        return this.bestMove;
    }

    public int getPrevMove(){
        return this.prevMove;
    }

    public int getGameN(){
        return this.gameN;
    }



}
