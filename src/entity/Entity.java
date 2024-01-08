package entity;
import java.awt.image.BufferedImage;

public class Entity {
    public static int playerX;
    public static int playerY; //koordynaty x i y gracza

    public static int speed; // szybkosc gracza
    public BufferedImage rest, left1, left2, right1, right2, straight1, straight2; //rysunki potrzebne do animacji
    public static String direction; //direction potrzebny do animacji


    //potrzebne do animacji tła i postaci
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteCounterNumber;

}
