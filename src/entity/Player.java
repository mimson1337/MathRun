package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp =gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 640;
        y = 800;
        speed = 4;
        direction = "rest";
    }
    public void getPlayerImage(){

        try{
            rest= ImageIO.read(getClass().getResourceAsStream("/player/gracz1.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/gracz_lewo_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/gracz_lewo_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/gracz_prawo_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/gracz_prawo_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            if (keyHandler.rightPressed == true) {
                x += speed;
                direction = "right";

            } else if (keyHandler.leftPressed == true) {
                x -= speed;
                direction = "left";
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            direction = "rest";
        }

    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);

        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction){
            case "rest":
                    image = rest;
            break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,x,y, gp.tileSize, gp.tileSize, null);
    }
}
