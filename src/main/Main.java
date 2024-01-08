package main;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import entity.BossFrame;
import main.GamePanel;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();

// Tworzymy okno do wyboru poziomu, przekazując gamePanel jako argument
        LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame(gamePanel);
        levelSelectionFrame.setVisible(true);

        //BossFrame bossFrame = new BossFrame(gamePanel);

// Czekamy, aż użytkownik wybierze poziom
        while (!levelSelectionFrame.isLevelChosen) {
            try {
                Thread.sleep(100); // Oczekiwanie na wybór poziomu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MathRun");

// Ustawienie gamePanel w oknie gry
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

// Uruchomienie wątku gry
        gamePanel.startGameThread();
        // Press Shift+F10 or click the green arrow button in the gutter to run the code.

    }
}
