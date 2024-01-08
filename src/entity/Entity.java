package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public static int playerX;
    public static int playerY;
    public static int playerWidth = 80;

    public static int playerHeight = 80;

    public int speed;
    public BufferedImage rest, left1, left2, right1, right2, straight1, straight2;
    public String direction;
    public Rectangle solidArea;
    public static boolean collision = false;


    //potrzebne do animacji postaci
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteCounterNumber;

}
