//package main;
//import javax.swing.*;
//import java.awt.*;
//
//import static java.awt.SystemColor.window;
//
//public class GameFrame extends JFrame {
//    GamePanel gp;
//
//    public GameFrame(GamePanel gamePanel, int selectedLevel) {
//        super("Gra - Poziom " + selectedLevel);
//        setSize(1280, 960);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        this.gp = gamePanel; // Przypisz przekazany GamePanel do zmiennej instancji
//
//        this.add(gamePanel);
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//        gamePanel.startGameThread();
//    }
//
//    public static void main(String[] args) {
//        // Inicjalizacja gamePanel przed utworzeniem LevelSelectionFrame
//        GamePanel gamePanel = new GamePanel();
//
//        // Tworzymy okno do wyboru poziomu, przekazując gamePanel jako argument
//        LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame(gamePanel);
//        levelSelectionFrame.setVisible(true);
//
//        // Czekamy, aż użytkownik wybierze poziom
//        while (!levelSelectionFrame.isLevelChosen) {
//            try {
//                Thread.sleep(100); // Oczekiwanie na wybór poziomu
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Uruchamiamy grę z wybranym poziomem
//        SwingUtilities.invokeLater(() -> new GameFrame(gamePanel, gamePanel.gameLevel));
//    }
//
//}
