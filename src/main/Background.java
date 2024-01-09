package main;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background extends Entity {
    GamePanel gp;
    BufferedImage road1, road2, road3, road4;
    int worldX, worldY;
    public String road;

    //konstruktor
    public Background(GamePanel gp){
        this.gp =gp;
        setBackgroundDefaultValues();
        getBackgroundImage();
    }

    /**
     * ustawienie defaultowych wartosci backgroundu w gamePanel
     */
    public void setBackgroundDefaultValues(){
        worldX = 0;
        worldY = 0;
        road = "roadStart";
    }

    /**
     * rysunki potrzebne do animacji tła
     */
    public void getBackgroundImage(){
        try
        {
        road1= ImageIO.read(getClass().getResourceAsStream("/background/droga1.png"));
        road2= ImageIO.read(getClass().getResourceAsStream("/background/droga2.png"));
        road3= ImageIO.read(getClass().getResourceAsStream("/background/droga3.png"));
        road4= ImageIO.read(getClass().getResourceAsStream("/background/droga4.png"));
         }
        catch (IOException e)
        {
        e.printStackTrace();
        }
    }

    /**
     * update tła, spriteCounterNumber wykorzystywany dla różnych poziomów po to, aby animacja zwiększała swoja predkosc wraz z zwiekszana predkoscia faktyczna obiektów spadających
     */
     public void updateBackground() {
         spriteCounterNumber = switch (gp.gameLevel) {
             case 1 -> 16;
             case 2 -> 12;
             case 3 -> 8;
             default -> 0;
         };
         if(gp.obstacleCount < 10) {
             if (gp.gameStarted == false) {
                 road = "roadStart";
             } else {
                 road = "roadGo";
                 spriteCounter++;
                 if (spriteCounter > spriteCounterNumber) {
                     if (spriteNum == 1) {
                         spriteNum = 2;
                     } else if (spriteNum == 2) {
                         spriteNum = 3;
                     } else if (spriteNum == 3) {
                         spriteNum = 4;
                     } else if (spriteNum == 4) {
                         spriteNum = 1;
                     }
                     spriteCounter = 0;
                 }
             }
         }
    }

    /**
     * rysowanie tła, znów wykorzystanie buffered image i przede wszystkim spriteNum do animacji
     * @param g2
     */
    public void drawBackground(Graphics2D g2){
        BufferedImage image = null;
        switch (road){
            case "roadStart":
                image = road1;
                break;
            case "roadGo":
                if(spriteNum == 1) {
                    image = road1;
                }
                if(spriteNum == 2){
                    image = road2;
                }
                if(spriteNum == 3) {
                    image = road3;
                }
                if(spriteNum == 4){
                    image = road4;
                }
                break;

        }
        g2.drawImage(image,320,0, 640, 960, null);
    }
}
