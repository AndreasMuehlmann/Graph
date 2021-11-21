import javax.swing.*;
import java.awt.*;
import java.util.*;

class MyPanel extends JPanel {

    LinkedList<Node> nodes;
    Color[] colors= {Color.yellow, Color.blue, Color.green, Color.orange, Color.pink, Color.red};
    int windowWidth;
    int windowHeight;

    public MyPanel(LinkedList<Node> nodes, int windowWidth, int windowHeight) {
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        Graphics2D g2d = (Graphics2D) g;
        Node node;
        for (int i = 0; i < nodes.size(); i++){
            node = nodes.get(i);
            for (int j = 0; j < node.edges.size(); j++){
                drawEdge(g2d, node, node.edges.get(j));
            }
        }

        for (int k = 0; k < nodes.size(); k++){
            node = nodes.get(k);
            drawNode(g2d, node);
        }
    }  

    private void drawNode(Graphics2D g2d, Node node){
        Color original = g2d.getColor();
        g2d.drawOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);
        g2d.setColor(colors[node.color]);
        g2d.fillOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);
        g2d.setColor(original);

        
        int widthString = String.valueOf(node.index).length();
        g2d.drawString(String.valueOf(node.index), node.position[0] - widthString * 3, node.position[1] + 3);
    }

    private void drawEdge(Graphics2D g2d, Node from, Node to){

        int x1 = from.position[0];
        int y1 = from.position[1];
        int x2 = to.position[0];
        int y2 = to.position[1];

        g2d.drawLine(x1, y1, x2, y2);
    }

    public void setNodes(LinkedList<Node> nodes){
        this.nodes = nodes;
    }

    public void update(){
        repaint();
    }
}