import java.util.*;

public class Node{

    String name;
    int nodeColor;
    int radius;
    int[] position;
    LinkedList<Node> edges = new LinkedList<Node> ();
    HashMap<String, Integer> edgeColors = new HashMap<String, Integer> ();
    Data data;

    public Node(String name, int nodeColor, int radius, int[] position, LinkedList<Node> edges){
        this.name = name;
        this.nodeColor = nodeColor;
        this.radius = radius;
        this.position = position;
        this.edges = edges;
        setEdgeColors ();
    }

    private void setEdgeColors(){
        for (Node edge : edges)
            edgeColors.put(edge.name, -1);
    }

    public void setEdgeColor(String nodeName, int color){
        edgeColors.put(nodeName, color);
    }

    public void setNodeColor(int color){
        this.nodeColor = color;
    }

    public int giveColor(){
        return this.nodeColor;
    }

    public void addEdge(Node node){
        this.edges.add(node);
    }

    public void setEdges(LinkedList<Node> edges){
        this.edges = edges;
    }

    public LinkedList<Node> giveEdges(){
        return this.edges;
    }

    public String giveName(){
        return this.name;
    }

    public Data giveData(){
        return this.data;
    }

   public int[] givePosition(){
       return position;
   }
}
