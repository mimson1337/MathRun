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
import java.text.DecimalFormat;

public class BossFrame extends JFrame implements ActionListener {
    JButton button1, button2, button3;
    GamePanel gp;


    public BossFrame(GamePanel gp, int bossPoints, double playerPoints) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gp = gp;
        setResizable(false);

        //JLabel potrzebny do wyświetlenia backgroundu outcome1/outcome6
        JLabel backgroundLabel;
        //zdjęcia tła, jak i przycisków
        BufferedImage image1, image2, image3, image4, image5;
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/title/outcome1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/title/outcome6.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/title/back_to_menu.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/title/next_level.png"));
            image5 = ImageIO.read(getClass().getResourceAsStream("/title/retry_level.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //petla if potrzebna do okreslenia, jaki jest outcome
        if (bossPoints > playerPoints) {
            ImageIcon imageIcon = new ImageIcon(image1);
            backgroundLabel = new JLabel(imageIcon);
        } else {
            ImageIcon imageIcon = new ImageIcon(image2);
            backgroundLabel = new JLabel(imageIcon);
        }
        //wartosci backgroundLabel oraz dodanie do panelu
        backgroundLabel.setBounds(0, 0, gp.screenWidth, gp.screenHeight);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(image1.getWidth(), image1.getHeight());
        panel.add(backgroundLabel);

        //zmienne potrzebne do ograniczenia wyswietlanej liczby do jednej liczby po przecinku. wykorzystywane równiez w klasie player przy wyswietlaniu punktów gracza
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String formattedPlayerPoints = decimalFormat.format(playerPoints);
        String formattedBossPoints = decimalFormat.format(bossPoints);

        //wyswietlanie ostatecznych punktów gracza jak i bossa
        JLabel playerPointsLabel = new JLabel("" + formattedPlayerPoints);
        JLabel bossPointsLabel = new JLabel("" + formattedBossPoints);
        playerPointsLabel.setForeground(Color.BLACK);
        bossPointsLabel.setForeground(Color.BLACK);

        //ustawienia czcionki, z jaka wyswietlana sa punkty gracza i bossa
        Font font = new Font("Arial", Font.PLAIN, 70); // Przykładowa czcionka (możesz dostosować)
        playerPointsLabel.setFont(font);
       bossPointsLabel.setFont(font);

        // Ustawienie pozycji etykiet
        bossPointsLabel.setBounds(300, 550, 250, 100);
        playerPointsLabel.setBounds(850, 550, 250, 100);

        // Dodanie etykiet do panelu
        backgroundLabel.add(playerPointsLabel);
        backgroundLabel.add(bossPointsLabel);

        button1 = new JButton(new ImageIcon(image5));
        button2 = new JButton(new ImageIcon(image4));
        button3 = new JButton(new ImageIcon(image3));

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

    /**
     * actionPerformed potrzebny do obsługi przycisków
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            // Obsługa przycisku "Powtórz poziom"
            restart();
        } else if (e.getSource() == button2) {
            startNewLevel();
            // Obsługa przycisku "Następny poziom"
        } else if (e.getSource() == button3) {
            mainMenu();
            // Obsługa przycisku "Wróć do głównego menu"
        }
    }

    /**
     * metoda używana przy "Powtórz poziom"
     */
    public void restart(){
        restartPrepare();
        gp.startGameThread();
    }

    /**
     * metoda używana przy "Następny poziom"
     */
    public void startNewLevel(){
        restartPrepare();
        gp.gameLevel++;
        gp.startGameThread();
    }

    /**
     * metoda używana przy "Wróć do głównego menu"
     */
    public void mainMenu(){
        restartPrepare();
        LevelSelectionFrame levelSelectionFrame = new LevelSelectionFrame(gp);
        levelSelectionFrame.setVisible(true);
        gp.startGameThread();

    }

    /**
     * metoda używana przy wszystkich 3 metodach związanych z restartowaniem gry - zresetowanie wartosci i przygotowanie do kolejnej gry
     */
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
