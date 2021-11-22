import javax.swing.*;
import java.awt.*;
import java.util.*;

class Panel extends JPanel {

    LinkedList<Node> nodes;
    Color[] colors= {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink};
    int windowWidth;
    int windowHeight;

    public Panel(LinkedList<Node> nodes, int windowWidth, int windowHeight) {
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
        for (Node node : nodes) {
            for (Node edge : node.edges){
                drawEdge(g2d, node, edge);
            }
        }

        for (Node node : nodes){
            drawNode(g2d, node);
        }
    }  

    public void setNodes(LinkedList<Node> nodes){
        this.nodes = nodes;
    }

    public void update(){
        repaint();
    }

    private void drawNode(Graphics2D g2d, Node node){
        Color original = g2d.getColor();
        g2d.drawOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);
        Color color = (node.color > 6) ? Color.white : colors[node.color];
        g2d.setColor(color);
        g2d.fillOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);
        g2d.setColor(original);

        
        int widthString = String.valueOf(node.index).length();
        g2d.drawString(String.valueOf(node.index), node.position[0] - widthString * 3, node.position[1] + 3);
    }

    private void drawEdge(Graphics2D g2d, Node from, Node to){
        double deltaX = to.position[0] - from.position[0];
        double deltaY = to.position[1] - from.position[1];

        double distance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0)) - to.radius;

        int[] pointToDrawTo = pointBetweenNodes(distance, from, to);

        g2d.drawLine(from.position[0], from.position[1], pointToDrawTo[0], pointToDrawTo[1]);
    }

    public int[] pointBetweenNodes(double distance, Node from, Node to){
        double deltaX = to.position[0] - from.position[0];
        double deltaY = to.position[1] - from.position[1];

        double alpha;
        if (deltaY == 0) // can't divide by zero. Line has to be horizontal
            if (deltaX <0) // see's in wich direction the Line has to go
                alpha = Math.PI * 1.5;
            else
                alpha = Math.PI *0.5;
        else
            alpha = Math.atan(deltaX / deltaY);

        if (deltaY < 0) // to have all possiblitys. Here (Math.atan(deltaX / deltaY)) the "-" wouldn't do anything
            alpha += Math.PI;

        double newDeltaX = Math.sin(alpha) * distance; 
        double newDeltaY = Math.cos(alpha) * distance;

        int[] point = new int[2];


        point[0] = (int) (from.position[0] + newDeltaX);
        point[1] = (int) (from.position[1] + newDeltaY);

        return point;
    }
}