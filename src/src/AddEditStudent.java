import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created By Jonathon on 08/03/2021
 * Update Comments About Program Here
 **/
public class AddEditStudent extends JFrame {

    CommonLabel headerLabel, labelId, labelEmail, labelName, labelCourse, labelBlock, labelRoom;
    JTextField idField;
    CommonTextField nameField, emailField, courseField, blockField, roomField;
    CommonButton btnClear, btnAdd, btnCancel, btnEdit;
    Design design = new Design();
    int action;
    StudentList studentList;
    Student student;

    //the actions here will be an int 0 = Add, 1 = edit. this will manage some of the data that will be displayed.
    AddEditStudent(int action, Student student){
        this.action = action;
        this.student = student;
        headerLabel = new CommonLabel(getHeader(action),25);
        headerLabel.setBorder(BorderFactory.createMatteBorder(0,0, 3, 0, design.borderGold));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBounds(125, 30, 150, 30);

        labelId = new CommonLabel("ID:", 15);
        labelId.setBounds(50, 90, 100, 30);

        idField = setIdTextField(action);
        idField.setBounds(50, 115, 150, 30);

        labelName = new CommonLabel("Name: ", 15);
        labelName.setBounds(50, 165, 100, 30);

        nameField = new CommonTextField();
        nameField.setBounds(50, 190, 250, 30);

        labelEmail = new CommonLabel("Email", 15);
        labelEmail.setBounds(50, 200, 100, 30);

        emailField = new CommonTextField();
        emailField.setBounds(50, 210, 250, 30);

        labelCourse = new CommonLabel("Course:", 15);
        labelCourse.setBounds(50, 240, 100, 30);

        courseField = new CommonTextField();
        courseField.setBounds(50, 270, 250, 30);

        labelBlock = new CommonLabel("Block: ", 15);
        labelBlock.setBounds(50, 310, 100, 30);

        blockField = new CommonTextField();
        blockField.setHorizontalAlignment(JTextField.CENTER);
        blockField.setBounds(50, 345, 100, 30);

        labelRoom = new CommonLabel("Room: ", 15);
        labelRoom.setBounds(180, 310, 100, 30);

        roomField = new CommonTextField();
        roomField.setHorizontalAlignment(JTextField.CENTER);
        roomField.setBounds(180, 345, 100, 30);

        //setting buttons
        if(this.action == 1){
            btnEdit = new CommonButton("Edit", design.btnWarningColor, 13);
            btnEdit.setBounds(50, 410, 100, 40);
            btnEdit.addActionListener(l -> editStudentInfo(student));
            this.add(btnEdit);
        } else {
            btnAdd = new CommonButton("Add", design.btnAddColor, 13);
            btnAdd.setBounds(50, 410, 100, 40);
            btnAdd.addActionListener(l -> addStudent(student));
            this.add(btnAdd);
        }

        btnClear = new CommonButton("Clear", design.tableButtonColor, 13);
        btnClear.setBounds(180, 410, 100, 40);

        this.add(btnClear);

        //adding labels
        this.add(headerLabel); this.add(labelId); this.add(labelName); this.add(labelEmail);
        this.add(labelCourse); this.add(labelBlock); this.add(labelRoom);

        this.add(idField); this.add(nameField); this.add(courseField); this.add(emailField);
        this.add(blockField); this.add(roomField);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false); this.setLayout(null);
        this.setSize(400, 600);
        this.getContentPane().setBackground(design.bgColor);
        this.setVisible(true);
    }

    public String getHeader(int type){
        if(type == 0)
            return "Add Student";
        else
            return "Edit Student";
    }

    public JTextField setIdTextField(int action) {
        JTextField textField = new JTextField();
        if (action == 1){
            textField.setText(String.valueOf(student.getUserID()));
            textField.setEditable(false);
            textField.setForeground(Color.white);
            textField.setBackground(Color.black);
            textField.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        } else {
            textField.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        }

        textField.setFont(new Font(design.fontName, Font.PLAIN, 15));

        return textField;
    }

    public void editStudentInfo(Student student ){

    }

    public void addStudent(Student student){

    }
}
