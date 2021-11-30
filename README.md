# Graph
This is an API to display Graphs.
You can also animate some Graphalgorithms.

Class Diagramm:

Graph:
    Graphic graphic;
    LinkedList<Node> nodes
    double nodeToEdgeRatio;
    int windowWidth;
    int windowHeight;
    int sideDistance;

    public Graph(LinkedList<Node> nodes, int windowWidth, int windowHeight, double nodeToEdgeRatio)

    public void update() // repaints the graph and updates the nodes of graphic

    public void setEdgeColors(Color color)

    public Color giveRandomColor()

    public LinkedList<Node>  giveNodes()

    public void  setNodes(LinkedList<Node> nodes)

    public void addNode(Node Node)
    
    public void Arrange(){ 

    public void makeGraph(int amount, Color color, int radius)

    private LinkedList<Node> makeRandomNodes(int amount, Color color, int radius)

    public void makeTree(int amount, int color, int radius)

    public void standardNodes(Color color, int radius)

    public void setEdges(Node node, int[] edgesIndex, boolean undirected)

    public LinkedList<Node> DFS(Node from, Node to, Color color) //Depthfirstsearch animated and gives path

    public LinkedList<Node> DFS(Node from, Node to, Color color, HashMap<String, Boolean> visited //You can set nodes you don't want to visit

    public HashMap<String, Boolean> booleanHashMapForNodes()

    public LinkedList<Node> go(Node from, Node to, Color color,  LinkedList<Node> path, HashMap<String, Boolean> visited) // helperfunction for DFS

    private void print_path(LinkedList<Node> path) 

    public void randomColoring(){ //colors random nodes randomly