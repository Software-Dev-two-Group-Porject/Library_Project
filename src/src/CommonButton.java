import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 08/03/2021
 * Update Comments About Program Here
 **/
public class CommonButton extends JButton {

    Design design = new Design();

    CommonButton(String text, Color bgColor, int fontSize){
        this.setText(text);
        this.setForeground(Color.white);
        this.setBackground(bgColor);
        this.setFont(new Font(design.fontName, Font.BOLD, fontSize));
    }

}
