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
        
        //g2d.setStroke(new BasicStroke(1));  this makes the line thicker     

        g2d.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 

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
        Color color = (node.color > 6) ? Color.white : colors[node.color];
        g2d.setColor(color);
        g2d.fillOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);
        g2d.setColor(original);

        g2d.drawOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);

        
        int widthString = String.valueOf(node.index).length();
        g2d.drawString(String.valueOf(node.index), node.position[0] - widthString * 3, node.position[1] + 3);
    }

    private void drawEdge(Graphics2D g2d, Node from, Node to){
        double deltaX = to.position[0] - from.position[0];
        double deltaY = to.position[1] - from.position[1];

        double distance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0)) - to.radius;

        double angelLine = giveAngelLine(from, to);

        int[] pointToDrawTo = givePoint(distance, angelLine, from);

        g2d.drawLine(from.position[0], from.position[1], pointToDrawTo[0], pointToDrawTo[1]);

        for (Node edge : to.giveEdges()){
            if (from == edge){
                return;
            }
        }
        int[][] pointsTriangle = giveTriangleForArrow(pointToDrawTo, 15.0, angelLine, Math.PI * 0.1, from);

        Polygon triangle = new Polygon();
        for (int[] point : pointsTriangle)
            triangle.addPoint(point[0], point[1]);
        g2d.drawPolygon(triangle);
        g2d.fillPolygon(triangle);
    }
    
    private double giveAngelLine(Node from, Node to)
    {
        double deltaX = to.position[0] - from.position[0];
        double deltaY = to.position[1] - from.position[1];

        double angel;
        if (deltaX == 0) // can't divide by zero. Line has to be horizontal
            if (deltaY < 0) // see's in wich direction the Line has to go
                angel = Math.PI * 1.5;
            else
                angel = Math.PI * 0.5; 
        else
            angel = Math.atan(deltaY / deltaX);

        if (deltaX < 0) // to have all possiblitys. Here (Math.atan(deltaX / deltaY)) the "-" wouldn't do anything
            angel += Math.PI;

        return angel;
    }

    private int[] givePoint(double distance, double angel, Node from){
        double newDeltaX = Math.cos(angel) * distance; 
        double newDeltaY = Math.sin(angel) * distance;

        int[] point = new int[2];

        point[0] = (int) (from.position[0] + newDeltaX);
        point[1] = (int) (from.position[1] + newDeltaY);

        return point;
    }

    private int[][] giveTriangleForArrow(int[] endLine, double lengthSide, double angelLine, double angelArrow, Node from)
    {
        double angel1 = Math.PI * 1.5 - angelLine + angelArrow;
        double angel2 = Math.PI * 1.5 - angelLine - angelArrow;

        double x1 = Math.sin(angel1) * lengthSide;
        double y1 = Math.cos(angel1) * lengthSide;

        int[] point1 = new int[2];

        point1[0] = (int) (endLine[0] + x1);
        point1[1] = (int) (endLine[1] + y1);

        int[] point2 = new int[2];

        double x2 = Math.sin(angel2) * lengthSide;
        double y2 = Math.cos(angel2) * lengthSide;

        point2[0] = (int) (endLine[0] + x2);
        point2[1] = (int) (endLine[1] + y2);

        int[][] triangle = {point1, point2, endLine};

        return triangle;
    }
}