import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 02/03/2021
 * Update Comments About Program Here
 **/
public class LoginPanel extends JPanel {
    private JLabel emailLabel, passwordLabel, loginLabel, logoLabel, headerLabel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    JPanel loginContainer;
    JButton loginBtn;
    Display display;
    Design design = new Design();
    Font labelFont = new Font(design.fontName, Font.BOLD, 15);

    User user;
    User [] userList;


    LoginPanel(Display parentFrame){
        this.user = new User();
        this.user.initUserList();

        this.display = parentFrame;
        this.setBackground(design.bgColor);

        logoLabel = new JLabel();
        logoLabel.setIcon(design.img);
        logoLabel.setBounds(30, 20, 181, 100);

        headerLabel = new JLabel("COM809 Halls Book Order Service");
        headerLabel.setFont(new Font(design.fontName, Font.BOLD, 35));
        headerLabel.setBounds(220, 40, 580, 50);
        headerLabel.setForeground(Color.white);


        loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.white);
        loginLabel.setFont(new Font(design.fontName, Font.BOLD, 30));
        loginLabel.setBounds(150, 10, 100, 65);

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.white);
        emailLabel.setBounds(40, 85, 100, 20);
        emailLabel.setFont(labelFont);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(40, 160, 100, 20);

        emailTextField = new JTextField();
        emailTextField.setBounds(40,110, 290, 30);
        emailTextField.setBorder(BorderFactory.createLineBorder(new Color(105,105,105), 2));
        emailTextField.setFont(labelFont);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 180, 290, 30);
        emailTextField.setBorder(BorderFactory.createLineBorder(new Color(105,105,105), 2));
        emailTextField.setFont(labelFont);

        loginBtn = new JButton("Login");
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(design.btnAddColor);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 20));
        loginBtn.setBounds(40, 230, 100, 40);
        loginBtn.addActionListener(l -> processLogin());

        loginContainer = new JPanel();
        loginContainer.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        loginContainer.setBackground(design.bgColor);
        loginContainer.setBounds(250, 170, 400, 300);

        loginContainer.setLayout(null);

        loginContainer.add(loginLabel); loginContainer.add(emailLabel);
        loginContainer.add(emailTextField); loginContainer.add(passwordLabel);
        loginContainer.add(passwordField); loginContainer.add(loginBtn);


        this.setLayout(null); this.add(headerLabel);
        this.add(logoLabel); this.add(loginContainer);

    }

    private void processLogin(){
        String email;
        email = emailTextField.getText();
        //firstly we check for valid email with Utility classes.
        User userSignIn = user.getUserByEmail(email);
        if(userSignIn.getEmail() != null){
            if(userSignIn.getPassword().equals(new String(passwordField.getPassword()))){
                display.setLoginPanelVisibility(false);
                if(userSignIn.getStatus().equals("staff")){
                    display.add(new StaffPanel(userSignIn));
                } else {
                    display.add(new StudentPanel(new Student()));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Password");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter correct email");
        }


    }
}
