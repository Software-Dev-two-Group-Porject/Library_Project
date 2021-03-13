import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class Design {

    public Color borderColor, bgColor, btnAddColor, btnCancelColor, btnWarningColor,  borderGold, tableButtonColor, dataTableBg;

    public String fontName;
    public ImageIcon img;

    Design(){
        this.borderColor =  new Color(105,105,105);
        this.bgColor = new Color(4, 30, 66);
        this.btnAddColor = new Color(0, 103, 38);
        this.btnWarningColor = new Color(255, 174, 0);
        this.btnCancelColor = new Color(217, 83, 79);
        this.img = new ImageIcon("src\\img\\ulster_logo.png");
        this.fontName = "Arial";
        this.borderGold = new Color(207,181, 59);
        this.tableButtonColor = new Color(0, 92, 151);
        this.dataTableBg = new Color(238,238,238);
    }
}
