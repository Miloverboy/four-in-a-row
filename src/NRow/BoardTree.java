package NRow;

import java.util.LinkedList;
import java.util.List;

public class BoardTree {
    private List<BoardTree> children;
    private Board board;
    private int alpha;
    private int beta;
    private BoardTree parent;
    private int player;

    public BoardTree(BoardTree boardtree, Board board, int player) {
        this.parent = boardtree;
        this.board = board;
        this.children = new LinkedList<BoardTree>();
        this.player = player;
    }

    public void createChildren() {
        Board newBoard;
        BoardTree child;
        int nextPlayer = (player == 0) ? 1 : 0;
        for (int i = 0; i < board.width; i++) {
            if (board.isValid(i)) {
                newBoard = board.getNewBoard(i, nextPlayer);
                child = new BoardTree(this, newBoard, nextPlayer);
                this.children.add(child);
            }
        }
    }

    public List<BoardTree> getChildren() {
        return children;
    }

    public Board getBoard() {
        return board;
    }
}