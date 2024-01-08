package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp =gp;
        this.keyHandler = keyHandler;

        //solidArea = new Rectangle(0,0, gp.tileSize, gp.tileSize);

        setDefaultValues();
        getPlayerImage();
    }

    public static void setDefaultValues(){
        playerX = 600;
        playerY = 800;
        speed = 5;
        direction = "rest";
    }
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
//    public static double updatePoints(double points) {
//        // Zaktualizuj punkty gracza
//        playerPoints = points;
//        return playerPoints;
//    }


//   public static boolean collidesWith(Obstacle mathObject) {
////        // Sprawdź kolizję gracza z obiektem matematycznym
////        // ...
////        if((playerX>=ObstacleX)&&(playerX<=ObstacleX+20)&&(playerY=ObstacleY)) {
////        collision= true;
////        }
////        else{
////            collision=false;
////        }
////
//    }
public static boolean collidesWith(Obstacle obstacle) {

    // Sprawdź kolizję prostokątów
    return (playerX < obstacle.ObstacleX + 220 && playerX > obstacle.ObstacleX && playerY == obstacle.ObstacleY);
     // Brak kolizji
}

//    int obstacleX = obstacle.ObstacleX;
//    int obstacleY = obstacle.ObstacleY;
//    int playerX = Player.playerX;
//    int playerY = Player.playerY;
//    int obstacleWidth = 100;  // Przykładowa szerokość przeszkody
//    int obstacleHeight = 20; // Przykładowa wysokość przeszkody

    public void update() {
//x>320 && x< 960
//        if (obstacleCount >= 9)
//            System.out.println("");
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

                    if (keyHandler.rightPressed == true) {
                        playerX += speed;
                        direction = "right";

                    } else if (keyHandler.leftPressed == true) {
                        playerX -= speed;
                        direction = "left";
                    }


                    //collision
                    if (playerX < 320) {
                        playerX = 320;
                    } else if (playerX > 880) {
                        playerX = 880;
                    }

                    gp.cChecker.checkTile(this);

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
//            playerX = 600;
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
//            spriteCounter++;
//            if (spriteCounter > 24) {
//                if (spriteNum == 1) {
//                    spriteNum = 2;
//                    playerY = 790;
//                } else if (spriteNum == 2) {
//                    spriteNum = 3;
//                    playerY = 770;
//                }else if (spriteNum == 3) {
//                    spriteNum = 4;
//                    playerY = 750;
//                }else if (spriteNum == 4) {
//                    spriteNum = 5;
//                    playerY = 730;
//                }else if (spriteNum == 5) {
//                    spriteNum = 6;
//                    playerY = 700;
//                }
//
//            }
//            int[] playerYValues = {790, 770, 750, 730, 700};
//
//            for (spriteCounter = 0; spriteCounter < 24; spriteCounter++) {
//                spriteNum++;
//
//                playerY = playerYValues[spriteNum];
//            }

        }
        };

    public void imageDraw(Graphics2D g2) {
        BufferedImage imagez;
        try {
            imagez = ImageIO.read(getClass().getResourceAsStream("/title/punkty_gracza.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Narysuj obrazek na (0, 0) bez tworzenia nowego panelu
        g2.drawImage(imagez, 0, 0, null);
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
            case "run":
                if(spriteNum == 1) {
                    image = straight1;
                }
                if(spriteNum == 2){
                    image = straight2;
                }
                break;
//            case "boss":
//                if(spriteNum == 1) {
//                    image = straight1;
//                }
//                if(spriteNum == 6){
//                    image = straight2;
//                }
//                break;
        }
        Font font = new Font("Arial", Font.PLAIN, 70); // Przykładowa czcionka (możesz dostosować)
        g2.setFont(font);

        // Ustaw kolor tekstu
        g2.setColor(Color.WHITE); // Przykładowy kolor (możesz dostosować)

        // Narysuj punkty gracza na panelu gry
        imageDraw(g2);
        String playerPointsText = "" + gp.playerPoints;
        g2.drawString(playerPointsText, 120, 420); // Dostosuj położenie tekstu
        g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
    }
}
