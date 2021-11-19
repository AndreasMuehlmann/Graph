import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
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
}