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

        this.sideDistance = 30;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public Graph(LinkedList<Node> nodes){
        this.nodes = nodes;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.windowWidth = screenSize.width;
        this.windowHeight = screenSize.height;

        this.sideDistance = 30;
        graphic = new Graphic(nodes, windowWidth, windowHeight);
    }

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.sideDistance = 30;
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

    public void makeTree(int amount, int maxEdgeAmount, Color color, int radius){ 
        Random random = new Random();   

        assert(maxEdgeAmount > 0);
        
        LinkedList<Node> newNodes = makeRandomNodes(amount, color, radius);

        nodes.addAll(newNodes);

        LinkedList<Node> tree = new LinkedList<Node>();

        Node root = newNodes.get(0);
        newNodes.remove(0);
        tree.add(root);

        while (true){
            if (newNodes.size() == 0)
                break;

            Node appendTo = tree.get(random.nextInt(tree.size()));
            if (appendTo.edges.size() >= maxEdgeAmount)
                continue;

            int index = random.nextInt(newNodes.size());
            Node appended = newNodes.get(index);
            newNodes.remove(index);

            appendTo.addEdge(appended);
            tree.add(appended);
        }
        update();
        return;
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
    
    public void arrangeTree()
    {
        int radius = 20;

        double breadthFaktorDist = 3;
        int breadthDist = (int) (2 * radius + breadthFaktorDist * radius);

        double depthFaktorDist = 3;
        int depthDist = (int) (2 * radius + depthFaktorDist * radius);

        boolean fromTop = true;

        LinkedList<Node> topOrdering = topologicalSort();
        Node root = topOrdering.get(0);
        root.setPosition(windowWidth / 2, depthDist);

        int[][] Dists = giveMaxDists(root, radius, breadthDist, depthDist, 0);
        int breadthMaxDist = Dists[0][0];
        int depthMaxDist = Dists[0][1];

        //remove this
        positionNodes(root, radius, breadthDist, depthDist, fromTop);

        boolean newFromTop = breadthMaxDist >= depthMaxDist;
        
        double breadthRadius = scaling((newFromTop) ? windowWidth : windowHeight, breadthMaxDist, radius, breadthFaktorDist);
        double depthRadius = scaling((newFromTop) ? windowHeight: windowWidth, depthMaxDist, radius, depthFaktorDist);

        int newRadius = (breadthRadius < depthRadius) ? (int) (breadthRadius) : (int) (depthRadius);

        if (newRadius >= radius && newFromTop == fromTop){
            update();
            return;
        }
        
        radius = newRadius;
        fromTop = newFromTop;
        breadthDist = (int) (breadthFaktorDist * breadthRadius);
        depthDist = (int) (depthFaktorDist * depthRadius);
        
        if (!fromTop)
            root.setPosition(depthDist, windowHeight / 2);
        positionNodes(root, radius, breadthDist, depthDist, fromTop);

        update();
    }

    public LinkedList<Node> topologicalSort()
    {
        HashMap<String, Boolean> visited = booleanHashMapForNodes();
        LinkedList<Node> topOrdering = new LinkedList<Node> ();

        for (Node node : nodes){
            if (visited.get(node.name) == false)
                topDFS(node, topOrdering, visited);
        }
        return topOrdering;
    }

    public HashMap<String, Boolean> booleanHashMapForNodes(){
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        for (Node node : nodes)
            visited.put(node.name, false);
        return visited;
    }

    public void topDFS(Node from,  LinkedList<Node> topOrdering, HashMap<String, Boolean> visited) // helperfunction for DFS
    {
        visited.put(from.name, true);

        for (Node edge : from.edges.values()){
            if (visited.get(edge.name)){
                continue;
            }

            topDFS(edge, topOrdering, visited);
        }

        topOrdering.addFirst(from);
        return;
    }

    private int[][] giveMaxDists(Node root, int radius, int breadthDist, int depthDist, int totalDepth)
    {
        totalDepth += depthDist;
        int maxTotalDepth = totalDepth;

        int root_total_breadth = 0;
        int[] totalBreadths = new int[root.edges.size()];

        int i = 0;
        for (Node edge : root.edges.values()){
            int[][] Dists = giveMaxDists(edge, radius, breadthDist, depthDist, totalDepth);
            maxTotalDepth = (Dists[0][1] < maxTotalDepth) ? maxTotalDepth : Dists[0][1];

            totalBreadths[i] = Dists[0][0];
            root_total_breadth += Dists[0][0];
            i++;
        }

        if (root.edges.size() == 0)
            root_total_breadth += breadthDist;

        int[] bdMaxDists = {root_total_breadth, maxTotalDepth}; 
        int[][] Dists = {bdMaxDists, totalBreadths};
        return  Dists;
    }

    private double scaling(int windowSize, int maxDist, int oldRadius, double faktorDist)
    {
        double newRadius = (windowSize * oldRadius) / maxDist;
        return newRadius;
    }

    private void positionNodes(Node root, int radius, int breadthDist, int depthDist, boolean fromTop)
    {
        int breadth;
        int depth;
        root.setRadius(radius);
        if (fromTop){
            breadth = root.position[0];
            depth = root.position[1];
        }
        else{
            breadth = root.position[1];
            depth = root.position[0];
        }
        int[][] dists = giveMaxDists(root, radius, breadthDist, depthDist, 0);

        breadth -= dists[0][0] / 2;

        int i = 0;
        for (Node edge : root.edges.values()){
            breadth += dists[1][i] / 2;
            if (fromTop)
                edge.setPosition(breadth , depth + depthDist);
            else
                edge.setPosition(depth + depthDist , breadth);
            breadth += dists[1][i] / 2;

            positionNodes(edge, radius, breadthDist, depthDist, fromTop);
            i++;
        }
    }
}