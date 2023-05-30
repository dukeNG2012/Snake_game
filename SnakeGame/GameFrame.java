//import javax.swing.JFileChooser;
//import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
      GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException
      {
        
        this.add(new GamePanel());
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
      }
}
