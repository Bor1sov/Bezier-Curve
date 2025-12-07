package app.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BezierCurve {
    private List<ControlPoint> controlPoints;
    private List<Point> curvePoints;
    private boolean isClosed;
    private String name;
    
    public BezierCurve(String name) {
        this.controlPoints = new ArrayList<>();
        this.curvePoints = new ArrayList<>();
        this.isClosed = false;
        this.name = name;
    }
    
    public void addControlPoint(ControlPoint point) {
        controlPoints.add(point);
        calculateCurve();
    }
    
    public void addControlPoint(int x, int y) {
        addControlPoint(new ControlPoint(x, y));
    }
    
    public void removeControlPoint(ControlPoint point) {
        controlPoints.remove(point);
        calculateCurve();
    }
    
    public void updateControlPoint(ControlPoint point, Point newPosition) {
        point.setPosition(newPosition);
        calculateCurve();
    }
    
    public List<ControlPoint> getControlPoints() {
        return Collections.unmodifiableList(controlPoints);
    }
    
    public List<Point> getCurvePoints() {
        return Collections.unmodifiableList(curvePoints);
    }
    
    public void setCurvePoints(List<Point> points) {
        this.curvePoints = new ArrayList<>(points);
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void setClosed(boolean closed) {
        isClosed = closed;
        calculateCurve();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isEmpty() {
        return controlPoints.size() < 2;
    }
    
    public int getControlPointCount() {
        return controlPoints.size();
    }
    
    public void calculateCurve() {
        if (controlPoints.size() < 2) {
            curvePoints.clear();
            return;
        }
        
        curvePoints = CurveCalculator.calculateBezierCurve(
            controlPoints, 
            isClosed, 
            1000
        );
    }
    
    public ControlPoint findControlPointAt(Point location) {
        for (ControlPoint point : controlPoints) {
            if (point.contains(location)) {
                return point;
            }
        }
        return null;
    }
}