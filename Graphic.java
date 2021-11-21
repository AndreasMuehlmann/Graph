import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graphic extends JFrame{

    MyPanel myPanel;

    public Graphic(LinkedList<Node> nodes, int windowWidth, int windowHeight){
        myPanel = new MyPanel(nodes , windowWidth, windowHeight);

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
        f.add(myPanel);
        f.pack();
        f.setVisible(true);
    }

    public void setNodes(LinkedList<Node> nodes){
        myPanel.setNodes(nodes);
    }

    public Color[] giveColors(){
        return myPanel.colors;
    }

    public void update(){
        repaint();
        myPanel.update();
    }
}
       
       
       