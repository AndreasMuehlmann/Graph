import javax.swing.*;
import java.util.*;

public class Graphic extends JFrame{

    Panel Panel;

    public Graphic(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        Panel = new Panel(nodes , windowWidth, windowHeight);


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGraph(); 
            }
        });

    }
    
    private void createAndShowGraph() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame frame = new JFrame("graphgraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void update(){
        Panel.update();
    }

    public void setNodes(LinkedList<Node> nodes){
        Panel.setNodes(nodes);
    }
}
       
       
       