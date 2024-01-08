package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelectionFrame extends JFrame implements ActionListener {

    JButton button1, button2, button3;
    GamePanel gp;
    boolean isLevelChosen = false;

    public LevelSelectionFrame(GamePanel gp) {
        super("Wybór Poziomu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gp = gp;

        button1 = new JButton("Poziom 1");
        button2 = new JButton("Poziom 2");
        button3 = new JButton("Poziom 3");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Usuń tę linię, ponieważ tworzenie nowego GamePanela powoduje problemy
        // gp = new GamePanel(gp.gameLevel);

        gp.gameLevel = 1; // Domyślnie ustaw poziom na 1

        if (e.getSource() == button1) {
            gp.gameLevel = 1;
        } else if (e.getSource() == button2) {
            gp.gameLevel = 2;
        } else if (e.getSource() == button3) {
            gp.gameLevel = 3;
        }

        isLevelChosen = true; // Oznaczamy, że poziom został wybrany

        // Zamknij okno wyboru poziomu
        dispose();

        // Usuń tę linijkę, ponieważ tworzenie nowego GameFrame powoduje problemy
        // new GameFrame(gp.gameLevel);

        // Uruchom wątek gry w obecnym GamePanelu z aktualnym poziomem
        gp.startGameThread();
    }

//    public static void main(String[] args) {
//        GamePanel gamePanel = new GamePanel();
//        SwingUtilities.invokeLater(() -> new LevelSelectionFrame(gamePanel));
//    }
}
