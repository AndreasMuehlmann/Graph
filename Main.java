import java.util.*;

        // colors are mapped to numbers 
        // 0 rot
        // 1 orange
        // 2 gelb
        // 3 grün
        // 4 blau
        // 5 pink
        // a number greater than 6 is always white

//TODO: Sometimes no edges

class Main
{
    Graph graph;

    public static void main(String args[])
    {
        Graph graph = new Graph(new LinkedList<Node>(), 2400, 1300, 1.0);

        graph.standardNodes(1, 30);

        graph.makeRandomNodes(10, -1, 30);

        LinkedList<Node> nodes = graph.giveNodes();

        graph.DFS(nodes.get(2), nodes.get(4), 10);
        graph.randomColoring();

        graph.DFS(nodes.get(1), nodes.get(2), 0);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            throw new UnsupportedOperationException("Interrupts not supported.", ie);
        }

        System.exit(0);
    }
}