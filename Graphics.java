import java.awt.*;
import java.util.*;

public class Graphics extends Frame{
    LinkedList<Node> Nodes;
    int WindowWidth;
    int WindowHeight;

    Graphics(LinkedList<Node> Nodes, int WindowWidth, int WindowHeight){
        this.Nodes = Nodes;
        this.WindowWidth = WindowWidth;
        this.WindowHeight = WindowHeight;

        setSize(WindowWidth, WindowHeight);

        setTitle("Graph");

        setLayout(null);
        
        setVisible(true);
    }
}





