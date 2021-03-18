import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StaffPanel extends JPanel {
    CommonLabel labelTotalRequests, labelReadyForCollection, labelsBooksForDelivery;
    HeaderPanel headerPanel;
    CommonLabel studentName, studentCourse, studentBlock, studentRoomNumber,
                studentEmail, studentCourses;
    CommonButton viewRequests, viewApprovals, viewReadys;
    CommonButton viewLoans;
    Design design = new Design();
    StudentDataPanel studentDataPanel;
    BookDataPanel bookDataPanel;
    CommonButton displayBookPanel, displayStudentPanel;
    JPanel readyStats, studentInfo, buttonContainer;
    BookLoanList bookLoanList;
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel bookLoanHolder;
    StaffPanel(User user){
        this.setLayout(null);
        this.setBackground(design.bgColor);
        bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();

        readyStats = new JPanel();
        readyStats.setLayout(new GridBagLayout());
        readyStats.setBackground(design.bgColor);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 20);

        headerPanel = new HeaderPanel("Staff",user.getName());
        headerPanel.setBounds(20, 10, 850, 120);
        this.add(headerPanel);

        labelTotalRequests = new CommonLabel("Requests: " + bookLoanList.getTotalRequests(), 20);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 10;
        readyStats.add(labelTotalRequests, gbc);

        viewRequests = new CommonButton("View", design.tableButtonColor, 12);
        gbc.gridx =11; gbc.gridy =0; gbc.gridwidth = 1;
        readyStats.add(viewRequests, gbc);

        labelsBooksForDelivery = new CommonLabel("For delivery: " + bookLoanList.getTotalApproved(), 20);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 10;
        readyStats.add(labelsBooksForDelivery, gbc);

        viewApprovals = new CommonButton("View", design.tableButtonColor, 12);
        gbc.gridx =11; gbc.gridy =1; gbc.gridwidth = 1;
        readyStats.add(viewApprovals, gbc);

        labelReadyForCollection = new CommonLabel("For Collection: " + bookLoanList.getTotalReady(), 20);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 10;
        readyStats.add(labelReadyForCollection, gbc);

        viewReadys = new CommonButton("View", design.tableButtonColor, 12);
        gbc.gridx =11; gbc.gridy =2; gbc.gridwidth = 1;
        readyStats.add(viewReadys, gbc);

        readyStats.setBounds(70, 130, 350, 120);
        this.add(readyStats);

        studentInfo = new JPanel();
        studentInfo.setBackground(design.bgColor);
        studentInfo.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,3,0);

        studentName = new CommonLabel("", 17);
        gbc.gridx = 0; gbc.gridy = 0;
        studentInfo.add(studentName, gbc);

        studentEmail = new CommonLabel("", 17);
        gbc.gridx = 0; gbc.gridy = 1;
        studentInfo.add(studentEmail, gbc);

        studentCourse = new CommonLabel("", 17);
        gbc.gridx =0; gbc.gridy = 2;
        studentInfo.add(studentCourse, gbc);

        studentBlock = new CommonLabel("", 17);
        gbc.gridx = 0; gbc.gridy = 3;
        studentInfo.add(studentBlock, gbc);

        studentRoomNumber = new CommonLabel("", 17);
        gbc.gridx = 1; gbc.gridy = 3;
        studentInfo.add(studentRoomNumber, gbc);
        studentInfo.setBounds(450, 130, 400, 120);

        viewLoans = new CommonButton("View Loans", design.tableButtonColor, 12);
        gbc.gridx = 0; gbc.gridy = 4;
        viewLoans.setVisible(false);
        studentInfo.add(viewLoans, gbc);

        this.add(studentInfo);

        bookLoanHolder = new JPanel();
        JScrollPane jsp = new JScrollPane(bookLoanHolder);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jsp.setBounds(450, 130, 400, 120);
        this.add(jsp);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        BookLoan [] bookLoans = bookLoanList.getBookLoansByUserId(student.getUserID());
        studentName.setText("Name: " + student.getName());
        studentEmail.setText("Email: " + student.getEmail());
        studentCourse.setText("Course: " + student.getCourse());
        studentBlock.setText("Block:  " + student.getBlockLetter());
        studentRoomNumber.setText("Room:  " + student.getRoomNumber());
        viewLoans.setVisible(true);
        if(bookLoans.length > 0){
            CommonLabel bookLoanLabel = new CommonLabel("Current Loans", 12);
            CommonLabel bookTitle = new CommonLabel("", 12);
            CommonLabel status = new CommonLabel("", 12);
            CommonLabel dateReleased = new CommonLabel("", 12);
            CommonLabel dateDue = new CommonLabel("", 12);
            for(int i = 0; i < bookLoans.length; i++){
                bookTitle.setText(bookDataPanel.getBookCatalog().getTitleByIsbn(bookLoans[i].getISBN()));
                status.setText(bookLoans[i].getStatus());
                dateReleased.setText(sdf.format(bookLoans[i].getDateIssued()));
                dateReleased.setText(sdf.format(bookLoans[i].getDueDate()));
            }
        }

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
