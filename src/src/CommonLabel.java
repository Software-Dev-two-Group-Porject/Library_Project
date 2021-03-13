import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 08/03/2021
 * Update Comments About Program Here
 **/
public class CommonLabel extends JLabel {
    Design design = new Design();
    CommonLabel(String text, int fontSize){
        this.setText(text);
        this.setFont(new Font(design.fontName, Font.BOLD, fontSize));
        this.setForeground(Color.white);
    }
}
