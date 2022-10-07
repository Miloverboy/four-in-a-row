package NRow;

public class State {
    Board board;
    int player;
    int value;

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

    public void setValue(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }


}
