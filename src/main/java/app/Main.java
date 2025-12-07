package app;

import app.view.MainWindow;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            } catch (Exception e) {
                System.err.println("Ошибка запуска приложения: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}