package main;

import entity.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background extends Entity {
    GamePanel gp;
    BufferedImage road1, road2, road3, road4;
    int worldX, worldY;
    public String road;
    public Background(GamePanel gp){
        this.gp =gp;


        setBackgroundDefaultValues();
        getBackgroundImage();


    }

    public void setBackgroundDefaultValues(){
        worldX = 0;
        worldY = 0;
        road = "roadStart";
    }
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
     public void updateBackground() {
         spriteCounterNumber = switch (gp.gameLevel) {
             case 1 -> 24;
             case 2 -> 18;
             case 3 -> 12;
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
