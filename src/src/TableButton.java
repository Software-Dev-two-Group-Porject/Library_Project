import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableButton extends JButton {
    Design design = new Design();

    private String id;
    private int yPos;

    TableButton(String id, Color buttonType, String text){
        this.id = id;
        this.setForeground(Color.white);
        this.setText(text);
        this.setBackground(buttonType);
        this.setFont(new Font(design.fontName, Font.BOLD, 10));
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

}