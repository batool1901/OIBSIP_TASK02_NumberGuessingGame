import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGame extends JFrame implements ActionListener {
    private Random random = new Random();
    private int randomNumber;
    private int attempts;
    private int score;
    private int maxAttempts = 5;
    private int round = 1;

    // UI components
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JLabel roundLabel;
    private JLabel attemptsLabel;

    public GuessTheNumberGame() {
        setTitle("Guess the Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Initialize game variables
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        score = 0;

        // Create UI components
        messageLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");
        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        roundLabel = new JLabel("Round: " + round, SwingConstants.CENTER);
        attemptsLabel = new JLabel("Attempts left: " + (maxAttempts - attempts), SwingConstants.CENTER);

        // Add action listener for the guess button
        guessButton.addActionListener(this);

        // Add components to frame
        add(roundLabel);
        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(scoreLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the user's guess
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);

            attempts++;
            attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));

            if (guess < randomNumber) {
                messageLabel.setText("Too low! Try again.");
            } else if (guess > randomNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Correct! You've guessed the number!");
                score += (maxAttempts - attempts + 1) * 10;
                scoreLabel.setText("Score: " + score);
                nextRound();
            }

            if (attempts >= maxAttempts) {
                messageLabel.setText("Out of attempts! The number was " + randomNumber);
                nextRound();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void nextRound() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to play the next round?", "Next Round", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            round++;
            randomNumber = random.nextInt(100) + 1;
            attempts = 0;
            roundLabel.setText("Round: " + round);
            attemptsLabel.setText("Attempts left: " + maxAttempts);
            messageLabel.setText("Guess a number between 1 and 100:");
            guessField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + score);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GuessTheNumberGame();
    }
}
