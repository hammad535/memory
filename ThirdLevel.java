import javax.swing.*;
import javax.swing.Timer;
import javax.sound.sampled.*;
 
import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class ThirdLevel implements ActionListener {
    JFrame frame = new JFrame("Memory Game");
    JPanel field = new JPanel();
    JPanel start_screen = new JPanel();
    JButton btn[] = new JButton[20];
    JButton goBack = new JButton("Main Menu");

    Random randomGenerator = new Random();

    Boolean game_over = false;
    int level = 8; // Set number of levels to 4
    int score = 0;

    String[] board;
    String[] symbols = {"!=", "@", "#", "$", "%", "&", "*", "/n", "~", "?"};
    private Clip backgroundMusic;
    private Clip winningMusic;
    private Clip losingMusic;
    boolean gameModeEasy = true;

    private int temp = 60;
    private int temp2 = 60;
    private boolean purgatory = false;
    private boolean shown = true;

    private JLabel timerLabel;
    private JTextField movesTextField;
    private Timer gameTimer; // Define gameTimer here

    private int moveCount;

    public ThirdLevel() {
        frame.setSize(500, 300);
        frame.setLocation(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start_screen.setLayout(new BorderLayout());
        frame.add(start_screen, BorderLayout.CENTER);
        frame.setVisible(true);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/music.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(getClass().getResource("/winning.wav"));
            winningMusic= AudioSystem.getClip();
            winningMusic.open(audioInputStream2);
            AudioInputStream audioInputStream3 = AudioSystem.getAudioInputStream(getClass().getResource("/losing.wav"));
            losingMusic= AudioSystem.getClip();
            losingMusic.open(audioInputStream3);
            
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        goToMainScreen();
    }
    public void playBackgroundMusic(int durationInSeconds) {
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            Timer timer = new Timer(durationInSeconds * 1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stopBackgroundMusic();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public void playWinningMusic(int durationInSeconds) {
        if (winningMusic != null) {
        	winningMusic.loop(Clip.LOOP_CONTINUOUSLY);
            Timer timer = new Timer(durationInSeconds * 1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	stopWinningMusic();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public void playlosingMusic(int durationInSeconds) {
        if (losingMusic != null) {
        	losingMusic.loop(Clip.LOOP_CONTINUOUSLY);
            Timer timer = new Timer(durationInSeconds * 1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	stoplosingMusic();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
    public void stopWinningMusic() {
        if (winningMusic != null && winningMusic.isRunning()) {
        	winningMusic.stop();
        }
    }
    public void stoplosingMusic() {
        if (losingMusic != null && losingMusic.isRunning()) {
        	losingMusic.stop();
        }
    }
    
    private void showGameOver() {
    	 stopBackgroundMusic();
    	 playlosingMusic(3);
        JOptionPane.showMessageDialog(frame, "You've Lost! Time's up.");
        frame.dispose();
        new LoseClass("3");
    }
    public void setUpGame(int x, boolean what) {
        level = x;
        clearMain();

        board = new String[2 * x];
        for (int i = 0; i < (x * 2); i++) {
            btn[i] = new JButton("");
            btn[i].setBackground(new Color(220, 220, 220));
            btn[i].addActionListener(this);
            btn[i].setEnabled(true);
            field.add(btn[i]);
        }

        for (int i = 0; i < x; i++) {
            for (int z = 0; z < 2; z++) {
                while (true) {
                    int y = randomGenerator.nextInt(x * 2);
                    if (board[y] == null) {
                        btn[y].setText(symbols[i]);
                        board[y] = symbols[i];
                        break;
                    }
                }
            }
        }
        createBoard();

        // Initialize gameTimer
        gameTimer = new Timer(1000, new ActionListener() {
            private int seconds = 60;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if (seconds >= 0) {
                    String time = String.format("Timer: 00:%02d", seconds);
                    timerLabel.setText(time);
                } else {
                    gameTimer.stop();
                    showGameOver();
                }
            }
        });
    }

    public void hideField(int x) {
        for (int i = 0; i < (x * 2); i++) {
            btn[i].setText("");
        }
        shown = false;
    }

    public void switchSpot(int i) {
        if (board[i] != "done") {
            if (btn[i].getText().equals("")) {
                btn[i].setText(board[i]);
            } else {
                btn[i].setText("");
            }
        }
    }

    public void showField(int x, String a[]) {
        for (int i = 0; i < (x * 2); i++) {
            btn[i].setText(a[i]);
        }
        shown = true;
    }

    public void waitABit() {
        try {
            Thread.sleep(5);
        } catch (Exception e) {
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < (level * 2); i++) {
            if (board[i] != "done") return false;
        }
        return true;
    }

    public void winner() {
    	 stopBackgroundMusic();
    	 playWinningMusic(3);
        String timerLabelText = timerLabel.getText();
        System.out.println("Timer label text: " + timerLabelText);

        // Split the timer label text using the space character and get the last part (time)
        String[] parts = timerLabelText.split(" ");
        if (parts.length >= 2) {
            String timeString = parts[parts.length - 1];
            //System.out.println("Time string: " + timeString);

            String[] timeParts = timeString.split(":");
            if (timeParts.length >= 2) {
                int minutes = Integer.parseInt(timeParts[0].trim());
                int seconds = Integer.parseInt(timeParts[1].trim());

                int secondsRemaining = minutes * 60 + seconds;
                //System.out.println("Seconds remaining: " + secondsRemaining);

                int timeTaken = 60 - secondsRemaining;
                //System.out.println("Time taken: " + timeTaken);
                gameTimer.stop();
                JOptionPane.showMessageDialog(frame, "Congratulations! You've cleared all the levels !");
                frame.dispose();
                

               
            } else {
                System.out.println("Unexpected format for time string.");
            }
        } else {
            System.out.println("Unexpected format for timer label text.");
        }
    }


 
   

    

    public void goToMainScreen() {
        clearMain();
        setUpGame(level, gameModeEasy);
    }

    public void createBoard() {
        field.setLayout(new BorderLayout());
        start_screen.add(field, BorderLayout.CENTER);
        field.setLayout(new GridLayout(5, 4, 2, 2));
        field.setBackground(Color.black);
        field.requestFocus();

        JPanel topPanel = new JPanel(new BorderLayout());

        // Add Timer Label and Moves Text Field
        timerLabel = new JLabel("Timer: 00:60");
        timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topPanel.add(timerLabel, BorderLayout.WEST);

        movesTextField = new JTextField("Moves: 0");
        movesTextField.setEditable(false);
        movesTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(movesTextField, BorderLayout.EAST);

        start_screen.add(topPanel, BorderLayout.NORTH);
    }


    public void clearMain() {
        start_screen.removeAll();
        start_screen.revalidate();
        start_screen.repaint();
    }

    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        playBackgroundMusic(60);

        if (purgatory) {
            switchSpot(temp2);
            switchSpot(temp);
            score++;
            temp = (level * 2);
            temp2 = 30;
            purgatory = false;
        }

        if (source == goBack) {
            goToMainScreen();
        }

        int matchedPairs = 0;

        for (int i = 0; i < (level * 2); i++) {
            if (source == btn[i]) {
                if (shown) {
                    hideField(level);
                    if (!gameTimer.isRunning()) {
                        gameTimer.start();
                    }
                } else {
                    switchSpot(i);
                    if (temp >= (level * 2)) {
                        temp = i;
                    } else {
                        if ((board[temp] != board[i]) || (temp == i)) {
                            temp2 = i;
                            purgatory = true;
                            moveCount++;
                            movesTextField.setText("Moves: " + moveCount);
                            
                        } else {
                            board[i] = "done";
                            board[temp] = "done";
                            temp = (level * 2);
                            moveCount++;
                            movesTextField.setText("Moves: " + moveCount);
                        }
                    }
                }
            }

            if (board[i].equals("done")) {
                matchedPairs++;
            }
        }
        
        if (matchedPairs == level*2||matchedPairs == (level*2)-1) {
        //System.out.println("you have won the game bro ");
        	
           winner();
        }
        
           
        
        
    }

    public static void main(String[] args) {
        new ThirdLevel();
    }
}
