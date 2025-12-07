package app.controller;

import app.model.BezierCurve;
import app.model.ControlPoint;
import app.model.CurveProperties;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class StateController {
    private BezierCurve currentCurve;
    private CurveProperties properties;
    private MouseHandler mouseHandler;
    private SelectionManager selectionManager;
    private List<ChangeListener> changeListeners;
    
    public StateController() {
        this.currentCurve = new BezierCurve("Основная кривая");
        this.properties = new CurveProperties();
        this.mouseHandler = new MouseHandler(this);
        this.selectionManager = new SelectionManager();
        this.changeListeners = new ArrayList<>();
    }
    
    public BezierCurve getCurrentCurve() {
        return currentCurve;
    }
    
    public CurveProperties getProperties() {
        return properties;
    }
    
    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }
    
    public SelectionManager getSelectionManager() {
        return selectionManager;
    }

    public void addControlPoint(int x, int y) {
        currentCurve.addControlPoint(x, y);
        fireStateChanged();
    }
    

    public void removeControlPoint(ControlPoint point) {
        currentCurve.removeControlPoint(point);
        fireStateChanged();
    }

    public void updateControlPoint(ControlPoint point, java.awt.Point newPosition) {
        currentCurve.updateControlPoint(point, newPosition);
        fireStateChanged();
    }
    

    public ControlPoint findControlPointAt(java.awt.Point location) {
        return currentCurve.findControlPointAt(location);
    }

    public void clear() {
        currentCurve = new BezierCurve("Основная кривая");
        selectionManager.clearSelection();
        fireStateChanged();
    }
    

    public void toggleControlLines() {
        properties.setShowControlLines(!properties.isShowControlLines());
        fireStateChanged();
    }

    public void toggleControlPoints() {
        properties.setShowPoints(!properties.isShowPoints());
        fireStateChanged();
    }

    public void setShowControlLines(boolean show) {
        properties.setShowControlLines(show);
        fireStateChanged();
    }
    

    public void setShowControlPoints(boolean show) {
        properties.setShowPoints(show);
        fireStateChanged();
    }

    public void addStateChangeListener(ChangeListener listener) {
        changeListeners.add(listener);
    }
    private void fireStateChanged() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged(event);
        }
    }

    public void addStateChangeListener(Object listener) {
        throw new UnsupportedOperationException("Unimplemented method 'addStateChangeListener'");
    }
}