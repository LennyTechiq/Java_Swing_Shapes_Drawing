import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ShapePanel extends JPanel {
    private String selectedShape;
    private int size;
    private int panelWidth;
    private int panelHeight;

    public ShapePanel(int width, int height) {
        selectedShape = "";
        size = 100; // Default size
        panelWidth = width;
        panelHeight = height;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    public void setSelectedShape(String shape) {
        selectedShape = shape;
        repaint();
    }

    public void setSize(int size) {
        this.size = size;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        switch (selectedShape) {
            case "Circle":
                g2d.setColor(Color.BLUE);
                g2d.fill(new Ellipse2D.Double(panelWidth / 2 - size / 2, panelHeight / 2 - size / 2, size, size));
                break;
            case "Square":
                g2d.setColor(Color.RED);
                g2d.fillRect(panelWidth / 2 - size / 2, panelHeight / 2 - size / 2, size, size);
                break;
            case "Triangle":
                int[] xPoints = {panelWidth / 2, panelWidth / 2 - size / 2, panelWidth / 2 + size / 2};
                int[] yPoints = {panelHeight / 2 - size / 2, panelHeight / 2 + size / 2, panelHeight / 2 + size / 2};
                g2d.setColor(Color.GREEN);
                g2d.fillPolygon(xPoints, yPoints, 3);
                break;
            case "Rectangle":
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(panelWidth / 2 - size, panelHeight / 2 - size / 2, size * 2, size);
                break;
            case "Sphere":
                // Draw a gradient-filled ellipse to represent the sphere
                GradientPaint gradient = new GradientPaint(panelWidth / 2 - size / 2, panelHeight / 2 - size / 2, Color.WHITE,
                        panelWidth / 2 + size / 2, panelHeight / 2 + size / 2, Color.LIGHT_GRAY);
                g2d.setPaint(gradient);
                g2d.fill(new Ellipse2D.Double(panelWidth / 2 - size / 2, panelHeight / 2 - size / 2, size, size));
                g2d.setColor(Color.BLACK);
                g2d.draw(new Ellipse2D.Double(panelWidth / 2 - size / 2, panelHeight / 2 - size / 2, size, size));
                break;
            case "Cube":
                int halfSize = size / 2;
                // Draw front face
                g2d.drawRect(panelWidth / 2 - halfSize, panelHeight / 2 - halfSize, size, size);
                // Draw back face
                g2d.drawRect(panelWidth / 2 - halfSize - halfSize / 2, panelHeight / 2 - halfSize - halfSize / 2, size, size);
                // Draw connecting lines
                g2d.drawLine(panelWidth / 2 - halfSize, panelHeight / 2 - halfSize, panelWidth / 2 - halfSize - halfSize / 2, panelHeight / 2 - halfSize - halfSize / 2);
                g2d.drawLine(panelWidth / 2 + halfSize, panelHeight / 2 - halfSize, panelWidth / 2 + halfSize - halfSize / 2, panelHeight / 2 - halfSize - halfSize / 2);
                g2d.drawLine(panelWidth / 2 - halfSize, panelHeight / 2 + halfSize, panelWidth / 2 - halfSize - halfSize / 2, panelHeight / 2 + halfSize - halfSize / 2);
                g2d.drawLine(panelWidth / 2 + halfSize, panelHeight / 2 + halfSize, panelWidth / 2 + halfSize - halfSize / 2, panelHeight / 2 + halfSize - halfSize / 2);
                break;
            case "Cone":
                // Draw a cone with an oval base
                int coneHeight = size;
                int coneBaseWidth = size;
                int coneBaseHeight = size / 2;
                int centerX = panelWidth / 2;
                int centerY = panelHeight / 2;

                // Calculate triangle points
                int baseLeftX = centerX - coneBaseWidth / 2;
                int baseLeftY = centerY + coneHeight / 2;
                int apexX = centerX;
                int apexY = centerY - coneHeight / 2;
                int baseRightX = centerX + coneBaseWidth / 2;
                int baseRightY = centerY + coneHeight / 2;

                // Draw triangle
                g2d.draw(new Line2D.Double(baseLeftX, baseLeftY, apexX, apexY));
                g2d.draw(new Line2D.Double(apexX, apexY, baseRightX, baseRightY));
                g2d.draw(new Line2D.Double(baseRightX, baseRightY, baseLeftX, baseLeftY));

                // Draw oval base
                g2d.draw(new Ellipse2D.Double(centerX - coneBaseWidth / 2, centerY + coneHeight / 2 - coneBaseHeight / 2, coneBaseWidth, coneBaseHeight));
                break;
            case "Cylinder":
                // Draw a cylinder
                int cylinderHeight = size;
                int cylinderWidth = size / 2;
                int x = panelWidth / 2 - cylinderWidth;
                int y = panelHeight / 2 - cylinderHeight / 2;
                // Draw top ellipse
                g2d.draw(new Ellipse2D.Double(x, y, cylinderWidth * 2, cylinderWidth));
                // Draw bottom ellipse
                g2d.draw(new Ellipse2D.Double(x, y + cylinderHeight - cylinderWidth, cylinderWidth * 2, cylinderWidth));
                // Draw connecting lines
                g2d.drawLine(x, y + cylinderWidth / 2, x, y + cylinderHeight - cylinderWidth / 2);
                g2d.drawLine(x + cylinderWidth * 2, y + cylinderWidth / 2, x + cylinderWidth * 2, y + cylinderHeight - cylinderWidth / 2);
                break;
            case "Torus":
                int torusRadius = size / 2;
                int torusTubeRadius = size / 6;
                int centerX1 = panelWidth / 2;
                int centerY1 = panelHeight / 2;

                for (int theta = 0; theta < 360; theta += 10) {
                    double radiansTheta = Math.toRadians(theta);
                    double cosTheta = Math.cos(radiansTheta);
                    double sinTheta = Math.sin(radiansTheta);
                    for (int phi = 0; phi < 360; phi += 10) {
                        double radiansPhi = Math.toRadians(phi);
                        double cosPhi = Math.cos(radiansPhi);
                        double sinPhi = Math.sin(radiansPhi);
                        double x1 = centerX1 + (torusRadius + torusTubeRadius * cosPhi) * cosTheta;
                        double y1 = centerY1 + (torusRadius + torusTubeRadius * cosPhi) * sinTheta;
                        double z1 = torusTubeRadius * sinPhi;

                        radiansTheta = Math.toRadians(theta + 10);
                        cosTheta = Math.cos(radiansTheta);
                        sinTheta = Math.sin(radiansTheta);
                        double x2 = centerX1 + (torusRadius + torusTubeRadius * cosPhi) * cosTheta;
                        double y2 = centerY1 + (torusRadius + torusTubeRadius * cosPhi) * sinTheta;
                        double z2 = torusTubeRadius * sinPhi;

                        radiansPhi = Math.toRadians(phi + 10);
                        cosPhi = Math.cos(radiansPhi);
                        sinPhi = Math.sin(radiansPhi);
                        double x3 = centerX1 + (torusRadius + torusTubeRadius * cosPhi) * cosTheta;
                        double y3 = centerY1 + (torusRadius + torusTubeRadius * cosPhi) * sinTheta;
                        double z3 = torusTubeRadius * sinPhi;

                        radiansTheta = Math.toRadians(theta);
                        cosTheta = Math.cos(radiansTheta);
                        sinTheta = Math.sin(radiansTheta);
                        double x4 = centerX1 + (torusRadius + torusTubeRadius * cosPhi) * cosTheta;
                        double y4 = centerY1 + (torusRadius + torusTubeRadius * cosPhi) * sinTheta;
                        double z4 = torusTubeRadius * sinPhi;

                        g2d.setColor(Color.CYAN);
                        g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                        g2d.drawLine((int) x2, (int) y2, (int) x3, (int) y3);
                        g2d.drawLine((int) x3, (int) y3, (int) x4, (int) y4);
                        g2d.drawLine((int) x4, (int) y4, (int) x1, (int) y1);
                    }
                }
                break;
            default:
                break;
        }
    }
}
