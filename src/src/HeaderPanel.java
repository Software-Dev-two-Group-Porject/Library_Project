import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class HeaderPanel extends JPanel {
    JLabel nameLabel, dateLabel, headerLabel, logoLabel;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    Design design = new Design();

    HeaderPanel(String type, String name){
        this.setLayout(null); this.setBackground(design.bgColor);
        this.setBorder(BorderFactory.createMatteBorder(0,0,5,0, design.borderColor));
        logoLabel = new JLabel();
        logoLabel.setIcon(design.img);
        logoLabel.setBorder(BorderFactory.createMatteBorder(1,1,0,1, design.borderGold));
        logoLabel.setBounds(30, 0, 272, 130);


        headerLabel = new JLabel(type + " Portal");
        headerLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, design.borderGold));
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font(design.fontName, Font.BOLD, 30));
        headerLabel.setBounds(350, 10, 250, 30);

        nameLabel = new JLabel("Name: " + name);
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font(design.fontName, Font.BOLD, 25));
        nameLabel.setBounds(320, 45, 400, 30);

        dateLabel = new JLabel("Date: " +formatter.format(date));
        dateLabel.setForeground(Color.white);
        dateLabel.setFont(new Font(design.fontName, Font.BOLD, 20));
        dateLabel.setBounds(320, 80, 200, 30);

        this.add(nameLabel); this.add(headerLabel); this.add(dateLabel); this.add(logoLabel);
    }
}

