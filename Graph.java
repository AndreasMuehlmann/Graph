import java.util.*;
import java.awt.Color;
public class Graph{

    Graphic graphic;
    LinkedList<Node> nodes = new LinkedList<Node>();
    double nodeToEdgeRatio;
    int windowWidth;
    int windowHeight;
    int sideDistance;

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight, double nodeToEdgeRatio){
        this.nodes = nodes;
        this.nodeToEdgeRatio = nodeToEdgeRatio; // could use some work because not accurate
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.sideDistance = 50;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public void update(){ // repaints the graph and updates the nodes of graphic
        graphic.setNodes(nodes);
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

    public void Arrange(){ // arranges nodes in a readable way
        //Have to figure out 
    }

    public void makeRandomNodes(int amount, int color, int radius){ // color -1 for colorful nodes
        Random random = new Random();   
        int startSize = nodes.size();
        boolean colorful = false;
        if (color == -1)
            colorful = true;

        for (int i = 0; i < amount; i++){
            if (colorful) 
                color = random.nextInt(graphic.giveColors().length);
            int[] position = {sideDistance + random.nextInt(windowWidth - sideDistance * 2), 
                sideDistance + random.nextInt(windowHeight - sideDistance * 2)};

            nodes.add(new Node(startSize + i, color, radius, position, new LinkedList<Node>()));
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
    }

    public void standardNodes(int color, int radius){

        setNodes(new LinkedList<Node> ());

        int[] position0 = {200, 500};
        int[] position1 = {200, 300};
        int[] position2 = {400, 600};
        int[] position3 = {400, 200};
        int[] position4 = {600, 500};
        int[] position5 = {600, 300};

        addNode(new Node(0, color, radius, position0, new LinkedList<Node> ()));
        addNode(new Node(1, color, radius, position1 , new LinkedList<Node> ()));
        addNode(new Node(2, color, radius, position2 , new LinkedList<Node> ()));
        addNode(new Node(3, color, radius, position3 , new LinkedList<Node> ()));
        addNode(new Node(4, color, radius, position4 , new LinkedList<Node> ()));
        addNode(new Node(5, color, radius, position5 , new LinkedList<Node> ()));


        int[] edgesIndex0 = {4};
        setEdges(nodes.get(0), edgesIndex0, true);

        int[] edgesIndex1 = {0, 3};
        setEdges(nodes.get(1), edgesIndex1, false);

        int[] edgesIndex2 = {3, 5};
        setEdges(nodes.get(2), edgesIndex2, true);

        int[] edgesIndex3 = {0, 5};
        setEdges(nodes.get(3), edgesIndex3, false);

        int[] edgesIndex4 = {0,5};
        setEdges(nodes.get(4), edgesIndex4, false);

        int[] edgesIndex5 = {1};
        setEdges(nodes.get(5), edgesIndex5, false);

        update();
    }

    public void setEdges(Node node, int[] edgesIndex, boolean undirected){
        LinkedList<Node> edges = new LinkedList<Node> ();

        for (int edge : edgesIndex){
            edges.add(nodes.get(edge));
            if (undirected)
                nodes.get(edge).addEdge(node);
        }
        node.setEdges(edges);
    }

    public LinkedList<Node> DFS(Node from, Node to, int color) //Depthfirstsearch animated and gives path
    {
        LinkedList<Node> path = go(from, to, color, new LinkedList<Node>(), new boolean[nodes.size()]);
        print_path(path);
        return path;
    }

    public LinkedList<Node> go(Node from, Node to, int color,  LinkedList<Node> path, boolean[] visited) // helperfunction for DFS
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            throw new UnsupportedOperationException("Interrupts not supported.", ie);
        }
        visited[from.index] = true;
        from.setColor(color);
        update();

        LinkedList<Node> newPath = new LinkedList<Node>(path);
        newPath.add(from);

        if (from == to)
            return newPath;
        
        for (int i = 0; i < from.edges.size(); i++){
            Node edge = from.edges.get(i);
            if (visited[edge.index]) 
                continue;

            newPath = go(edge, to, color, newPath, visited);

            if (newPath.size() != 0 && newPath.get(newPath.size() - 1) == to)
                return newPath;
        }
        return new LinkedList<Node>();
    }

    private void print_path(LinkedList<Node> path) 
    {
        System.out.println("path: ");
        for (int i = 0; i < path.size(); i++){
            System.out.println(path.get(i).index);
        }
        System.out.println("end\n");
    }


    public void randomColoring(){ //colors random nodes randomly
        Random random = new Random();
        for (int i = 0; i < 100; i++){

           try {
               Thread.sleep(50);
           } catch (InterruptedException ie) {
               throw new UnsupportedOperationException("Interrupts not supported.", ie);
           }

            nodes.get(random.nextInt(nodes.size())).setColor(random.nextInt(5));
            update();
        }
    }
}