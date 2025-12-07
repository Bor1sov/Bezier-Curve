package app.view.renderers;

import app.model.BezierCurve;
import app.model.ControlPoint;
import app.model.CurveProperties;
import java.awt.*;
import java.util.List;

public class CurveRenderer extends Renderer {
    public void drawCurve(Graphics2D g2d, BezierCurve curve, CurveProperties properties) {
        setGraphicsQuality(g2d);
        
        List<Point> points = curve.getCurvePoints();
        if (points.size() < 2) return;
        
        g2d.setColor(properties.getCurveColor());
        g2d.setStroke(new BasicStroke(
            properties.getCurveWidth(),
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
    

    public void drawControlLines(Graphics2D g2d, BezierCurve curve, CurveProperties properties) {
        setGraphicsQuality(g2d);
        
        List<ControlPoint> controlPoints = curve.getControlPoints();
        if (controlPoints.size() < 2) return;
        
        g2d.setColor(properties.getControlLineColor());
        g2d.setStroke(new BasicStroke(
            properties.getControlLineWidth(),
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        for (int i = 0; i < controlPoints.size() - 1; i++) {
            ControlPoint p1 = controlPoints.get(i);
            ControlPoint p2 = controlPoints.get(i + 1);
            g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
        if (curve.isClosed() && controlPoints.size() > 2) {
            ControlPoint first = controlPoints.get(0);
            ControlPoint last = controlPoints.get(controlPoints.size() - 1);
            g2d.drawLine(last.getX(), last.getY(), first.getX(), first.getY());
        }
    }
}