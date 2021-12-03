import java.util.*;
import java.awt.Color;

//TODO: Sometimes no edges
//TODO: arrange 
//TODO: EdgeToNodeRatio not accurate
//TODO: display edgeWeights


class Main
{
    Graph graph;

    public static void main(String args[])
    {
        Graph graph = new Graph();

        //graph.standardNodes(Color.orange, 30);

        //graph.makeGraph(50, Color.white, 30, 1.0);
        graph.makeTree(50, 2, Color.white, 30);
        
        LinkedList<Node> nodes = graph.giveNodes();

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