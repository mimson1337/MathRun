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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossFrame extends JFrame implements ActionListener {
    JButton button1, button2, button3;
    GamePanel gp;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obsługa zdarzeń przycisków
    }

    public BossFrame(GamePanel gp, int bossPoints, double playerPoints) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gp = gp;
        setSize(1280, 960);

        System.out.println(bossPoints);
        System.out.println(playerPoints);

        JPanel panel = new JPanel(null); // Ustawienie null layout
        panel.setBackground(Color.BLACK);

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
        } else {
            button2.setBounds(50, 350, 200, 50);
            panel.add(button2);
        }

        button3.setBounds(100, 600, 200, 50);
        panel.add(button3);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


