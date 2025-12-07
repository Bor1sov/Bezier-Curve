package app.view.renderers;

import java.awt.Graphics2D;

public abstract class Renderer {
    protected void setGraphicsQuality(Graphics2D g2d) {
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                           java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                           java.awt.RenderingHints.VALUE_RENDER_QUALITY);
    }
}