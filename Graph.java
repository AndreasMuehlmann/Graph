import java.util.*;
import java.awt.*;
public class Graph extends Frame{

    LinkedList<Node> Nodes = new LinkedList<Node>();
    int WindowWidth;
    int WindowHeight;

    Graph(LinkedList<Node> Nodes, int WindowWidth, int WindowHeight){
        this.Nodes = Nodes;
        this.WindowHeight = WindowHeight;
        this.WindowWidth = WindowWidth;
        Graphics Graphics =  new Graphics(null, WindowWidth, WindowHeight);
        }

    public void Draw(LinkedList<Node> Nodes){
        
        Iterator<Node> NodesIterator = Nodes.iterator();
        while(NodesIterator.hasNext()){
            NodesIterator.next().Draw();
        }
    }

    public LinkedList<Node>  GiveNodes(){
        return Nodes;
    }

    public void AddNode(Node Node){

    }

 //public Node RemoveNode(int Index){
 //    
 //}
//
 //public int GiveNextIndex(){
//
 //}
//
 //public void Arrange(){
 //   //Have to figure out 
 //}
//
 //public Node GiveRandomNode(){
 //    //Have to figure out
 //}
}
