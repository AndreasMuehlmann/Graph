import java.util.*;

public class Node{

    int index;
    int color;
    int radius;
    int[] position;
    LinkedList<Node> edges = new LinkedList<Node> ();
    Data data;

    public Node(int index, int color, int radius, int[] position, LinkedList<Node> edges){
        this.index = index;
        this.color = color;
        this.radius = radius;
        this.position = position;
        this.edges = edges;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int giveColor(){
        return this.color;
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

    public int giveIndex(){
        return this.index;
    }

    public Data giveData(){
        return this.data;
    }

   //public int[] GivePosition(){
   //    //return Position;
   //}
}
