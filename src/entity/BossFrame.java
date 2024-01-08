
package entity;

import main.GamePanel;
import main.LevelSelectionFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BossFrame extends JFrame implements ActionListener {
    JButton button1, button2, button3;
    GamePanel gp;


    public BossFrame(GamePanel gp, int bossPoints, double playerPoints) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gp = gp;
        //setSize(gp.screenWidth, gp.screenHeight);
        setResizable(false);

        JLabel backgroundLabel;
        BufferedImage image1, image2;
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/title/outcome1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/title/outcome6.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (bossPoints > playerPoints) {
            ImageIcon imageIcon = new ImageIcon(image1);
            backgroundLabel = new JLabel(imageIcon);
        } else {
            ImageIcon imageIcon = new ImageIcon(image2);
            backgroundLabel = new JLabel(imageIcon);
        }

        backgroundLabel.setBounds(0, 0, gp.screenWidth, gp.screenHeight);

        System.out.println(bossPoints);
        System.out.println(playerPoints);

        JPanel panel = new JPanel(new FlowLayout()); // Ustawienie null layout
        panel.setSize(image1.getWidth(), image1.getHeight());
        panel.add(backgroundLabel);


        button1 = new JButton("Powtórz poziom");
        button2 = new JButton("Następny poziom");
        button3 = new JButton("Menu główne");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        // Ustawienie pozycji i rozmiarów przycisków
        if (bossPoints > playerPoints) {
            button1.setBounds(200, 700, 250, 150);
            backgroundLabel.add(button1);
        } else if(gp.gameLevel<3 && bossPoints <= playerPoints){
            button2.setBounds(200, 700, 250, 150);
            backgroundLabel.add(button2);
        }

        button3.setBounds(850, 700, 250, 150);
        backgroundLabel.add(button3);

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
            mainMenu();
        }
    }

    public void restart(){
        restartPrepare();
        gp.startGameThread();
    }

    public void startNewLevel(){
        restartPrepare();
        gp.gameLevel++;
        gp.startGameThread();
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
