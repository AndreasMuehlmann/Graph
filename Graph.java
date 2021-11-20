import java.util.*;
public class Graph{

    LinkedList<Node> nodes = new LinkedList<Node>();
    Graphic graphic;
    int windowWidth;
    int windowHeight;
    int sideDistance;

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.sideDistance = 100;
        graphic = new Graphic(nodes, 1000, 1000);
    }

    public LinkedList<Node>  giveNodes(){
        return nodes;
    }

    public void addNode(Node Node){
        nodes.add(Node);
    }

    public void Arrange(){
        //Have to figure out 
    }

    public void makeRandomNodes(int amount){
        if (amount == 0){
            System.out.println("no nodes where added");
            return;
        }
        Random random = new Random();   
        int startSize = nodes.size();

        for (int i = 1; i <= amount; i++){
            int color = random.nextInt(graphic.giveColors().length);
            //int color = 0;
            int[] position = {sideDistance + random.nextInt(windowWidth - sideDistance * 2), sideDistance + random.nextInt(windowHeight - sideDistance * 2)};
            nodes.add(new Node(startSize + i, color, 30, position, new LinkedList<Node>()));
        }
        LinkedList<Node> edges = new LinkedList<Node>();
        for (int j = startSize; j < amount + startSize; j++){
            edges.clear();
            int edgesAmount = random.nextInt(amount);
            for (int k = 0; k <  edgesAmount; k++){
                edges.add(nodes.get(random.nextInt(nodes.size())));
            }
            nodes.get(j).setEdges(edges);
        }
        graphic.setNodes(nodes);
    }
}