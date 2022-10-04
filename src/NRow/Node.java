package NRow;

import java.util.LinkedList;
import java.util.List;

public class Node {
    State state;
    Node parent;
    List<Node> children;


    public Node (State state, Node parent){
        this.state= state;
        this.parent= parent;
        this.children= new LinkedList<Node>(); 
    }
    public State getState(){
        return state;
    }

    public Node getParent(){
        return parent;
    }

    public List<Node> getChildren(){
        return children;
    }

    public void setState(State newState){
        this.state= newState;
    }

    public void setParent(Node newParent){
        this.parent= newParent;
    }

    public void createChildren() {
        int nextPlayer = (this.state.getPlayer() == 0) ? 1 : 0;
        Board parentBoard = this.state.getBoard();
        Node child;
        State newState;
        Board newBoard;
        for (int i = 0; i < parentBoard.width; i++) {
            if (parentBoard.isValid(i)) {
                newBoard = parentBoard.getNewBoard(i, nextPlayer);
                newState = new State(newBoard, nextPlayer);
                child = new Node(newState, this);
                this.children.add(child);
            }
        }
    }
}
