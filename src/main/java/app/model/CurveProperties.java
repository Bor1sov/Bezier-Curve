package app.model;

import java.awt.Color;

public class CurveProperties {
    private Color curveColor;
    private Color controlLineColor;
    private Color controlPointColor;
    private Color selectedPointColor;
    private int curveWidth;
    private int controlLineWidth;
    private boolean showControlLines;
    private boolean showPoints;
    
    public CurveProperties() {
        this.curveColor = Color.BLUE;
        this.controlLineColor = Color.LIGHT_GRAY;
        this.controlPointColor = Color.RED;
        this.selectedPointColor = Color.GREEN;
        this.curveWidth = 2;
        this.controlLineWidth = 1;
        this.showControlLines = true;
        this.showPoints = true;
    }

    public Color getCurveColor() { return curveColor; }
    public void setCurveColor(Color color) { this.curveColor = color; }
    
    public Color getControlLineColor() { return controlLineColor; }
    public void setControlLineColor(Color color) { this.controlLineColor = color; }
    
    public Color getControlPointColor() { return controlPointColor; }
    public void setControlPointColor(Color color) { this.controlPointColor = color; }
    
    public Color getSelectedPointColor() { return selectedPointColor; }
    public void setSelectedPointColor(Color color) { this.selectedPointColor = color; }
    
    public int getCurveWidth() { return curveWidth; }
    public void setCurveWidth(int width) { this.curveWidth = width; }
    
    public int getControlLineWidth() { return controlLineWidth; }
    public void setControlLineWidth(int width) { this.controlLineWidth = width; }
    
    public boolean isShowControlLines() { return showControlLines; }
    public void setShowControlLines(boolean show) { this.showControlLines = show; }
    
    public boolean isShowPoints() { return showPoints; }
    public void setShowPoints(boolean show) { this.showPoints = show; }
}