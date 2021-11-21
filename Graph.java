import java.util.*;
import java.awt.Color;
public class Graph{

    Graphic graphic;
    LinkedList<Node> nodes = new LinkedList<Node>();
    boolean oneColor;
    double nodeToEdgeRatio;
    int windowWidth;
    int windowHeight;
    int sideDistance;

    public Graph(LinkedList<Node> nodes, boolean oneColor, int windowWidth, int windowHeight){
        this.nodes = nodes;
        this.oneColor = oneColor;
        this.nodeToEdgeRatio = 10.0;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.sideDistance = 50;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public void update(){
        graphic.update();
    }

    public LinkedList<Node>  giveNodes(){
        return nodes;
    }

    public void  setNodes(LinkedList<Node> nodes){
        this.nodes = nodes;
    }

    public void addNode(Node Node){
        nodes.add(Node);
    }
    
    public Color[] giveColors(){
        return graphic.giveColors();
    }

    public void Arrange(){
        //Have to figure out 
    }

    public void randomColoring(){
        Random random = new Random();
        for (int i = 0; i < nodes.size(); i++){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                throw new UnsupportedOperationException("Interrupts not supported.", ie);
            }

            nodes.get(random.nextInt(nodes.size())).setColor(random.nextInt(5));
        }
    }

    public void makeRandomNodes(int amount){
        if (amount == 0){
            System.out.println("no nodes where added");
            return;
        }
        Random random = new Random();   
        int startSize = nodes.size();

        for (int i = 0; i < amount; i++){
            int color = (oneColor) ? 0 : random.nextInt(graphic.giveColors().length);
            int[] position = {sideDistance + random.nextInt(windowWidth - sideDistance * 2), 
                sideDistance + random.nextInt(windowHeight - sideDistance * 2)};
            nodes.add(new Node(startSize + i, color, 30, position, new LinkedList<Node>()));
        }
        LinkedList<Node> edges = new LinkedList<Node>();
        for (int j = startSize; j < amount + startSize; j++){
            edges.clear();
            int edgesAmount = (int) (random.nextInt(amount) * nodeToEdgeRatio);
            for (int k = 0; k <  edgesAmount; k++){
                edges.add(nodes.get(random.nextInt(nodes.size())));
            }
            nodes.get(j).setEdges(edges);
        }
        graphic.setNodes(nodes);
    }
    

    public LinkedList<Node> DFS(Node from, Node to){
        return go(from, to, new LinkedList<Node>(), new boolean[nodes.size()]);
    }

    public LinkedList<Node> go(Node from, Node to, LinkedList<Node> path, boolean[] visited){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            throw new UnsupportedOperationException("Interrupts not supported.", ie);
        }
        visited[from.index] = true;
        from.setColor(1);
        update();

        if (from == to){
            System.out.println("found path");
            path.add(from);
            System.out.println(path);
            return path;
        }
        for (int i = 0; i < from.edges.size(); i++){
            Node edge = from.edges.get(i);
            System.out.println(from.index);
            if (visited[edge.index]) 
                continue;

            LinkedList<Node> newPath = new LinkedList<Node>(path);
            newPath.add(edge);
            System.out.println(from.index);
            LinkedList<Node> pathAddon = go(edge, to, newPath, visited);

            if (pathAddon.size() > 0){
                path.addAll(pathAddon);
                return path;
            }
        }
        return new LinkedList<Node>();
    }
}