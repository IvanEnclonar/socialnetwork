import java.util.ArrayList;

public class Node {
    private ArrayList<Node> adjacents = new ArrayList<>();
    private int id;

    public Node(int id) {
        this.id = id;
    }

    public boolean connectNodes(Node node) {
        if (!adjacents.contains(node)) {
            adjacents.add(node);
            return true;
        } else {
            return false;
        }
    }

    public int getID() {
        return this.id;
    }

    public void displayFriends() {
        System.out.println("\nPerson " + id + " has " + adjacents.size() + " friends!");
        System.out.print("List of Friends: ");
        for (Node friend : adjacents) {
            System.out.print(friend.getID() + " ");
        }
    }

    public ArrayList<Node> getFriends() {
        return adjacents;
    }
}
