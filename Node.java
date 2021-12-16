import java.util.*;
import java.awt.Color;

public class Node{

    String name;
    Color color;
    int radius;
    int[] position;
    HashMap<String, Node> edges = new HashMap<String, Node> ();
    HashMap<String, Color> edgeColors = new HashMap<String, Color> ();
    Data data;

    public Node(String name, Color color, int radius, int[] position, HashMap<String, Node> edges){
        this.name = name;
        this.color = color;
        this.radius = radius;
        this.position = position;
        this.edges = edges;
        setEdgeColors(Color.black);
    }

    public void setEdgeColors(Color color){
        for (Node edge : edges.values())
            edgeColors.put(edge.name, color);
    }

    public void setEdgeColor(String edgeName, Color color){
        edgeColors.put(edgeName, color);

        Node edge = edges.get(edgeName);
        Node edgeOfEdge = edge.edges.get(this.name);
        if (edgeOfEdge != null && edge.giveEdgeColor(this.name) != giveEdgeColor(edgeName)){
            edge.setEdgeColor(this.name, color);
        }
    }

    public void setNodeColor(Color color){
        this.color = color;
    }

    public Color giveNodeColor(){
        return this.color;
    }

    public Color giveEdgeColor(String edgeName){
        return edgeColors.get(edgeName);
    }

    public void addEdge(Node edge){
        this.edges.put(edge.name, edge);
        setEdgeColor(edge.name, Color.black);
    }

    public void setEdges(HashMap<String, Node>edges){
        this.edges = new HashMap<String, Node>(edges);
        setEdgeColors(Color.black);
    }

    public HashMap<String, Node> giveEdges(){
        return this.edges;
    }

    public String giveName(){
        return this.name;
    }

    public Data giveData(){
        return this.data;
    }

    public void setPosition(int x, int y){
        position[0] = x;
        position[1] = y;
    }

   public int[] givePosition(){
       return position;
   }

   public void setRadius(int radius)
   {
       this.radius = radius;
   }
}
