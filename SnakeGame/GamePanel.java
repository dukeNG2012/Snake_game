
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
//import java.util.Timer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

//import java.awt.image.*;

import javax.sound.sampled.*;

public class GamePanel extends JPanel  implements ActionListener 
{

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 5;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;

    /*
     * create arr x and y 
     */

    final int[] x = new int[GAME_UNITS]; 
    final int[] y = new int[GAME_UNITS]; 
    int bodyParts = 6; // begin with 6 body parts
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    GamePanel() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //ImageIcon image = new ImageIcon("C:/Users/ASUS/OneDrive/my programming files/Java programming/BroCode/SnakeGame/seng_tran.jpg"); // create image icon
        this.setBackground(Color.BLACK);
        
        
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    public void startGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        
        File file = new File("C:/Users/ASUS/OneDrive/my programming files/Java programming/BroCode/SnakeGame/Weekend_has_come.wav");
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiostream);

        clip.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //ImageIcon image = new ImageIcon("simpleprogram2.jpg"); // create image icon
        
        //frame.setIconImage(image.getImage());
        draw(g);
    }
    public void draw(Graphics g)
    {
        //Image img = new Image("C:/Users/ASUS/OneDrive/my programming files/Java programming/BroCode/SnakeGame/seng_tran.jpg");
        //g.drawImage(img, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
        //Image image = new Image(""); // create image icon
        //g.drawImage(image, appleX, DELAY, getFocusCycleRootAncestor());
        for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; ++i)
        {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
        g.setColor(Color.green);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);


        // snake draw:
        for(int j = 0; j < bodyParts; ++j)
        {
            if(j == 0) // this is the head
            {
                g.setColor(Color.CYAN);
                g.fillRect(x[j], y[j], UNIT_SIZE, UNIT_SIZE);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(x[j], y[j], UNIT_SIZE, UNIT_SIZE);
            }


        }
        
    }
    public void newApple()
    {
        // generate new cordinate apple
        appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }
    public void move()
    {
        // moving the snake;
        for(int i = bodyParts; i > 0; i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        // move method!
        switch(direction)
        {
            case 'U':
                y[0] = y[0]-UNIT_SIZE; // move to the next position!
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;

        }
    }
    public void checkApple()
    {

    }
    public void checkCollisions()
    {
       
        
    }
    public void actionPerformed(ActionEvent e)
    {
        if(running)
        {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        
                        if(direction != 'R')
                        {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(direction != 'L')
                        {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(direction != 'D')
                        {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        //System.out.println("i'm pressing left");
                        if(direction != 'U')
                        {
                            direction = 'D';
                        }
                        break;
                }
        }
        
    }
    
}
