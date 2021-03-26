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
    BookLoanDataPanel bookLoanDataPanel;
    String [] staffStatusChanges = {"Approved", "Delivered", "Collected"};
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
        headerPanel.setBounds(20, 5, 850, 135);
        this.add(headerPanel);

        labelTotalRequests = new CommonLabel("Requests: " + bookLoanList.getTotals("requested"), 15);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 10;
        readyStats.add(labelTotalRequests, gbc);

        viewRequests = new CommonButton("View", design.tableButtonColor, 12);
        viewRequests.addActionListener(l -> populateBookLoanDataPanelByStatus("requested"));
        gbc.gridx =11; gbc.gridy =0; gbc.gridwidth = 1;
        readyStats.add(viewRequests, gbc);

        labelsBooksForDelivery = new CommonLabel("For delivery: " + bookLoanList.getTotals("approved"), 15);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 10;
        readyStats.add(labelsBooksForDelivery, gbc);

        viewApprovals = new CommonButton("View", design.tableButtonColor, 12);
        gbc.gridx =11; gbc.gridy =1; gbc.gridwidth = 1;
        viewApprovals.addActionListener(l -> populateBookLoanDataPanelByStatus("approved"));
        readyStats.add(viewApprovals, gbc);

        labelReadyForCollection = new CommonLabel("For Collection: " + bookLoanList.getTotals("ready"), 15);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 10;
        readyStats.add(labelReadyForCollection, gbc);

        viewReadys = new CommonButton("View", design.tableButtonColor, 12);
        gbc.gridx =11; gbc.gridy =2; gbc.gridwidth = 1;
        viewReadys.addActionListener(l -> populateBookLoanDataPanelByStatus("ready"));
        readyStats.add(viewReadys, gbc);

        readyStats.setBounds(20, 150, 300, 100);
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
        studentInfo.setBounds(450, 150, 400, 120);

        viewLoans = new CommonButton("View Loans", design.tableButtonColor, 12);
        gbc.gridx = 0; gbc.gridy = 4;
        viewLoans.setVisible(false);
        viewLoans.addActionListener(l -> displayBookLoansForStudent());
        studentInfo.add(viewLoans, gbc);
        this.add(studentInfo);
        studentInfo.setVisible(false);


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

        bookLoanDataPanel = new BookLoanDataPanel(bookLoanList.getBookLoanList(), this, null);
        bookLoanDataPanel.setBounds(330,150, 620, 150);
        this.add(bookLoanDataPanel);
    }

    public void setStudentViewLabels(Student student){
        if(bookLoanDataPanel.isVisible()){
            bookLoanDataPanel.setVisible(false);
            studentInfo.setVisible(true);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        BookLoan [] bookLoans = bookLoanList.getBookLoansByUserId(student.getUserID());
        studentName.setText("Name: " + student.getName());
        studentEmail.setText("Email: " + student.getEmail());
        studentCourse.setText("Course: " + student.getCourse());
        studentBlock.setText("Block:  " + student.getBlockLetter());
        studentRoomNumber.setText("Room:  " + student.getRoomNumber());

        if(bookLoans.length > 0){
            viewLoans.setVisible(true);
            bookLoanDataPanel.renderTable(bookLoans);
        }
        //render the books in this section
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

    public void populateBookLoanDataPanelByStatus(String status){
      BookLoan [] statusBookLoans = bookLoanList.getBookLoansByStatus(status);
      for(int i = 0; i < statusBookLoans.length; i++){
          System.out.println(statusBookLoans[i].toString());
      }
      bookLoanDataPanel.renderTable(statusBookLoans);
    }

    public void displayBookLoansForStudent() {
        bookLoanDataPanel.setVisible(true);
        studentInfo.setVisible(false);
    }

    public void saveBookLoanListChanges(){
        bookLoanList.saveData();
    }
}
