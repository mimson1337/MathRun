package main;

import entity.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {


    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16
    final int scale = 5;
    public final int tileSize = originalTileSize * scale; //5x16=80 czyli gracz jest 80x80
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    public final int screenWidth = tileSize * maxScreenCol; //1280
    public final int screenHeight = tileSize * maxScreenRow; //960

    //FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;//CZAS
    Player player = new Player(this, keyHandler);
    Boss boss = new Boss(this);
    Background background = new Background(this);
    Obstacle obstacle = new Obstacle(this);
    public boolean gameStarted = false; //czy zaczęła się gra
    public int gameLevel; //poziom gry
    public int ObstacleSpeed; //predkosc obiektów spadających
    public int obstacleCount = 0; //ilosc obiektów spadających
    public int bossPoints =0; //punkty bossa
    public static double playerPoints = 1; //punkty gracza
    public boolean isFinished = false; //Czy boss juz skonczył chodzić


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void bossPointsBound(){
        Random random = new Random();
        int min = 0; // Minimalna wartość X
        int max = 0; // Maksymalna wartość X
        switch (gameLevel) {
            case 1:
                min = 5; // Minimalna wartość X
                max = 30; // Maksymalna wartość X
                break;
            case 2:
                min = 30; // Minimalna wartość X
                max = 99; // Maksymalna wartość X
                break;
            case 3:
                min = 100; // Minimalna wartość X
                max = 300; // Maksymalna wartość X
                break;
        }
        bossPoints = Math.min(max, Math.max(min, random.nextInt(max - min + 1) + min));
    }

    //w run znajduje się głowna pętla gry posiadająca metode update i paintComponent
    @Override
    public void run() {
            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

        switch (gameLevel) {
            case 1:
                ObstacleSpeed = 8;
                break;
            case 2:
                ObstacleSpeed = 10;
                break;
            case 3:
                ObstacleSpeed = 16;
                break;
        }

        bossPointsBound();

            while (gameThread != null) {


                update();

                repaint();


                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime / 1000000;
                    if (remainingTime < 0) {
                        remainingTime = 0;
                    }
                    Thread.sleep((long) remainingTime);
                    nextDrawTime += drawInterval;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(isFinished){
                    gameThread = null;
                }

            }
            //boss wyświetla się gdy gra jest zakonczona
        if (isFinished) {
            isFinished = false;
            BossFrame bossFrame = new BossFrame(this, bossPoints, playerPoints);
            bossFrame.setSize(screenWidth, screenHeight);
            bossFrame.setVisible(true);
            bossFrame.setLocationRelativeTo(null);
        }


    }

    public void update(){
        player.update();
        boss.updateBoss();
        obstacle.updateObstacle();
        background.updateBackground();


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        background.drawBackground(g2);
        obstacle.drawObstacle(g2);
        player.draw(g2);
        boss.drawBoss(g2);

        g2.dispose();
    }

}
