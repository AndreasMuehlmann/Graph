import javax.swing.*;
import java.awt.*;
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
        JFrame f = new JFrame("graphgraph");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(Panel);
        f.pack();
        f.setVisible(true);
    }

    public void update(){
        Panel.update();
    }

    public void setNodes(LinkedList<Node> nodes){
        Panel.setNodes(nodes);
    }

    public Color[] giveColors(){
        return Panel.colors;
    }
}
       
       
       