package NRow;

import java.util.LinkedList;
import java.util.List;
import NRow.Heuristics.SimpleHeuristic;

import NRow.Heuristics.Heuristic;

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

    public void createChildren(int depth) {
        int previousPlayer = this.state.getPlayer();
        int nextPlayer;
        if (previousPlayer == 0)
            nextPlayer = 1;
        else if (previousPlayer == 1)
            nextPlayer = 2;
        else 
            nextPlayer = 1;
        Board parentBoard = this.state.getBoard();
        Node child;
        State newState;
        Board newBoard;

        // create all children/possible boards if they are valid.

        for (int i = 0; i < parentBoard.width; i++) {
            if (parentBoard.isValid(i)) {
                newBoard = parentBoard.getNewBoard(i, nextPlayer);
                newState = new State(newBoard, nextPlayer);
                child = new Node(newState, this);
                this.children.add(child);
            }
        }

        // recursively create children of children, if max depth isn't reached.

        if (depth > 0) {
            for (int i = 0; i < this.children.size(); i++) {
                children.get(i).createChildren(depth-1);
            }
        }  
    }

    /*
            int[] utilities = evalActions(player, board);
            int bestAction = 0;
            for (int i = 0; i < utilities.length; i++) {
                bestAction = utilities[i] > utilities[bestAction] ? i : bestAction;*/

    public void maximise() {

        //remove:
        Heuristic heuristic = new SimpleHeuristic(4);

        if (this.children.size() == 0) {
            state.setAlpha(heuristic.evaluateBoard(state.getPlayer(), state.getBoard()));
        }
        
        for (int i = 0; i < this.children.size(); i++) {

        }
    }

    public void minimise() {

    }
}
