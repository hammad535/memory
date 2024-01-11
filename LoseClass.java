import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoseClass {
    private JFrame loseFrame;
    private JPanel losePanel;
    private JLabel gameOverLabel;
    private JButton playAgainButton;
    private JButton mainMenuButton;

    public LoseClass(String level) {
        loseFrame = new JFrame("Game Over");
        loseFrame.setSize(300, 200);
        loseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loseFrame.setLocationRelativeTo(null); // Center the frame

        losePanel = new JPanel();
        losePanel.setLayout(new BoxLayout(losePanel, BoxLayout.Y_AXIS));
        losePanel.setBackground(Color.BLACK); // Set dark background

        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setForeground(Color.WHITE); // Set text color
        losePanel.add(Box.createVerticalStrut(30)); // Add space
        losePanel.add(gameOverLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        losePanel.add(Box.createVerticalStrut(10)); // Add space
        losePanel.add(playAgainButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        losePanel.add(Box.createVerticalStrut(10)); // Add space
        losePanel.add(mainMenuButton);

        loseFrame.add(losePanel);
        loseFrame.setVisible(true);

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loseFrame.dispose();
                if(level.equals("1"))
                {
                new FirstLevel(); // Start the game again
                }
                if(level.equals("2"))
                {
                new SecondLevel(); // Start the game again
                }
                if(level.equals("3"))
                {
                new ThirdLevel(); // Start the game again
                }
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loseFrame.dispose();
                new MemoryGame(); // Go back to the main menu
            }
        });
    }
}
