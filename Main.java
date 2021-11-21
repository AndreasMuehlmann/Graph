import java.util.*;

class Main{
    Graph graph;

    public static void main(String args[]) throws InterruptedException{
        Graph graph = new Graph(new LinkedList<Node>(), true, 1200, 900);
        graph.makeRandomNodes(40);
        LinkedList <Node> nodes = graph.giveNodes();
        for (int i = 0; i < 5; i++){
            LinkedList<Node> path = graph.DFS(nodes.get(2+i), nodes.get(i));
            System.out.println("path: " + path);
        }
    }
}