package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Boss extends Entity{
    GamePanel gp;
    static int bossX, bossY;
    static String bossDirection;

    public Boss(GamePanel gp){
        this.gp =gp;
        setDefaultBossValues();
        getBossImage();
    }

    //defaultowe wartosci dla bossa
    public static void setDefaultBossValues(){
        bossX = 560;
        bossY = 0;
        //speed = 3;
        bossDirection = "rest";

    }

    //rysunki bossa potrzebne do animacji(wykorzystane w funkcji drawBoss)
    public void getBossImage(){

        try{
            rest= ImageIO.read(getClass().getResourceAsStream("/boss/boss_rest.png"));
            straight1= ImageIO.read(getClass().getResourceAsStream("/boss/boss_walk1.png"));
            straight2= ImageIO.read(getClass().getResourceAsStream("/boss/boss_walk2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //petla update boss, ktora odpala sie tylko wtedy gdy pojawia sie 10 obiekt (gp.obstacleCount == 10) oraz to w tej petli wykorzystywany jest bool isFinished
    public void updateBoss() {
        if(gp.obstacleCount == 10){
            bossDirection = "run";
            spriteCounter++;
            if(bossY<400) {
                //pętla potrzebna do animacji, wykorzystywana pozniej w metodzie draw
                if (spriteCounter > 3) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                        bossY += 5;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                        bossY += 5;
                    }
                    spriteCounter = 0;
                }
            }
            //wykorzystanie isFinished, potrzebne do wyswietlenia panelu BossFrame
            else if(bossY==400){
                bossDirection = "rest";
                gp.isFinished = true;
                bossY ++;
            }

        }
    };

    //metoda rysowania bossa - w zaleznosci od spriteNum, wyswietlany jest obrazek(podobnie jak w metodzie draw() w klasie Player)
    public void drawBoss(Graphics2D g2) {
        BufferedImage image = null;
        if (gp.obstacleCount == 10) {
            switch (bossDirection) {
                case "rest":
                    image = rest;
                    break;
                case "run":
                    if (spriteNum == 1) {
                        image = straight1;
                    }
                    if (spriteNum == 2) {
                        image = straight2;
                    }
                    break;
            }
            g2.drawImage(image, bossX, bossY, 150, 150, null);
        }
    }
}
