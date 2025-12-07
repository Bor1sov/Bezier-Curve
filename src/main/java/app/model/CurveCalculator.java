package app.model;

import app.utils.MathUtils;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CurveCalculator {

    public static List<Point> calculateBezierCurve(
            List<ControlPoint> controlPoints, 
            boolean closed, 
            int segments) {
        
        List<Point> curvePoints = new ArrayList<>();
        
        if (controlPoints.size() < 2) {
            return curvePoints;
        }
        
        int n = controlPoints.size() - 1;

        List<ControlPoint> points = new ArrayList<>(controlPoints);
        if (closed && controlPoints.size() > 2) {
            points.add(controlPoints.get(0));
            n = points.size() - 1;
        }

        for (int i = 0; i <= segments; i++) {
            double t = (double) i / segments;
            curvePoints.add(calculateBezierPoint(points, n, t));
        }
        
        return curvePoints;
    }

    private static Point calculateBezierPoint(
            List<ControlPoint> points, 
            int n, 
            double t) {
        
        double x = 0;
        double y = 0;
        
        for (int i = 0; i <= n; i++) {
            double coefficient = MathUtils.bernsteinCoefficient(n, i, t);
            x += coefficient * points.get(i).getX();
            y += coefficient * points.get(i).getY();
        }
        
        return new Point((int) Math.round(x), (int) Math.round(y));
    }
    

    public static List<Point> calculateDeCasteljau(
            List<ControlPoint> controlPoints,
            int segments) {
        
        List<Point> curvePoints = new ArrayList<>();
        
        for (int i = 0; i <= segments; i++) {
            double t = (double) i / segments;
            curvePoints.add(recursiveDeCasteljau(controlPoints, t));
        }
        
        return curvePoints;
    }

    private static Point recursiveDeCasteljau(
            List<ControlPoint> points, 
            double t) {
        
        if (points.size() == 1) {
            return points.get(0).getPosition();
        }
        
        List<ControlPoint> newPoints = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            ControlPoint p1 = points.get(i);
            ControlPoint p2 = points.get(i + 1);
            
            int x = (int) ((1 - t) * p1.getX() + t * p2.getX());
            int y = (int) ((1 - t) * p1.getY() + t * p2.getY());
            
            newPoints.add(new ControlPoint(x, y));
        }
        
        return recursiveDeCasteljau(newPoints, t);
    }
}