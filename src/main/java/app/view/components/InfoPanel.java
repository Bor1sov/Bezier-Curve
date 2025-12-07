package app.view.components;

import app.controller.StateController;
import app.model.ControlPoint;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class InfoPanel extends JPanel {
    private final StateController stateController;
    private JTable pointsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    
    public InfoPanel(StateController controller) {
        this.stateController = controller;
        initializePanel();
        updateInfo();
    }
    
    private void initializePanel() {
        setPreferredSize(new Dimension(300, 0));
        setBorder(BorderFactory.createTitledBorder("Информация"));
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Имя", "X", "Y"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        pointsTable = new JTable(tableModel);
        pointsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        pointsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        pointsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        
        JScrollPane scrollPane = new JScrollPane(pointsTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Готово");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statusPanel.add(statusLabel, BorderLayout.WEST);
        
        add(statusPanel, BorderLayout.SOUTH);

        JButton deleteButton = new JButton("Удалить выбранную точку");
        deleteButton.addActionListener(e -> deleteSelectedPoint());
        statusPanel.add(deleteButton, BorderLayout.EAST);
    }
    
    public void updateInfo() {

        tableModel.setRowCount(0);
        if (stateController.getCurrentCurve() != null) {
            for (ControlPoint point : stateController.getCurrentCurve().getControlPoints()) {
                tableModel.addRow(new Object[]{
                    point.getName(),
                    point.getX(),
                    point.getY()
                });
            }
        }

        updateStatus();
    }
    
    private void updateStatus() {
        if (stateController.getCurrentCurve() == null) {
            statusLabel.setText("Нет кривой");
        } else {
            int pointCount = stateController.getCurrentCurve().getControlPointCount();
            statusLabel.setText(String.format("Точек: %d", pointCount));
        }
    }
    
    private void deleteSelectedPoint() {
        int selectedRow = pointsTable.getSelectedRow();
        if (selectedRow >= 0) {
            if (stateController.getCurrentCurve() != null) {
                ControlPoint point = stateController.getCurrentCurve()
                    .getControlPoints().get(selectedRow);
                stateController.removeControlPoint(point);
            }
        }
    }
}