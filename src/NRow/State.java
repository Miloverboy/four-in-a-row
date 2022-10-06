package NRow;

public class State {
    private static final int MIN_VALUE = -2147483648;
    private static final int MAX_VALUE = 2147483647;
    Board board;
    int player;
    int alpha;
    int beta;

    public State (Board board, int player){
        this.board= board;
        this.player= player;
        this.alpha= MIN_VALUE;
        this.beta= MAX_VALUE;
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer() {
        return player;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    public void setBeta(int beta){
        this.beta = beta;
    }



}
