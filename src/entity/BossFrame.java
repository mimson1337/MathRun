//package entity;
//
//import main.GamePanel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class BossFrame extends JFrame implements ActionListener {
//    JButton button1, button2, button3;
//    GamePanel gp;
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//    public BossFrame(GamePanel gp, int bossPoints, double playerPoints) {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.gp = gp;
//        setSize(1280, 960);
//
//
//        System.out.println(bossPoints);
//        System.out.println(playerPoints);
//
//        JPanel panel = new JPanel(new FlowLayout());
//
//        button1 = new JButton("Powtórz poziom");
//        button2 = new JButton("Następny poziom");
//        button3 = new JButton("Menu główne");
//
//        button1.addActionListener(this);
//        button2.addActionListener(this);
//        button3.addActionListener(this);
//
//        panel.setBackground(Color.BLACK);
//
//        add(panel);
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//
//        if (bossPoints > playerPoints) {
//            panel.add(button1);
//            button1.setBounds( 50, 150, 200, 50);
//        } else {
//            panel.add(button2);
//            button2.setBounds( 50, 350, 200, 50);
//        }
//
//        button3.setBounds( 100, 600, 200, 50);
//        panel.add(button3);
//
//    }
//}

package entity;

import main.GamePanel;
import main.LevelSelectionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossFrame extends JFrame implements ActionListener {
    JButton button1, button2, button3;
    GamePanel gp;


    public BossFrame(GamePanel gp, int bossPoints, double playerPoints) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gp = gp;
        setSize(gp.screenWidth, gp.screenHeight);
        setResizable(false);

        System.out.println(bossPoints);
        System.out.println(playerPoints);

        JPanel panel = new JPanel(null); // Ustawienie null layout
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));

        button1 = new JButton("Powtórz poziom");
        button2 = new JButton("Następny poziom");
        button3 = new JButton("Menu główne");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        // Ustawienie pozycji i rozmiarów przycisków
        if (bossPoints > playerPoints) {
            button1.setBounds(50, 150, 200, 50);
            panel.add(button1);
        } else if(gp.gameLevel<3 && bossPoints <= playerPoints){
            button2.setBounds(50, 350, 200, 50);
            panel.add(button2);
        }

        button3.setBounds(100, 600, 200, 50);
        panel.add(button3);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            // Obsługa przycisku "Powtórz poziom"
            restart();
        } else if (e.getSource() == button2) {
            startNewLevel();
            // Obsługa przycisku "Następny poziom"
            // Tu możesz dodać kod obsługi dla przejścia do następnego poziomu
        } else if (e.getSource() == button3) {
//            JFrame gamePanelFrame = (JFrame) SwingUtilities.getWindowAncestor(gp);
//            gamePanelFrame.dispose();
            mainMenu();
//            gamePanelFrame.add(gp);
//            gamePanelFrame.setVisible(true);
            // Obsługa przycisku "Menu główne"
            // Tu możesz dodać kod obsługi dla powrotu do menu głównego
        }
    }

//    private void restartGame() {
//        // Utwórz nowy panel gry i zainicjuj go na tym samym poziomie
//        GamePanel newGamePanel = new GamePanel();
//        newGamePanel.gameLevel = gp.gameLevel;
//        Player.setDefaultValues();
//        Boss.setDefaultBossValues();
//
//        // Zamknij aktualne okno BossFrame
//        this.dispose();
//
//        // Uruchom nową grę z nowym panelem
//        BossFrame newBossFrame = new BossFrame(newGamePanel, 0, 0);
//        newGamePanel.startGameThread();
//        newGamePanel.requestFocus();
//        newGamePanel.setFocusable(true);

    public void restart(){
//        gp.gameStarted = false;
//        gp.playerPoints = 1;
//        Player.setDefaultValues();
//        Boss.setDefaultBossValues();
//        gp.isFinished = false;
//        gp.obstacleCount = 0;
        restartPrepare();
        gp.startGameThread();
//        this.dispose();
    }

    public void startNewLevel(){
//        gp.gameStarted = false;
//        gp.playerPoints = 1;
//        Player.setDefaultValues();
//        Boss.setDefaultBossValues();
//        gp.isFinished = false;
        restartPrepare();
        gp.gameLevel++;
//        gp.obstacleCount = 0;
        gp.startGameThread();
//        this.dispose();
    }
    public void mainMenu(){
        restartPrepare();
        LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame(gp);
        levelSelectionFrame.setVisible(true);
        gp.startGameThread();

    }

    public void restartPrepare(){
        gp.gameStarted = false;
        gp.playerPoints = 1;
        Player.setDefaultValues();
        Boss.setDefaultBossValues();
        gp.isFinished = false;
        gp.obstacleCount = 0;
        this.dispose();
    }
}

//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("MathRun");
//
//// Ustawienie gamePanel w oknie gry
//        window.add(gp);
//        window.pack();
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);

// Uruchomienie wątku gry