package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Boss extends Entity{
    GamePanel gp;
    int bossX, bossY;
    public Boss(GamePanel gp){
        this.gp =gp;

        //solidArea = new Rectangle(0,0, gp.tileSize, gp.tileSize);

        setDefaultBossValues();
        getBossImage();
    }
    public void setDefaultBossValues(){
        bossX = 560;
        bossY = 0;
        //speed = 3;
        direction = "rest";

    }


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

    public void updateBoss() {
        if(gp.obstacleCount == 10){
            direction = "run";
            spriteCounter++;
            if(bossY<400) {
                if (spriteCounter > 8) {
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
            else {
                direction = "rest";
            }

        }
    };
    public void drawBoss(Graphics2D g2) {
        BufferedImage image = null;
        if (gp.obstacleCount == 10) {
            switch (direction) {
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
