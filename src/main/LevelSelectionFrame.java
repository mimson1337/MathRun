//package main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LevelSelectionFrame extends JFrame implements ActionListener {
//
//    JButton button1, button2, button3;
//    GamePanel gamePanel;
//    boolean isLevelChosen = false;
//
//    public LevelSelectionFrame(GamePanel gp) {
//        super("Wybór Poziomu");
//        setSize(1280, 960);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        this.gamePanel = gp;
//
//
//
//        JPanel panel = new JPanel();
//        button1 = new JButton("Poziom 1");
//        button2 = new JButton("Poziom 2");
//        button3 = new JButton("Poziom 3");
//
//        button1.addActionListener(this);
//        button2.addActionListener(this);
//        button3.addActionListener(this);
//
//        panel.add(button1);
//        button1.setBounds( 50, 50, 200, 50);
//        panel.add(button2);
//        button1.setBounds( 111, 150, 200, 50);
//        panel.add(button3);
//        button1.setBounds( 222, 250, 200, 50);
//        panel.setBackground(Color.BLACK);
//
//        add(panel);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        // Usuń tę linię, ponieważ tworzenie nowego GamePanela powoduje problemy
//        // gp = new GamePanel(gp.gameLevel);
//
//        gamePanel.gameLevel = 1; // Domyślnie ustaw poziom na 1
//
//        if (e.getSource() == button1) {
//            gamePanel.gameLevel = 1;
//        } else if (e.getSource() == button2) {
//            gamePanel.gameLevel = 2;
//        } else if (e.getSource() == button3) {
//            gamePanel.gameLevel = 3;
//        }
//        isLevelChosen = true; // Oznaczamy, że poziom został wybrany
//        dispose();
//    }
//}

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelectionFrame extends JFrame implements ActionListener {

    JButton button1, button2, button3;
    GamePanel gamePanel;
    boolean isLevelChosen = false;

    public LevelSelectionFrame(GamePanel gp) {
        super("Wybór Poziomu");
        setSize(1280, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gamePanel = gp;

        JPanel panel = new JPanel(null); // Ustawienie null layout
        panel.setBackground(Color.BLACK);

        button1 = new JButton("Poziom 1");
        button2 = new JButton("Poziom 2");
        button3 = new JButton("Poziom 3");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        // Ustawienie pozycji i rozmiarów przycisków
            button1.setBounds(50, 50, 500, 50);
        button2.setBounds(111, 150, 200, 50);
        button3.setBounds(222, 250, 200, 50);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        gamePanel.gameLevel = 1; // Domyślnie ustaw poziom na 1

        if (e.getSource() == button1) {
            gamePanel.gameLevel = 1;
        } else if (e.getSource() == button2) {
            gamePanel.gameLevel = 2;
        } else if (e.getSource() == button3) {
            gamePanel.gameLevel = 3;
        }
        isLevelChosen = true; // Oznaczamy, że poziom został wybrany
        dispose();
    }
}

