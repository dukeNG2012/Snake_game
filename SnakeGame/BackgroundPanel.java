import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

public class BackgroundPanel extends Panel 
{
    Image img;
    public BackgroundPanel()
    {
        img = Toolkit.getDefaultToolkit().createImage("C:/Users/ASUS/OneDrive/my programming files/Java programming/BroCode/SnakeGame/seng_tran.jpg");
        
    }
}
