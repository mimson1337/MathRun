package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp =gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }
//ustawienie defaultowych wartosci dla gracza
    public static void setDefaultValues(){
        playerX = 600;
        playerY = 800;
        speed = 5;
        direction = "rest";
    }

    //rysunki gracza potrzebne do animacji
    public void getPlayerImage(){

        try{
            rest= ImageIO.read(getClass().getResourceAsStream("/player/gracz1.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/gracz_lewo_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/gracz_lewo_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/gracz_prawo_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/gracz_prawo_2.png"));
            straight1= ImageIO.read(getClass().getResourceAsStream("/player/gracz_przod1.png"));
            straight2= ImageIO.read(getClass().getResourceAsStream("/player/gracz_przod2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

public static boolean collidesWith(Obstacle obstacle) {
    // Sprawdź czy jest kolizja pomiedzy graczem a obiektem i zwróć true lub false
    return (playerX < obstacle.ObstacleX + 260 && playerX > obstacle.ObstacleX && playerY == obstacle.ObstacleY);
}

//update gracza
    public void update() {
        //spriteCounterNumber potrzebny do animacji - im mniejszy tym szybciej gracz przebiera nogami
        spriteCounterNumber = switch (gp.gameLevel) {
            case 1 -> 16;
            case 2 -> 12;
            case 3 -> 8;
            default -> 0;
        };
        if (gp.obstacleCount < 10) {
            if (!gp.gameStarted) {
                direction = "rest";
                if (keyHandler.leftPressed || keyHandler.rightPressed) {
                    gp.gameStarted = true;
                }
            } else {
                if (keyHandler.leftPressed || keyHandler.rightPressed) {
                    gp.gameStarted = true;

                    if (keyHandler.rightPressed) {
                        playerX += speed;
                        direction = "right";

                    } else if (keyHandler.leftPressed) {
                        playerX -= speed;
                        direction = "left";
                    }


                    //ograniczenie pola gracza
                    if (playerX < 320) {
                        playerX = 320;
                    } else if (playerX > 880) {
                        playerX = 880;
                    }

                    //pętla potrzebna do animacji, wykorzystywana pozniej w metodzie draw
                    spriteCounter++;
                    if (spriteCounter > spriteCounterNumber) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                        }
                        spriteCounter = 0;
                    }
                } else {
                    direction = "run";
                    spriteCounter++;
                    if (spriteCounter > spriteCounterNumber) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                        }
                        spriteCounter = 0;
                    }
                }
            }
        }
        else if(gp.obstacleCount == 10){
            direction = "run";
            spriteCounter++;
            if(playerY > 600) {
                if(playerX == 600) {
                    if (spriteCounter > 5) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                            playerY -= 5;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                            playerY -= 5;
                        }
                        spriteCounter = 0;
                    }
                }
                if(playerX > 600) {
                    if (spriteCounter > 5) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                            playerY -= 5;
                            playerX -= 5;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                            playerY -= 5;
                            playerX -= 5;
                        }
                        spriteCounter = 0;
                    }
                }
                if(playerX < 600) {
                    if (spriteCounter > 5) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                            playerY -= 5;
                            playerX += 5;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                            playerY -= 5;
                            playerX += 5;
                        }
                        spriteCounter = 0;
                    }
                }
            }
            else{
                direction = "rest";
            }
        }
        };

    //narysowanie interfejsu graficznego podczas rozgrywki
    public void imageDraw(Graphics2D g2) {
        BufferedImage imagez;
        try {
            imagez = ImageIO.read(getClass().getResourceAsStream("/title/punkty_gracza.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(imagez, 0, 0, null);
    }

    //petla z rysowaniem i animacja. wykorzystywane sa tu wczesniej wartosci spriteNum oraz uzyto petli switch do okreslenia direction
    public void draw(Graphics2D g2){
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
            case "run":
                if(spriteNum == 1) {
                    image = straight1;
                }
                if(spriteNum == 2){
                    image = straight2;
                }
                break;
        }
        //tekst, przedstawiajacy punktacje gracza
        Font font = new Font("Arial", Font.PLAIN, 70); // Przykładowa czcionka (możesz dostosować)
        g2.setFont(font);

        // Ustaw kolor tekstu
        g2.setColor(Color.WHITE); // Przykładowy kolor (możesz dostosować)

        // Narysuj punkty gracza na panelu gry
        imageDraw(g2);
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String playerPointsText = decimalFormat.format(gp.playerPoints);

        // Pobierz szerokość i wysokość tekstu
        FontMetrics fontMetrics = g2.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(playerPointsText);
        int textHeight = fontMetrics.getHeight();

        // Oblicz nowe pozycje, aby umieścić tekst w centrum
        int num = 130 + (gp.tileSize - textWidth) / 2;
        int num1 = 420 + (gp.tileSize - textHeight) / 2;
        g2.drawString(playerPointsText, num, num1); // Dostosuj położenie tekstu
        g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
    }
}
