import java.util.*;
import java.awt.Color;

//TODO: Sometimes no edges
//TODO: EdgeToNodeRatio not accurate
//TODO: display edgeWeights
//TODO: arangeTree: fromula for radius make better


class Main
{
    Graph graph;

    public static void main(String args[])
    {
        Graph graph = new Graph();

        //graph.standardNodes(Color.orange, 30);

        //graph.makeGraph(50, Color.white, 30, 1.0);
        LinkedList<Node> nodes = graph.giveNodes();
        graph.makeTree(50, 2, Color.white, 30);
        graph.arrangeTree();
        graph.delay(1);


        graph.DFSAnimated(nodes.get(0), nodes.get(7), Color.blue, 0.05);
        graph.delay(2);

        graph.randomColoring(0.01);
        graph.setEdgeColors(Color.black);
        graph.setNodeColors(Color.white);


        graph.DFSAnimated(nodes.get(0), nodes.get(9), Color.blue, 1);

        graph.delay(5);
        System.exit(0);
    }
}