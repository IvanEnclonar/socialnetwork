import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;

public class Network {
    Node[] nodes;

    public Network(int size) {
        nodes = new Node[size];
    }

    public void createUser(int n) {
        if (nodes[n] == null) {
            nodes[n] = new Node(n);
        }
    }

    public boolean doesExist(int n) {
        if (n >= nodes.length || n < 0) {
            return false;
        } else if (nodes[n] == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addConnection(int person1, int person2) {
        if (person1 == person2) {
            return false;
        } else {
            createUser(person1);
            createUser(person2);
            nodes[person1].connectNodes(nodes[person2]);
            nodes[person2].connectNodes(nodes[person1]);
            return true;
        }
    }

    public void getUserFriends(int userID) {
        nodes[userID].displayFriends();
    }

    public void displayAll() {
        for (Node n : nodes) {
            if (n != null) {
                System.out.println(n.getID());
            } else {
                System.out.println("null");
            }
        }
    }

    public void getConnection(int person1, int person2) {
        bfs(nodes[person1], nodes[person2]);
    }

    public boolean backtracking(HashMap<Node, Node> parentsMap, Node person1, Node person2) {
        Node current = person2;
        while (current != person1) {
            System.out.println(
                    "Person " + current.getID() + " is friends with person " + parentsMap.get(current).getID());
            current = parentsMap.get(current);
        }
        return true;
    }

    public boolean bfs(Node person1, Node person2) {
        Queue<Node> queue = new LinkedList<>(); // FIFO
        HashMap<Node, Node> parentsMap = new HashMap<>();

        queue.add(person1);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.getID() == person2.getID()) {
                // print path
                System.out.println("\nA path exists between " + person1.getID() + " and " + person2.getID() + ": ");
                backtracking(parentsMap, person1, person2);
                return true;
            } else {
                for (Node friend : current.getFriends()) {
                    if (parentsMap.containsKey(friend)) {
                        continue;
                    } else {
                        parentsMap.put(friend, current);
                    }
                    queue.add(friend);
                }
            }
        }
        return false;
    }
}
