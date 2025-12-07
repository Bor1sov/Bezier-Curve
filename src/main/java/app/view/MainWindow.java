package app.view;

import app.controller.StateController;
import app.view.components.CanvasPanel;
import app.view.components.InfoPanel;
import java.awt.*;
import javax.swing.*;


public class MainWindow extends JFrame {
    private CanvasPanel canvasPanel;
    private InfoPanel infoPanel;
    private StateController stateController;
    
    public MainWindow() {
        initializeWindow();
        initializeComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initializeWindow() {
        setTitle("Редактор кривых Безье");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 800));
        setMinimumSize(new Dimension(800, 600));
    }
    
    private void initializeComponents() {
        stateController = new StateController();
        canvasPanel = new CanvasPanel(stateController);
        infoPanel = new InfoPanel(stateController);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());

        add(infoPanel, BorderLayout.WEST);

        add(canvasPanel, BorderLayout.CENTER);

        add(createControlPanel(), BorderLayout.SOUTH);

        setJMenuBar(createMenuBar());
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton clearButton = new JButton("Очистить");
        clearButton.addActionListener(e -> stateController.clear());
        
        JButton toggleLinesButton = new JButton("Скрыть/показать линии");
        toggleLinesButton.addActionListener(e -> stateController.toggleControlLines());
        
        JButton togglePointsButton = new JButton("Скрыть/показать точки");
        togglePointsButton.addActionListener(e -> stateController.toggleControlPoints());
        
        panel.add(clearButton);
        panel.add(toggleLinesButton);
        panel.add(togglePointsButton);
        
        return panel;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("Вид");
        JCheckBoxMenuItem showLinesItem = new JCheckBoxMenuItem("Показать контрольные линии", true);
        showLinesItem.addActionListener(e -> stateController.setShowControlLines(showLinesItem.isSelected()));
        
        JCheckBoxMenuItem showPointsItem = new JCheckBoxMenuItem("Показать контрольные точки", true);
        showPointsItem.addActionListener(e -> stateController.setShowControlPoints(showPointsItem.isSelected()));
        
        viewMenu.add(showLinesItem);
        viewMenu.add(showPointsItem);

        JMenu helpMenu = new JMenu("Справка");
        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "Редактор кривых Безье\n\n" +
            "Возможности:\n" +
            "• Добавление точек кликом мыши\n" +
            "• Перетаскивание точек\n" +
            "• Реальное обновление кривой\n" +
            "• Поддержка замкнутых кривых\n\n" +
            "Используйте:\n" +
            "ЛКМ - добавить/выбрать точку\n" +
            "Перетащите - переместить точку",
            "О программе",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void setupListeners() {
    stateController.addStateChangeListener(e -> {
            canvasPanel.repaint();
            infoPanel.updateInfo();
        });
    }
    
    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }
}