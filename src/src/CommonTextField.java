import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 08/03/2021
 * Update Comments About Program Here
 **/
public class CommonTextField extends JTextField {
    Design design = new Design();
    CommonTextField(){
        this.setFont(new Font(design.fontName, Font.PLAIN, 15));
        this.setBorder(BorderFactory.createLineBorder(design.borderColor));
    }
}
