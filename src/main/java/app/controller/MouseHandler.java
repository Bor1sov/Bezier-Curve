package app.controller;

import app.model.ControlPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private StateController stateController;
    private ControlPoint draggedPoint;
    private boolean isDragging;
    
    public MouseHandler(StateController controller) {
        this.stateController = controller;
        this.draggedPoint = null;
        this.isDragging = false;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        ControlPoint point = stateController.findControlPointAt(e.getPoint());
        
        if (point != null) {
            draggedPoint = point;
            isDragging = true;
            point.setSelected(true);
        } else {
            stateController.addControlPoint(e.getX(), e.getY());
        }
        
        stateController.getCurrentCurve().calculateCurve();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggedPoint != null) {
            draggedPoint.setSelected(false);
        }
        draggedPoint = null;
        isDragging = false;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging && draggedPoint != null) {
            stateController.updateControlPoint(draggedPoint, e.getPoint());
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        ControlPoint point = stateController.findControlPointAt(e.getPoint());
    }
}