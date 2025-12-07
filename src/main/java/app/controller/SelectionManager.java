package app.controller;

import app.model.ControlPoint;
import java.util.HashSet;
import java.util.Set;


public class SelectionManager {
    private Set<ControlPoint> selectedPoints;
    
    public SelectionManager() {
        selectedPoints = new HashSet<>();
    }
    

    public void selectPoint(ControlPoint point) {
        point.setSelected(true);
        selectedPoints.add(point);
    }
    

    public void deselectPoint(ControlPoint point) {
        point.setSelected(false);
        selectedPoints.remove(point);
    }

    public void clearSelection() {
        for (ControlPoint point : selectedPoints) {
            point.setSelected(false);
        }
        selectedPoints.clear();
    }

    public boolean isSelected(ControlPoint point) {
        return selectedPoints.contains(point);
    }

    public int getSelectionCount() {
        return selectedPoints.size();
    }

    public Set<ControlPoint> getSelectedPoints() {
        return new HashSet<>(selectedPoints);
    }
}