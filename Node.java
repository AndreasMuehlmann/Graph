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

    public void ChangeColor(int color){
        this.color = color;
    }

    public int giveColor(){
        return color;
    }

    public void setEdges(LinkedList<Node> edges){
        this.edges = edges;
    }

    public int giveIndex(){
        return index;
    }

    public LinkedList<Node> giveEdges(){
        return edges;
    }

    public Data giveData(){
        return data;
    }

   //public int[] GivePosition(){
   //    //return Position;
   //}
}

interface Data{
    
}
