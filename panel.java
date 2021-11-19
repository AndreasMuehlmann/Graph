import javax.swing.*;
import java.awt.*;
import java.util.*;

class MyPanel extends JPanel {

    LinkedList<Node> nodes;
    Color[] colors= {Color.white,Color.blue,Color.green,Color.orange,Color.yellow,Color.black,Color.pink,Color.red};
    int windowWidth;
    int windowHeight;

    public MyPanel(LinkedList<Node> nodes, int windowWidth, int windowHeight) {
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        Graphics2D g2d = (Graphics2D) g;

        Color original = g2d.getColor();
        g2d.drawOval(10,10,100,100);
        g2d.setColor(Color.black);
        g2d.fillOval(10,10,100,100);
        g2d.setColor(original);

       //Graphics2D g2d = (Graphics2D) g;
       //for (int i = 0; i < nodes.size(); i++){
       //    Node node = nodes.get(i);
       //    drawNode(g2d, node);
       //    for (int j = 0; j < node.edges.size(); j++){
       //        drawEdge(g2d, node, node.edges.get(j));
       //    }
       //}
    }  

    private void drawNode(Graphics2D g2d, Node node){
        Color original = g2d.getColor();
        g2d.drawOval(node.position[0], node.position[1], node.radius, node.radius);
        g2d.setColor(colors[node.color]);
        g2d.fillOval(node.position[0], node.position[1], node.radius, node.radius);
        g2d.setColor(original);

        g2d.drawString(String.valueOf(node.index), node.position[0], node.position[1]);
    }

    private void drawEdge(Graphics2D g2d, Node from, Node to){
        g2d.drawLine(from.position[0], from.position[1], to.position[0], to.position[1]);
    }

    public void setNodes(LinkedList<Node> nodes){
        this.nodes = nodes;
    }
}