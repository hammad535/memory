import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WinClass {
    private JFrame winFrame;
    private JPanel winPanel;
    private JLabel congratsLabel;
    private JLabel movesLabel;
    private JLabel timeLabel;
    private JButton nextButton;
    private JButton mainMenuButton;

    public WinClass(int moves, int time,String level) {
        winFrame = new JFrame("Congratulations!");
        winFrame.setSize(300, 200);
        winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        winFrame.setLocationRelativeTo(null); // Center the frame

        winPanel = new JPanel();
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        winPanel.setBackground(Color.BLACK); // Set dark background

        congratsLabel = new JLabel("Congratulations!");
        congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        congratsLabel.setForeground(Color.WHITE); // Set text color
        winPanel.add(Box.createVerticalStrut(30)); // Add space
        winPanel.add(congratsLabel);

        movesLabel = new JLabel("Moves: " + moves);
        movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movesLabel.setForeground(Color.WHITE); // Set text color
        winPanel.add(Box.createVerticalStrut(10)); // Add space
        winPanel.add(movesLabel);

        int minutes = time / 60;
        int seconds = time % 60;
        timeLabel = new JLabel(String.format("Time: %02d:%02d", minutes, seconds));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setForeground(Color.WHITE); // Set text color
        winPanel.add(Box.createVerticalStrut(10)); // Add space
        winPanel.add(timeLabel);

        nextButton = new JButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        winPanel.add(Box.createVerticalStrut(10)); // Add space
        winPanel.add(nextButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        winPanel.add(Box.createVerticalStrut(10)); // Add space
        winPanel.add(mainMenuButton);

        winFrame.add(winPanel);
        winFrame.setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(level.equals("1"))
            	{
            	winFrame.dispose();
            	new SecondLevel();
            	}
            	if(level.equals("2"))
            	{
            	winFrame.dispose();
            	new ThirdLevel();
            	}
            	 
                
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new MemoryGame();
            }
        });
    }
}
