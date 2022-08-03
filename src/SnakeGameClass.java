import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGameClass extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    private static final int ULTRA_SPEED = 75;
    private static int DELAY;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean isRunning = false;
    Timer timer;
    Random random;
    JLabel score;
    JLabel gameOver;


    public SnakeGameClass() {
        DELAY = (4 - GameMenu.checkDifficultyLevel.getSelectedIndex()) * ULTRA_SPEED;
        System.out.println(DELAY);

        score = new JLabel("Your score is: " + applesEaten);
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setVerticalAlignment(JLabel.TOP);
        score.setFont(new Font("MV Boli", Font.BOLD, 24));

        this.add(score);

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.startGame();
    }

    public void startGame() {
        newApple();
        isRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        g.setColor(Color.RED);
        g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

        }
    }

    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }

    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
        score.setText("Your score is: " + applesEaten);
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                isRunning = false;
                break;
            }
        }
        if (x[0] < 0 || y[0] < 0 || x[0] > SCREEN_WIDTH || y[0] > SCREEN_WIDTH)
            isRunning = false;

        if (!isRunning) {
            timer.stop();
            gameOver();
        }
    }



    public void gameOver() {
        gameOver = new JLabel();
        gameOver.setText("Gave Over! Your score is " + applesEaten + "!");
        gameOver.setFont(new Font("MV Boli", Font.BOLD, 32));
        gameOver.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        gameOver.setHorizontalAlignment(JLabel.CENTER);

        this.remove(score);
        this.add(gameOver);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (isRunning) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
}
