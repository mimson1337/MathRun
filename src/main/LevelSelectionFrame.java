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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelSelectionFrame extends JFrame implements ActionListener {

    JButton button1, button2, button3, button4;
    GamePanel gamePanel;
    public boolean isLevelChosen = false;

    public LevelSelectionFrame(GamePanel gp) {
        super("Wybór Poziomu");
        setSize(gp.screenWidth, gp.screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.gamePanel = gp;
        BufferedImage image, image1, image2, image3, image4 = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/title/Title.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/title/poziom_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/title/poziom_2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/title/poziom_3.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/title/koniec.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, 1280, 960);


        JPanel panel = new JPanel(null); // Ustawienie null layout
        panel.setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        panel.add(backgroundLabel);

        button1 = new JButton(new ImageIcon(image1));
        button2 = new JButton(new ImageIcon(image2));
        button3 = new JButton(new ImageIcon(image3));
        button4 = new JButton(new ImageIcon(image4));

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        // Ustawienie pozycji i rozmiarów przycisków
        button1.setBounds(256, 450, 256, 150);
        button2.setBounds(512, 450, 256, 150);
        button3.setBounds(768, 450, 256, 150);
        button4.setBounds(512, 600, 256, 150);

        backgroundLabel.add(button1);
        backgroundLabel.add(button2);
        backgroundLabel.add(button3);
        backgroundLabel.add(button4);

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
        }else if (e.getSource() == button4) {
            System.exit(0);
        }

        isLevelChosen = true; // Oznaczamy, że poziom został wybrany
        dispose();
    }
}

