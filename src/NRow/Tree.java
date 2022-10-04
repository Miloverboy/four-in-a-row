package NRow;

public class Tree {
    Node root;

    public Tree(State state) {
        root = new Node(state, null);
    }

    public void createTree(int depth) {
        root.createChildren(depth);
        
    }
}
