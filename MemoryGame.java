import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoryGame {
    private JFrame mainFrame;
    private JPanel menuPanel;
    private JPanel introductionPanel;

    public MemoryGame() {
        mainFrame = new JFrame("Memory - DarkIT Theme");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // Center the frame on the screen

        menuPanel = createMenuPanel();
        introductionPanel = createIntroductionPanel();
        
        mainFrame.add(menuPanel);
        mainFrame.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBackground(Color.DARK_GRAY); // Set background color to dark gray

        // Customizing button appearance
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonTextColor = Color.WHITE;
        Color buttonBackgroundColor = Color.BLACK;
        Dimension buttonSize = new Dimension(120, 30); // Set button size

        JButton startButton = createButton("Start", buttonFont, buttonTextColor, buttonBackgroundColor, buttonSize);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start button clicked");
                mainFrame.dispose(); // Close the main menu screen
                EventQueue.invokeLater(() -> {
                    new FirstLevel(); // Launch the FirstLevel game
                });
            }
        });

        JButton introductionButton = createButton("Introduction", buttonFont, buttonTextColor, buttonBackgroundColor, buttonSize);
        introductionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Introduction button clicked");
                mainFrame.remove(menuPanel);
                mainFrame.add(introductionPanel);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        JButton closeButton = createButton("Close", buttonFont, buttonTextColor, buttonBackgroundColor, buttonSize);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Close button clicked");
                System.exit(0);
            }
        });

        // Adding space between buttons
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space above Start button
        panel.add(startButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between Start and Introduction buttons
        panel.add(introductionButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between Introduction and Close buttons
        panel.add(closeButton);

        return panel;
    }

    private JPanel createIntroductionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        JTextArea introText = new JTextArea(
            "Welcome to Memory - DarkIT Theme!\n\n" +
            "Summary:\n" +
            "Memory is a game where you need to match pairs of cards.\n" +
            "You will have different levels with increasing difficulty.\n" +
            "Complete the levels before the timer runs out!\n\n" +
            "Enjoy the DarkIT-themed challenge!"
        );
        introText.setFont(new Font("Arial", Font.PLAIN, 16));
        introText.setForeground(Color.WHITE);
        introText.setBackground(Color.DARK_GRAY);
        introText.setWrapStyleWord(true);
        introText.setLineWrap(true);
        introText.setEditable(false);
        introText.setFocusable(false);

        panel.add(introText, BorderLayout.CENTER);

        JButton backButton = createButton("Back to Menu", new Font("Arial", Font.BOLD, 12), Color.WHITE, Color.BLACK, new Dimension(100, 25));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to Menu button clicked");
                mainFrame.remove(introductionPanel);
                mainFrame.add(menuPanel);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    // Function to create buttons with custom styling
    private JButton createButton(String text, Font font, Color textColor, Color backgroundColor, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(textColor);
        button.setBackground(backgroundColor);
        button.setPreferredSize(size);
        button.setFocusPainted(false); // Remove button border
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MemoryGame();
            }
        });
    }
}
