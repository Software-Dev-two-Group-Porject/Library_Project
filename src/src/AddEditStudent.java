import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created By Jonathon on 08/03/2021
 * Update Comments About Program Here
 **/
public class AddEditStudent extends JFrame {

    CommonLabel headerLabel, labelId, labelEmail, labelStatus, labelPassword, labelPasswordRepeat,
            labelName, labelCourse, labelBlock, labelRoom;
    JTextField idField;
    CommonTextField nameField, emailField, courseField, blockField, roomField;
    JPasswordField passwordField, repeatPasswordField;
    CommonButton btnClear, btnAddEdit, btnCancel;
    Design design = new Design();
    int action, id;
    StudentList studentList;
    Student student;
    StudentDataPanel studentDataPanel;
    JComboBox<String> statusBox;
    User user;

    //the actions here will be an int 0 = Add, 1 = edit. this will manage some of the data that will be displayed.
    AddEditStudent(int action, Student student, StudentDataPanel studentDataPanel, StudentList studentList){
        this.studentDataPanel = studentDataPanel;
        this.studentList = studentList;
        this.action = action;
        this.student = student;
        user = new User();
        headerLabel = new CommonLabel(getHeader(action),25);
        headerLabel.setBorder(BorderFactory.createMatteBorder(0,0, 3, 0, design.borderGold));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBounds(125, 30, 150, 30);

        labelId = new CommonLabel("ID:", 15);
        labelId.setBounds(50, 70, 100, 25);

        idField = setIdTextField(action);
        idField.setBounds(50, 95, 150, 30);

        labelName = new CommonLabel("Name: ", 15);
        labelName.setBounds(50, 135, 100, 25);

        nameField = new CommonTextField();
        nameField.setBounds(50, 160, 250, 30);
        nameField.setText(student.getName());

        labelEmail = new CommonLabel("Email:", 15);
        labelEmail.setBounds(50, 200, 100, 25);

        emailField = new CommonTextField();
        emailField.setBounds(50, 225, 250, 30);
        emailField.setText(student.getEmail());

        labelPassword = new CommonLabel("Password:", 15);
        labelPassword.setBounds(50, 265, 100, 25);


        passwordField = new JPasswordField(student.getPassword());
        passwordField.setColumns(20);
        passwordField.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        passwordField.setBounds(50, 290, 250, 30);

        labelPasswordRepeat = new CommonLabel("Repeat Password:", 15);
        labelPasswordRepeat.setBounds(50, 320, 150, 25);

        repeatPasswordField = new JPasswordField(student.getPassword());
        repeatPasswordField.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        repeatPasswordField.setBounds(50, 345, 250, 30);

        labelStatus = new CommonLabel("Status:", 15);
        labelStatus.setBounds(50, 385, 100, 25);

        String [] options = {"staff", "student"};
        statusBox = new JComboBox<>(options);
        statusBox.setBounds(50, 410, 250, 30);
        statusBox.setSelectedIndex(1);

        labelCourse = new CommonLabel("Course:", 15);
        labelCourse.setBounds(50, 450, 100, 25);

        courseField = new CommonTextField();
        courseField.setBounds(50, 475, 250, 30);
        courseField.setText(student.getCourse());

        labelBlock = new CommonLabel("Block: ", 15);
        labelBlock.setBounds(50, 510, 100, 25);

        blockField = new CommonTextField();
        blockField.setHorizontalAlignment(JTextField.CENTER);
        blockField.setBounds(50, 535, 100, 30);
        blockField.setText(String.valueOf(student.getBlockLetter()).toUpperCase());

        labelRoom = new CommonLabel("Room: ", 15);
        labelRoom.setBounds(180, 510, 100, 25);

        roomField = new CommonTextField();
        roomField.setHorizontalAlignment(JTextField.CENTER);
        roomField.setBounds(180, 535, 100, 30);
        roomField.setText(String.valueOf(student.getRoomNumber()));

        //setting buttons
        if(this.action == 1){
            btnAddEdit = new CommonButton("Edit", design.btnWarningColor, 13);
            btnAddEdit.addActionListener(l -> editStudentInfo(student));
        } else {
            btnAddEdit = new CommonButton("Add", design.btnAddColor, 13);
            btnAddEdit.addActionListener(l -> addStudent(student));
        }

        btnAddEdit.setBounds(50, 575, 100, 40);
        this.add(btnAddEdit);

        btnClear = new CommonButton("Clear", design.tableButtonColor, 13);
        btnClear.setBounds(180, 575, 100, 40);
        this.add(btnClear);


        //adding labels
        this.add(headerLabel); this.add(labelId); this.add(labelName); this.add(labelEmail);
        this.add(labelCourse); this.add(labelBlock); this.add(labelRoom); this.add(labelStatus);
        this.add(labelPassword); this.add(labelPasswordRepeat);

        this.add(idField); this.add(nameField); this.add(courseField); this.add(emailField);
        this.add(statusBox); this.add(repeatPasswordField);
        this.add(blockField); this.add(roomField); this.add(passwordField);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false); this.setLayout(null);
        this.setSize(400, 670);
        this.getContentPane().setBackground(design.bgColor);
        this.setVisible(true);
    }

    public String getHeader(int type){
        if(type == 0)
            return "Add Student";
        else
            return "Edit Student";
    }

    public JTextField setIdTextField(int id) {
        JTextField textField = new JTextField();
            textField.setText(String.valueOf(student.getUserID()));
            textField.setEditable(false);
            textField.setForeground(Color.white);
            textField.setBackground(Color.black);
            textField.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            textField.setFont(new Font(design.fontName, Font.PLAIN, 15));
        return textField;
    }


    public void setStatusFieldsOnChange(ActionEvent ae){

    }

    public void setStatusFields(String status){

    }



    public void editStudentInfo(Student student ){
    }

    public void addStudent(Student student){

        //need to order the userList array.
        studentDataPanel.renderTable(studentList.getStudentList());

    }
}
