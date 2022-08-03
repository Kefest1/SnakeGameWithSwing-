import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameMenu extends JFrame implements KeyListener {
    public static final int SCREEN_WIDTH = 1500;
    public static final int SCREEN_HEIGHT = 1000;
    String[] gameDifficultyLevels = {"Easy", "Medium", "Hard", "Ultra"};
    String snakePicturePath = "icons\\22285-snake-icon.png";
    JTextField userName; // TODO override get method
    JLabel greeting;
    JLabel difficultyLevel;
    JLabel enterText;
    JLabel sneakPicture;
    String defaultUsername = "Nameless";
    JButton buttonPlay;
    JButton buttonExit;
    static JComboBox<String> checkDifficultyLevel;

    SnakeGameClass snakeGame;

    private void createGreetingLabel() {
        greeting = new JLabel("Welcome to the snake game!");
        greeting.setHorizontalAlignment(JLabel.CENTER);
        greeting.setVerticalAlignment(JLabel.TOP);
        greeting.setFont(new Font("MV Boli", Font.PLAIN, 60));
        greeting.setIcon(new ImageIcon(snakePicturePath));
        greeting.setBackground(Color.BLACK);
        greeting.setOpaque(true);
        greeting.setBorder(BorderFactory.createLineBorder(Color.blue, 10));
        greeting.setForeground(Color.GREEN);
    }

    private void createButtonPlay() {
        buttonPlay = new JButton("Play");
        buttonPlay.setBounds(200, 500, 180, 70);
        personaliseButtons(buttonPlay);
        buttonPlay.addActionListener(
                e -> {
                    disperseGameMenu();
                    addGame();
                }
        );
    }

    private void personaliseButtons(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setFocusable(false);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.CYAN);
    }

    private void createButtonExit() {
        buttonExit = new JButton("Exit");
        buttonExit.setBounds(200, 600, 180, 70);
        personaliseButtons(buttonExit);
        buttonExit.addActionListener(
                e -> {
                    this.dispose();
                }
        );
    }

    public GameMenu() throws HeadlessException {
        this.addKeyListener(this);
        this.setFocusable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGreetingLabel();

        userName = new JTextField();
        userName.setBounds(200, 820, 118, 35);

        createButtonPlay();
        createButtonExit();

        checkDifficultyLevel = new JComboBox<>(gameDifficultyLevels);
        checkDifficultyLevel.setBounds(200, 740, 120, 35);

        enterText = new JLabel("Enter your username:");
        enterText.setBounds(200, 780, 250, 40);
        enterText.setFont(new Font("MV Boli", Font.PLAIN, 16));

        difficultyLevel = new JLabel("Choose difficulty level:");
        difficultyLevel.setBounds(200, 700, 250, 40);
        difficultyLevel.setFont(new Font("MV Boli", Font.PLAIN, 16));

        sneakPicture = new JLabel();
        sneakPicture.setIcon(new ImageIcon("icons\\427533.png"));
        sneakPicture.setBounds(600, 250, 600, 600);

        greeting.add(userName);
        greeting.add(buttonPlay);
        greeting.add(buttonExit);
        greeting.add(sneakPicture);
        greeting.add(checkDifficultyLevel);
        greeting.add(difficultyLevel);
        greeting.add(enterText);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(greeting);

        this.setVisible(true);
    }

    public void disperseGameMenu() {
        this.remove(greeting);
        this.revalidate();
        this.repaint();
        this.setSize(615, 640);
    }

    public void addGame() {
        snakeGame = new SnakeGameClass();
        this.add(snakeGame);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (snakeGame.direction != 'R') {
                    snakeGame.direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snakeGame.direction != 'L') {
                    snakeGame.direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (snakeGame.direction != 'D') {
                    snakeGame.direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakeGame.direction != 'U') {
                    snakeGame.direction = 'D';
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}