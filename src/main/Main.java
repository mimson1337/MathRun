package main;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import main.GamePanel;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        //System.out.printf("Hello and welcome!");
        // Tworzymy okno do wyboru poziomu
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
//        // Uruchamiamy grę z wybranym poziomem
//        SwingUtilities.invokeLater(() -> new GameFrame(levelSelectionFrame.gp.gameLevel));
//
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("MathRun");
////        KeyHandler keyH = new KeyHandler();
//        GamePanel gamePanel = new GamePanel(levelSelectionFrame.gp.gameLevel);
//        window.add(gamePanel);
//
//        window.pack();
//
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
//        gamePanel.startGameThread();

// Inicjalizacja gamePanel przed utworzeniem LevelSelectionFrame
        // Inicjalizacja gamePanel, przekazując domyślny poziom gry
        // Inicjalizacja gamePanel przed utworzeniem LevelSelectionFrame
        GamePanel gamePanel = new GamePanel();

// Tworzymy okno do wyboru poziomu, przekazując gamePanel jako argument
        LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame(gamePanel);
        levelSelectionFrame.setVisible(true);

// Czekamy, aż użytkownik wybierze poziom
        while (!levelSelectionFrame.isLevelChosen) {
            try {
                Thread.sleep(100); // Oczekiwanie na wybór poziomu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

// Utwórz finalną zmienną gamePanel do użycia w lambdzie
        final GamePanel finalGamePanel = gamePanel;

// Uruchamiamy grę z wybranym poziomem
        //SwingUtilities.invokeLater(() -> new GameFrame(finalGamePanel, levelSelectionFrame.gp.gameLevel));

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MathRun");

// Ustawienie gamePanel w oknie gry
        window.add(finalGamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

// Uruchomienie wątku gry
        finalGamePanel.startGameThread();

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.

    }
}