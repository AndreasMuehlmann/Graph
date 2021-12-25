import java.util.*;
import java.awt.*;

//TODO: fix Graph generators
//TODO: display edgeWeights
//TODO: README

public class Main {
    

    public static void main(String args[])
    {
        Graph graph = new Graph();

        //graph.standardNodes(Color.orange, 30);
        //graph.makeGraph(50, Color.white, 30, 1.0);
        //graph.makeDirectedAcyclicGraph(50, Color.white, 30, 1);
        graph.makeTree(50, 2, Color.white, 30);
        graph.arrangeTree();

        Animation animation = new Animation(graph);
        animation.animate();
       //System.exit(0);
    }
}
