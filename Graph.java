import java.util.*;
public class Graph{

    LinkedList<Node> nodes = new LinkedList<Node>();
    Graphic graphic;

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        this.nodes = nodes;
        graphic = new Graphic(nodes, 1000, 1000);
    }

    public LinkedList<Node>  giveNodes(){
        return nodes;
    }

    public void addNode(Node Node){
        nodes.add(Node);
    }

   //public int GiveNextIndex(){
    
   //}

    public void Arrange(){
        //Have to figure out 
    }

    public void makeRandomNodes(int amount){
        Random random = new Random();   
        int startSize = nodes.size();

        for (int i = 0; i < amount; i++){
            int color = random.nextInt(graphic.colors.length); 
            int[] position = {0, 0};
            nodes.add(new Node(i, color, 20, position, null));
        }

        LinkedList<Node> edges = new LinkedList<Node>();
        for (int j = 0; j < amount; j++){
            int edgesAmount = random.nextInt(amount);
            for (int k = 0; k <  edgesAmount; k++){
                edges.add(nodes.get(random.nextInt(amount) + startSize));
                nodes.get(nodes.size() - 1).setEdges(edges);
            }
        }
    }
}