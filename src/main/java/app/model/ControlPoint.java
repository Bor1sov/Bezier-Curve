package app.model;

import java.awt.Point;

public class ControlPoint {
    private Point position;
    private boolean isSelected;
    private final int radius;
    private String name;
    
    public ControlPoint(int x, int y) {
        this(new Point(x, y), "P" + System.currentTimeMillis());
    }
    
    public ControlPoint(Point position, String name) {
        this.position = position;
        this.name = name;
        this.isSelected = false;
        this.radius = 6;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position) {
        this.position = position;
    }
    
    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }
    
    public int getX() {
        return position.x;
    }
    
    public int getY() {
        return position.y;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean contains(Point p) {
        return position.distance(p) <= radius;
    }
    
    public double distanceTo(Point p) {
        return position.distance(p);
    }
    
    @Override
    public String toString() {
        return String.format("%s(%d, %d)", name, position.x, position.y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ControlPoint that = (ControlPoint) obj;
        return position.equals(that.position) && name.equals(that.name);
    }
    
    @Override
    public int hashCode() {
        return 31 * position.hashCode() + name.hashCode();
    }
}