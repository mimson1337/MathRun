package main;

import entity.Boss;
import entity.Entity;
import entity.Obstacle;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GamePanel extends JPanel implements Runnable{


    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16
    final int scale = 5;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth = tileSize * maxScreenCol; //1280
    final int screenHeight = tileSize * maxScreenRow; //960

    //FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    //CZAS
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this, keyHandler);
    Boss boss = new Boss(this);
    Background background = new Background(this);
    Obstacle obstacle = new Obstacle(this);
    public boolean gameStarted = false;
    public int gameLevel;

    Button button1,button2,button3;
    public int ObstacleSpeed;
    public int obstacleCount = 0;
    public int bossPoints;
    public static double playerPoints = 1;


    

    public GamePanel(){
//        super();

//        super.setSize(400,600);
//        super.setVisible(true);
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

    @Override
    public void run() {
            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

        switch (gameLevel) {
            case 1:
                ObstacleSpeed = 2;
                break;
            case 2:
                ObstacleSpeed = 4;
                break;
            case 3:
                ObstacleSpeed = 8;
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
