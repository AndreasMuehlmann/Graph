import java.util.*;
import java.awt.Color;

//TODO: Sometimes no edges
//TODO: arrange 
//TODO: EdgeToNodeRatio not accurate
//TODO: display Data in Node
//TODO: EdgeColors
//TODO: DFS isn't animated right
//TODO: Refactoring (make a diagramm for overview)


class Main
{
    Graph graph;

    public static void main(String args[])
    {
        Graph graph = new Graph(new LinkedList<Node>(), 2400, 1300, 0.5);

        //graph.standardNodes(Color.orange, 30);

        graph.makeGraph(50, Color.white, 30);

        //graph.makeDirectedAcyclicGraph(10, Color.white, 30);

        LinkedList<Node> nodes = graph.giveNodes();

        graph.DFS(nodes.get(4), nodes.get(6), Color.blue, 0.5);
        graph.delay(2);

        graph.randomColoring(0.01);
        graph.setEdgeColors(Color.black);
        graph.setNodeColors(Color.white);


        graph.DFS(nodes.get(1), nodes.get(4), Color.blue, 1);

        graph.delay(5);
        System.exit(0);
    }
}