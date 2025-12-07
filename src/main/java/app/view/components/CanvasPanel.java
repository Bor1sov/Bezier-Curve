package app.view.components;

import app.controller.StateController;
import app.model.BezierCurve;
import app.view.renderers.CurveRenderer;
import app.view.renderers.PointRenderer;
import java.awt.*;
import javax.swing.*;


public class CanvasPanel extends JPanel {
    private final StateController stateController;
    private final CurveRenderer curveRenderer;
    private final PointRenderer pointRenderer;
    
    public CanvasPanel(StateController controller) {
        this.stateController = controller;
        this.curveRenderer = new CurveRenderer();
        this.pointRenderer = new PointRenderer();
        
        initializePanel();
    }
    
    private void initializePanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        addMouseListener(stateController.getMouseHandler());
        addMouseMotionListener(stateController.getMouseHandler());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                           RenderingHints.VALUE_RENDER_QUALITY);

        BezierCurve curve = stateController.getCurrentCurve();

        if (curve != null && !curve.isEmpty()) {
            if (stateController.getProperties().isShowControlLines()) {
                curveRenderer.drawControlLines(g2d, curve, stateController.getProperties());
            }

            curveRenderer.drawCurve(g2d, curve, stateController.getProperties());

            if (stateController.getProperties().isShowPoints()) {
                pointRenderer.drawControlPoints(g2d, curve, stateController.getProperties());
            }
        }

        drawInstructions(g2d);
    }
    
    private void drawInstructions(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        
        String[] instructions = {
            "Инструкция:",
            "• ЛКМ - добавить контрольную точку",
            "• ЛКМ на точке - выбрать точку",
            "• Перетащите точку - изменить кривую",
            "• Для кривой нужно минимум 2 точки"
        };
        
        int y = 20;
        for (String line : instructions) {
            g2d.drawString(line, 10, y);
            y += 20;
        }

        BezierCurve curve = stateController.getCurrentCurve();
        if (curve != null) {
            g2d.drawString("Точек: " + curve.getControlPointCount(), 10, y);
        }
    }
}