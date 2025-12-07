package app.utils;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GeometryUtils {
    
    /**
     * Проверяет, находится ли точка рядом с отрезком
     */
    public static boolean isPointNearLine(Point point, Point lineStart, Point lineEnd, double tolerance) {
        double distance = Line2D.ptSegDist(
            lineStart.x, lineStart.y,
            lineEnd.x, lineEnd.y,
            point.x, point.y
        );
        return distance <= tolerance;
    }
    
    /**
     * Вычисляет ближайшую точку на отрезке к заданной точке
     */
    public static Point getClosestPointOnSegment(Point point, Point lineStart, Point lineEnd) {
        double lineLength = lineStart.distance(lineEnd);
        if (lineLength == 0) return lineStart;
        
        double t = ((point.x - lineStart.x) * (lineEnd.x - lineStart.x) +
                   (point.y - lineStart.y) * (lineEnd.y - lineStart.y)) /
                   (lineLength * lineLength);
        
        t = MathUtils.clamp(t, 0, 1);
        
        int closestX = (int) (lineStart.x + t * (lineEnd.x - lineStart.x));
        int closestY = (int) (lineStart.y + t * (lineEnd.y - lineStart.y));
        
        return new Point(closestX, closestY);
    }
    
    /**
     * Вычисляет точку пересечения двух отрезков
     */
    public static Point getLineIntersection(Point p1, Point p2, Point p3, Point p4) {
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        double x3 = p3.x, y3 = p3.y;
        double x4 = p4.x, y4 = p4.y;
        
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        
        if (Math.abs(denominator) < 1e-10) {
            return null; 
        }
        
        double intersectX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        if (isPointOnSegment(new Point((int)intersectX, (int)intersectY), p1, p2) &&
            isPointOnSegment(new Point((int)intersectX, (int)intersectY), p3, p4)) {
            return new Point((int)intersectX, (int)intersectY);
        }
        
        return null;
    }
    
    public static boolean isPointOnSegment(Point point, Point lineStart, Point lineEnd) {
        double epsilon = 1e-6;
        
        double crossProduct = (point.y - lineStart.y) * (lineEnd.x - lineStart.x) -
                            (point.x - lineStart.x) * (lineEnd.y - lineStart.y);
        
        if (Math.abs(crossProduct) > epsilon) {
            return false;
        }
        
        double dotProduct = (point.x - lineStart.x) * (lineEnd.x - lineStart.x) +
                          (point.y - lineStart.y) * (lineEnd.y - lineStart.y);
        
        if (dotProduct < 0) {
            return false;
        }
        
        double squaredLength = lineStart.distanceSq(lineEnd);
        if (dotProduct > squaredLength) {
            return false;
        }
        
        return true;
    }
}