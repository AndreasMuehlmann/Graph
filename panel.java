import javax.swing.*;
import java.awt.*;
import java.util.*;

class Panel extends JPanel {

    Graphics2D g2d;

    LinkedList<Node> nodes;
    int windowWidth;
    int windowHeight;
    Color backGroundColor;

    public Panel(LinkedList<Node> nodes, int windowWidth, int windowHeight) {
        this.nodes = nodes;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        backGroundColor = new Color(100, 100, 100);
        this.setBackground(backGroundColor);
    }

    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        Graphics2D g2d = (Graphics2D) g;

        if (nodes.size() < 40)        
            g2d.setStroke(new BasicStroke(2));

        g2d.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 

        for (Node node : nodes) {
            for (Node edge : node.edges.values()){
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
        
        g2d.setColor(node.color);
        g2d.fillOval(node.position[0] - node.radius, node.position[1] - node.radius, node.radius * 2, node.radius * 2);

        g2d.setColor(Color.black);
        int widthString = node.name.length();
        g2d.drawString(String.valueOf(node.name), node.position[0] - widthString * 3, node.position[1] + 3);
    }

    private void drawEdge(Graphics2D g2d, Node from, Node to){
        g2d.setColor(from.edgeColors.get(to.name));

        double deltaX = to.position[0] - from.position[0];
        double deltaY = to.position[1] - from.position[1];

        double distance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0)) - to.radius;

        double angelLine = giveAngelLine(from, to);

        int[] pointToDrawTo = givePointCuttingNode(distance, angelLine, from);

        g2d.drawLine(from.position[0], from.position[1], pointToDrawTo[0], pointToDrawTo[1]);

        for (Node edge : to.edges.values()){
            if (from == edge){
                return;
            }
        }
        int[][] pointsTriangle = giveTriangleForArrow(pointToDrawTo, 15.0, angelLine, Math.PI * 0.1);

        Polygon triangle = new Polygon();
        for (int[] point : pointsTriangle)
            triangle.addPoint(point[0], point[1]);
        g2d.fillPolygon(triangle);
        g2d.drawPolygon(triangle);
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

    private int[] givePointCuttingNode(double distance, double angel, Node from){
        double newDeltaX = Math.cos(angel) * distance; 
        double newDeltaY = Math.sin(angel) * distance;

        int[] point = new int[2];

        point[0] = (int) (from.position[0] + newDeltaX);
        point[1] = (int) (from.position[1] + newDeltaY);

        return point;
    }

    private int[][] giveTriangleForArrow(int[] endLine, double lengthSide, double angelLine, double angelArrow)
    {
        double angel1 = Math.PI * 1.5 - angelLine + angelArrow;
        double angel2 = Math.PI * 1.5 - angelLine - angelArrow;

        int[][] triangle = {calculatePointForArrow(endLine, lengthSide, angel1),
             calculatePointForArrow(endLine, lengthSide, angel2), endLine};

        return triangle;
    }

    private int[] calculatePointForArrow(int[] endLine, double lengthSide, double angel)
    {
        double deltaX = Math.sin(angel) * lengthSide;
        double deltaY = Math.cos(angel) * lengthSide;

        int[] point = new int[2];

        point[0] = (int) (endLine[0] + deltaX);
        point[1] = (int) (endLine[1] + deltaY);

        return point;
    }
}