import java.util.*;

        // colors are mapped to numbers 
        // 1 rot
        // 2 orange
        // 3 gelb
        // 4 grün
        // 5 blau
        // 6 pink
        // a number greater than 6 is always white


class Main
{
    Graph graph;

    public static void main(String args[])
    {
        Graph graph = new Graph(new LinkedList<Node>(), 1200, 900, 2.0);

        //graph.standardNodes(1, 30);

        graph.makeRandomNodes(10, -1, 30);

        LinkedList<Node> nodes = graph.giveNodes();

       //graph.DFS(nodes.get(2), nodes.get(4), 10);
       //graph.randomColoring();

        graph.DFS(nodes.get(1), nodes.get(2), 10);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            throw new UnsupportedOperationException("Interrupts not supported.", ie);
        }

        System.exit(0);
    }
}