import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
public class Graph{

    Graphic graphic;
    LinkedList<Node> nodes = new LinkedList<Node>();
    int windowWidth;
    int windowHeight;
    int sideDistance;

    public Graph(){
        this.nodes = new LinkedList<Node>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.windowWidth = (int) (screenSize.width / 1.1);
        this.windowHeight = (int) (screenSize.height / 1.1);

        this.sideDistance = 50;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public Graph(LinkedList<Node> nodes){
        this.nodes = nodes;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.windowWidth = screenSize.width;
        this.windowHeight = screenSize.height;

        this.sideDistance = 50;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.sideDistance = 50;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public void update(){ // repaints the graph and updates the nodes of graphic
        graphic.setNodes(nodes);
        graphic.update();
    }

    public void setEdgeColors(Color color){
        for (Node node : nodes)
            node.setEdgeColors(color);
    }

    public void setNodeColors(Color color){
        for (Node node : nodes)
            node.setNodeColor(color);

    }

    public Color giveRandomColor(){
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
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
    
    public void arrangeDirectedAcyclicGraph(){ // arranges nodes in a readable way
        //Have to figure out 
    }

    public void makeGraph(int amount, Color color, int radius, double nodeToEdgeRatio){ // color null for colorful nodes
        Random random = new Random();
        int startSize = nodes.size();

        nodes.addAll(makeRandomNodes(amount, color, radius));
        HashMap<String, Node> edges = new HashMap<String, Node>();
        for (int j = startSize; j < amount + startSize; j++){
            edges.clear();

            int edgesAmount = (int) (random.nextInt(amount) * nodeToEdgeRatio);
            for (int k = 0; k <  edgesAmount; k++){
                Node edge = nodes.get(random.nextInt(nodes.size()));
                edges.put(edge.name, edge);
            }
            nodes.get(j).setEdges(edges);
        }
        update();
    }

    private LinkedList<Node> makeRandomNodes(int amount, Color color, int radius){ 
        LinkedList<Node> newNodes = new LinkedList<Node>();
        Random random = new Random();   
        int startSize = newNodes.size();
        for (int i = 0; i < amount; i++){
            int[] position = {sideDistance + random.nextInt(windowWidth - sideDistance * 2), 
                sideDistance + random.nextInt(windowHeight - sideDistance * 2)};
            newNodes.add(new Node(Integer.toString(startSize + i), (color == null) ? giveRandomColor() : color, radius, position, new HashMap<String, Node>()));
        }
        return newNodes;
    }

    public void makeDirectedAcyclicGraph(int amount, Color color, int radius, double nodeToEdgeRatio){ 
        Random random = new Random();   
        int startSize = nodes.size();

        nodes.addAll(makeRandomNodes(amount, color, radius));

        HashMap<String, Node> edges = new HashMap<String, Node>();
        for (int j = startSize; j < amount + startSize; j++){
            edges.clear();
            
            int edgesAmount = (int) (random.nextInt(amount) * nodeToEdgeRatio);
            for (int k = 0; k <  edgesAmount; k++){

                int range = nodes.size() - j - 1;
                if (range <= 0)
                    continue;

                Node edge = nodes.get(random.nextInt(range) + j + 1);
                if (!isInEdges(edges, edge))
                    edges.put(edge.name, edge);
            }
            nodes.get(j).setEdges(edges);
        }
        update();
    }

    private boolean isInEdges(HashMap<String, Node> edges, Node searchedEdge)
    {
        for (Node edge : edges.values())
            if (edge == searchedEdge)
                return true;
        return false;
    }

    public void standardNodes(Color color, int radius){

        setNodes(new LinkedList<Node> ());

        int[] position0 = {200, 500};
        int[] position1 = {200, 300};
        int[] position2 = {400, 600};
        int[] position3 = {400, 200};
        int[] position4 = {600, 500};
        int[] position5 = {600, 300};

        addNode(new Node("Hello", color, radius, position0, new HashMap<String, Node> ()));
        addNode(new Node("Moin", color, radius, position1 , new  HashMap<String, Node>()));
        addNode(new Node("Bye", color, radius, position2 , new HashMap<String, Node> ()));
        addNode(new Node("servus", color, radius, position3 , new HashMap<String, Node> ()));
        addNode(new Node("4", color, radius, position4 , new HashMap<String, Node> ()));
        addNode(new Node("5", color, radius, position5 , new HashMap<String, Node> ()));


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
        HashMap<String, Node> edges = new HashMap<String, Node> ();

        for (int edgeIndex : edgesIndex){
            Node edge = nodes.get(edgeIndex);
            edges.put(edge.name, edge);
            if (undirected)
                edge.addEdge(node);
        }
        node.setEdges(edges);
    }

    public LinkedList<Node> DFS(Node from, Node to, Color color, double delay) //Depthfirstsearch animated and gives path
    {
        LinkedList<Node> path = helperDFS(from, to, color, new LinkedList<Node>(), booleanHashMapForNodes(), delay);
        print_path(path);
        return path;
    }

    public LinkedList<Node> DFS(Node from, Node to, Color color, HashMap<String, Boolean> visited, double delay) //You can set nodes you don't want to visit
    {
        LinkedList<Node> path = helperDFS(from, to, color, new LinkedList<Node>(), visited, delay);
        print_path(path);
        return path;
    }

    public HashMap<String, Boolean> booleanHashMapForNodes(){
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        for (Node node : nodes)
            visited.put(node.name, false);
        return visited;
    }

    public LinkedList<Node> helperDFS(Node from, Node to, Color color,  LinkedList<Node> path, HashMap<String, Boolean> visited, double delay) // helperfunction for DFS
    {
        visited.put(from.name, true);
        delay(delay);
        from.setNodeColor(color);
        update();

        LinkedList<Node> newPath = new LinkedList<Node>(path);
        newPath.add(from);

        if (from == to){

            delay(delay);
            to.setNodeColor(Color.green);
            return newPath;
        }
        
        for (Node edge : from.edges.values()){

            delay(delay);
            from.setEdgeColor(edge.name, color);
            update();

            if (visited.get(edge.name)){
                delay(delay);
                from.setEdgeColor(edge.name, Color.red);
                update();
                continue;
            }

            newPath = helperDFS(edge, to, color, newPath, visited, delay);

            if (newPath.size() != 0 && newPath.get(newPath.size() - 1) == to){

                delay(delay);
                from.setEdgeColor(edge.name, Color.green);
                update();

                delay(delay);
                from.setNodeColor(Color.green);
                update();
                return newPath;
            }

            delay(delay);
            from.setEdgeColor(edge.name, Color.orange);
            update();
        }

        delay(delay);
        from.setNodeColor(Color.orange);
        update();

        return new LinkedList<Node>();
    }

    public void delay(double seconds)
    {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException ie) {
            throw new UnsupportedOperationException("Interrupts not supported.", ie);
        }
    }

    private void print_path(LinkedList<Node> path) 
    {
        System.out.println("path: ");
        for (int i = 0; i < path.size(); i++){
            System.out.println(path.get(i).name);
        }
        System.out.println("end\n");
    }


    public void randomColoring(double delay){ //colors random nodes randomly
        Random random = new Random();
        for (int i = 0; i < (1 / delay); i++){

            delay(delay);

            Node node = nodes.get(random.nextInt(nodes.size()));
            node.setNodeColor(giveRandomColor());

           for (Node edge : node.edges.values()){
                node.setEdgeColor(edge.name, giveRandomColor());
                update();
         
           }
        }
    }
}