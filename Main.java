import java.util.*;

class Main{
    public static void main(String args[]){
        Graph graph = new Graph(new LinkedList<Node>(), 1000, 1000);
        graph.makeRandomNodes(20);
        graph.makeRandomNodes(10);
    }
}
