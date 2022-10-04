package NRow;

public class State {
    Board board;
    int player;

    public State (Board board, int player){
        this.board= board;
        this.player= player;
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer() {
        return player;
    }



}
