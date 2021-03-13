import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 02/03/2021
 * Update Comments About Program Here
 **/
public class Display extends JFrame {
    private LoginPanel loginPanel;

    Display(){
        loginPanel = new LoginPanel(this);
        this.add(loginPanel);
        this.getContentPane().setBackground(new Color(4, 30, 66));
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void setLoginPanelVisibility(boolean val){
        this.loginPanel.setVisible(val);
    }
}
