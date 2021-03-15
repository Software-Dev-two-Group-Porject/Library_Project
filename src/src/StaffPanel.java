import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StaffPanel extends JPanel {
    CommonLabel labelTotalRequests, labelReadyForCollection, labelsBooksForDelivery;
    HeaderPanel headerPanel;
    CommonLabel studentName, studentCourse, studentBlock, studentRoomNumber, currentLoanCount;

    Design design = new Design();
    StudentDataPanel studentDataPanel;
    BookDataPanel bookDataPanel;
    CommonButton displayBookPanel, displayStudentPanel;
    JPanel readyStats, studentInfo, buttonContainer;


    StaffPanel(User user){
        this.setLayout(null);
        this.setBackground(design.bgColor);

        headerPanel = new HeaderPanel("Staff",user.getName());
        headerPanel.setBounds(20, 10, 850, 120);
        this.add(headerPanel);

        labelTotalRequests = new CommonLabel("Requests: ", 20);
        labelTotalRequests.setBounds(50, 150, 300, 30);
        this.add(labelTotalRequests);

        labelReadyForCollection = new CommonLabel("For Collection: ", 20);
        labelReadyForCollection.setBounds(50, 180, 330, 30);
        this.add(labelReadyForCollection);

        labelsBooksForDelivery = new CommonLabel("For delivery: ", 20);
        labelsBooksForDelivery.setBounds(50, 210, 300, 30);
        this.add(labelsBooksForDelivery);

        studentName = new CommonLabel("Name:", 15);
        studentName.setBounds(400, 150, 300, 25);
        this.add(studentName);

        studentCourse = new CommonLabel("Course:", 15);
        studentCourse.setBounds(400, 180, 300, 25);
        this.add(studentCourse);

        studentBlock = new CommonLabel("Block:", 15);
        studentBlock.setBounds(400, 210, 150, 25);
        this.add(studentBlock);

        studentRoomNumber = new CommonLabel("Room Num:", 15);
        studentRoomNumber.setBounds(550, 210, 150, 25);
        this.add(studentRoomNumber);

        buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout());
        buttonContainer.setBounds(50, 260, 180, 35);
        buttonContainer.setBackground(design.bgColor);
        this.add(buttonContainer);


        displayBookPanel = new CommonButton("Catalog", design.tableButtonColor, 10);
        displayBookPanel.addActionListener(this::setPanelVisibility);
        buttonContainer.add(displayBookPanel);

        displayStudentPanel = new CommonButton("Students", design.tableButtonColor, 10);
        displayStudentPanel.addActionListener(this::setPanelVisibility);
        displayStudentPanel.setEnabled(false);
        buttonContainer.add(displayStudentPanel);

        studentDataPanel = new StudentDataPanel(this);
        studentDataPanel.setBounds(50, 300, 900, 250);
        this.add(studentDataPanel);
        studentDataPanel.setVisible(true);

        bookDataPanel = new BookDataPanel(null, this);
        bookDataPanel.setBounds(50, 300, 900, 250);
        bookDataPanel.setVisible(false);
        this.add(bookDataPanel);
    }

    public void setStudentViewLabels(Student student){
        studentName.setText("Name: " + student.getName());
        studentCourse.setText("Course: ");
        studentBlock.setText("Block:  " + student.getBlockLetter());
        studentRoomNumber.setText("Room:  " + student.getRoomNumber());
    }

    public void setPanelVisibility(ActionEvent e){
        if(e.getSource() == displayBookPanel){
            bookDataPanel.setVisible(true);
            studentDataPanel.setVisible(false);
            displayBookPanel.setEnabled(false);
            displayStudentPanel.setEnabled(true);
            bookDataPanel.displayAllBookData();
        }
        else if(e.getSource() == displayStudentPanel){
            bookDataPanel.setVisible(false);
            studentDataPanel.setVisible(true);
            displayBookPanel.setEnabled(true);
            displayStudentPanel.setEnabled(false);
        }
    }


}
