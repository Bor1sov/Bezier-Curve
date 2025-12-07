package app.view.renderers;

import app.model.BezierCurve;
import app.model.ControlPoint;
import app.model.CurveProperties;
import java.awt.*;


public class PointRenderer extends Renderer {

    public void drawControlPoints(Graphics2D g2d, BezierCurve curve, CurveProperties properties) {
        setGraphicsQuality(g2d);
        
        for (ControlPoint point : curve.getControlPoints()) {
            drawControlPoint(g2d, point, properties);
        }
    }
    
    private void drawControlPoint(Graphics2D g2d, ControlPoint point, CurveProperties properties) {
        int radius = point.getRadius();
        int diameter = radius * 2;
        int x = point.getX() - radius;
        int y = point.getY() - radius;
        
        if (point.isSelected()) {
            g2d.setColor(properties.getSelectedPointColor());
        } else {
            g2d.setColor(properties.getControlPointColor());
        }
        g2d.fillOval(x, y, diameter, diameter);
        

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(x, y, diameter, diameter);
        

        drawPointLabel(g2d, point);
    }

    private void drawPointLabel(Graphics2D g2d, ControlPoint point) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        
        String label = point.getName();
        int labelX = point.getX() + point.getRadius() + 2;
        int labelY = point.getY() - point.getRadius();
        
        g2d.drawString(label, labelX, labelY);
    }
}