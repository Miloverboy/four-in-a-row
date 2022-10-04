package NRow;

import java.util.List;

public class Node {
    State state;
    Node parent;
    List<Node> children;


    public Node (State state, Node parent, List<Node> children){
        this.state= state;
        this.parent= parent;
        this.children= children; 
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

}
