package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public BufferedImage rest, left1, left2, right1, right2;
    public String direction;


    //potrzebne do animacji postaci
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
