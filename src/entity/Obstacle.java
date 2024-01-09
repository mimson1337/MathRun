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
    double number =0;
    int ObstacleX;
    int ObstacleY;
    public List<Obstacle> mathObjects;
    String finalEquation;


    //konstruktor
    public Obstacle(GamePanel gp) {
        this.gp = gp;
        mathObjects = new ArrayList<>();
        setObstacleDefaultValues(this);
    }


    /**
     * metoda z ifem, który wywołuję metodę Player.collidesWith, potrzebna do obliczenia rezultatu operacji matematycznej
     * @param num
     * @param eq
     */
    public void didCollide(double num, String eq) {
        // Sprawdź kolizję z graczem
        for (Obstacle mathObject : mathObjects) {
            if (Player.collidesWith(mathObject)) {
                calculateResult(eq, num);// oblicz rezultat posiadając liczbę i operator
            }
        }
    }

    /**
     * ustawienie defaultowych wartosci dla Obstacle
     * @param mathObject
     */
    public void setObstacleDefaultValues(Obstacle mathObject) {
        ObstacleX = getRandomXPosition();
        ObstacleY = 0;

        number = getRandomNumber();
        equation = getRandomOperator();
        finalEquation = getRandomMathExpression(number, equation);
        mathObjects.add(this);
    }

    /**
     * metoda opisująca update obiektu spadającego
     */
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
                    // Dodaj nowy obiekt po przekroczeniu ObstacleY > 1000
                } else {
                    gp.obstacleCount++;
                    gp.gameStarted = false;
                }
            }
        }
    }

    /**
     * metoda generująca losową liczbę
     * @return
     */
    public double getRandomNumber() {
        Random random = new Random();
        number = random.nextInt(8) + 2;
        return number;
    }

    /**
     * metoda spajająca liczbę num oraz string eq, który jest operatorem
     * @param num
     * @param eq
     * @return
     */
    public String getRandomMathExpression(double num, String eq) {
        return eq + num;
    }

    /**
     * metoda losująca dany operator
     * @return
     */
    private String getRandomOperator() {
        // Przykładowa metoda generująca losowy operator matematyczny
        String[] operators = {"+", "-", "*", "/"};
        Random random = new Random();
        return operators[random.nextInt(operators.length)];
    }

    /**
     * Przykładowa metoda generująca losową pozycję X dla obiektu matematycznego w danym zakresie
     * @return
     */
    private int getRandomXPosition() {
        Random random = new Random();
        int minX = 320; // Minimalna wartość X
        int maxX = 660; // Maksymalna wartość X
        return Math.min(maxX, Math.max(minX, random.nextInt(maxX - minX + 1) + minX));
    }

    /**
     * metoda rysująca obstacle za pomocą Graphics2D
     * @param g2
     */
    public void drawObstacle(Graphics2D g2) {
        if (gp.gameStarted) {
            int rectangleWidth = 300;  // Przykładowa szerokość prostokąta
            int rectangleHeight = 50; // Przykładowa wysokość prostokąta
            BufferedImage image1 = null;
            try {
                image1 = ImageIO.read(getClass().getResourceAsStream("/background/rectangle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image1, ObstacleX, ObstacleY, rectangleWidth, rectangleHeight, null);
            // Rysuj obiekt matematyczny na panelu
            Font font = new Font("Arial", Font.BOLD, 35); // Przykładowa czcionka (możesz dostosować)
            g2.setFont(font);
            g2.setColor(Color.BLACK); // Ustaw kolor tekstu
            g2.drawString(finalEquation, ObstacleX + 125, ObstacleY + 35);
        }
    }

    /**
     * metoda nadpisująca wartość gp.playerPoints po odpowiednim działaniu matematycznym
     * @param eq
     * @param num
     * @return
     */
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
