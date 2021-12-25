import java.awt.*;

//TODO: README

public class Main {
    

    public static void main(String args[])
    {
        Graph graph = new Graph();

        //graph.standardNodes(Color.orange, 30);
        //graph.makeGraph(50, Color.white, 30, 1.0);
        //graph.makeDirectedAcyclicGraph(50, Color.white, 30, 1);
        graph.makeTree(100, 4, Color.white, 30);
        graph.arrangeTree();

        Animation animation = new Animation(graph);
        animation.animate();
       //System.exit(0);
    }
}
