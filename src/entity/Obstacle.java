package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Obstacle extends Entity {
    GamePanel gp;
    String equation;
    double multiply, subtract, add, divide, number;
    int ObstacleX;
    int ObstacleY;

    public List<Obstacle> mathObjects;
    String finalEquation;
    private boolean shouldRespawn = false;



    public Obstacle(GamePanel gp) {
        this.gp = gp;
        mathObjects = new ArrayList<>();
        setObstacleDefaultValues(this);
    }

    public void didCollide(double num, String eq) {
        // Aktualizuj położenie obiektów matematycznych

        // Sprawdź kolizję z graczem
        for (Obstacle mathObject : mathObjects) {
            if (Player.collidesWith(mathObject)) {
                calculateResult(eq, num);
                // Dodaj nowy obiekt matematyczny po zderzeniu
            }
        }
    }


    public void setObstacleDefaultValues(Obstacle mathObject) {
        ObstacleX = getRandomXPosition();
        ObstacleY = 0;

        number = getRandomNumber();
        equation = getRandomOperator();
        finalEquation = getRandomMathExpression(number, equation);
        mathObjects.add(this);
    }


    public void updateObstacle() {

        if (gp.gameStarted) {
            // Aktualizuj położenie obiektu matematycznego
            ObstacleY += gp.ObstacleSpeed;
            if (ObstacleY == 800) {
                didCollide(number, equation);
            }
            if (ObstacleY > 1000) {
                if (gp.obstacleCount < 9) {
                    gp.obstacleCount++;
                    mathObjects.remove(this);
                    setObstacleDefaultValues(this);
                    mathObjects.add(new Obstacle(gp));
                    // Dodaj nowy obiekt po przekroczeniu ObstacleY > 100
                } else {
                    gp.obstacleCount++;
                    gp.gameStarted = false;
                }

            }

            // Jeśli obiekt matematyczny wypadł poza ekran, usuń go

        }


    }

    public double getRandomNumber() {
        // Przykładowa metoda generująca losowe równanie matematyczne
        Random random = new Random();
        number = random.nextInt(3) + 2;
        return number;
    }

    public String getRandomMathExpression(double num, String eq) {
        // Przykładowa metoda generująca losowe równanie matematyczne
        return eq + num;
    }

//    public String getRandomEquation() {
//   }

    private String getRandomOperator() {
        // Przykładowa metoda generująca losowy operator matematyczny
        String[] operators = {"+", "-", "*", "/"};
        Random random = new Random();
        return operators[random.nextInt(operators.length)];
    }

    private int getRandomXPosition() {
        // Przykładowa metoda generująca losową pozycję X dla obiektu matematycznego
        Random random = new Random();
        int minX = 320; // Minimalna wartość X
        int maxX = 660; // Maksymalna wartość X
        return Math.min(maxX, Math.max(minX, random.nextInt(maxX - minX + 1) + minX));
    }


    public void drawObstacle(Graphics2D g2) {
        if (gp.gameStarted == true) {
            // Rysuj biały prostokąt jako tło
            int rectangleWidth = 300;  // Przykładowa szerokość prostokąta
            int rectangleHeight = 50; // Przykładowa wysokość prostokąta
            //g2.setColor(Color.WHITE);
            //g2.fillRect(ObstacleX, ObstacleY, rectangleWidth, rectangleHeight);
            BufferedImage image1 = null;
            try {
                image1 = ImageIO.read(getClass().getResourceAsStream("/background/rectangle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
            g2.drawImage(image1, ObstacleX, ObstacleY, rectangleWidth, rectangleHeight, null);
            // Rysuj obiekt matematyczny na panelu
            Font font = new Font("Arial", Font.BOLD, 35); // Przykładowa czcionka (możesz dostosować)
            g2.setFont(font);
            g2.setColor(Color.BLACK); // Ustaw kolor tekstu
            g2.drawString(finalEquation, ObstacleX + 125, ObstacleY + 35);


        }

    }



    public double calculateResult(String eq, double num) {
        switch (eq) {
            case "*":
                return gp.playerPoints *= num;
            case "-":
                return gp.playerPoints -= num;
            case "/":
                return gp.playerPoints /= num;
            case "+":
                return gp.playerPoints += num;
        }
        return gp.playerPoints;
    }
}
