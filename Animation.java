import java.util.*;
import java.awt.Color;



class Animation
{
    Graph graph;
    LinkedList<Node> nodes;

    public Animation(Graph graph){
        this.graph = graph;
        nodes = graph.giveNodes();
    }

    public void randomColoring(double delay){ //colors random nodes randomly
        Random random = new Random();
        for (int i = 0; i < (5 / delay); i++){

            delay(delay);

            Node node = nodes.get(random.nextInt(nodes.size()));
            node.setNodeColor(graph.giveRandomColor());

           for (Node edge : node.edges.values()){
                node.setEdgeColor(edge.name, graph.giveRandomColor());
                graph.update();
         
           }
        }
    }
    
    public void animate()
    {
        delay(1);

       DFSAnimated(nodes.get(0), nodes.get(8), Color.blue, 0.05);
       delay(2);

       randomColoring(0.01);
       graph.setEdgeColors(Color.black);
       graph.setNodeColors(Color.white);


       DFSAnimated(nodes.get(0), nodes.get(9), Color.blue, 1);

    }

    public LinkedList<Node> DFSAnimated(Node from, Node to, Color color, double delay) //Depthfirstsearch animated and gives path
    {
        LinkedList<Node> path = helperDFSAnimated(from, to, color, new LinkedList<Node>(), graph.booleanHashMapForNodes(), delay);
        printPath(path);
        return path;
    }

    public LinkedList<Node> helperDFSAnimated(Node from, Node to, Color color,  LinkedList<Node> path, HashMap<String, Boolean> visited, double delay) // animated helperfunction for DFS 
    {
        visited.put(from.name, true);
        delay(delay);
        from.setNodeColor(color);
        graph.update();

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
            graph.update();

            if (visited.get(edge.name)){
                delay(delay);
                from.setEdgeColor(edge.name, Color.red);
                graph.update();
                continue;
            }

            newPath = helperDFSAnimated(edge, to, color, newPath, visited, delay);

            if (newPath.size() != 0 && newPath.get(newPath.size() - 1) == to){

                delay(delay);
                from.setEdgeColor(edge.name, Color.green);
                graph.update();

                delay(delay);
                from.setNodeColor(Color.green);
                graph.update();
                return newPath;
            }

            delay(delay);
            from.setEdgeColor(edge.name, Color.orange);
            graph.update();
        }

        delay(delay);
        from.setNodeColor(Color.orange);
        graph.update();

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

    public void printPath(LinkedList<Node> path) 
    {
        System.out.println("path: ");
        for (int i = 0; i < path.size(); i++){
            System.out.println(path.get(i).name);
        }
        System.out.println("end\n");
    }
}