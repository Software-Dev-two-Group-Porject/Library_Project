import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created By Jonathon on 18/03/2021
 * Update Comments About Program Here
 **/
public class BookLoanDataPanel extends JPanel {
    BookLoan[] bookLoans;
    CommonLabel headerLabel;
    TableButton updateButton;
    JPanel dataPanel;
    Design design = new Design();
    Catalog catalog;
    GridBagConstraints gbc = new GridBagConstraints();
    StudentList studentList;
    StaffPanel staffPanel;
    StudentPanel studentPanel;
    BookLoanList bookLoanList;

    public CommonButton saveChanges;

    BookLoanDataPanel(BookLoan[] bookLoans, StaffPanel staffPanel, StudentPanel studentPanel) {
        this.staffPanel = staffPanel;
        this.studentPanel = studentPanel;
        this.studentList = new StudentList();
        this.setBackground(design.bgColor);

        bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();

        studentList.populateList();
        this.bookLoans = bookLoans;
        this.setLayout(new BorderLayout());
        this.catalog = new Catalog();
        catalog.initializeCatalogue();
        headerLabel = new CommonLabel(setTableHeader(), 10);
        headerLabel.setBorder(BorderFactory.createLineBorder(design.borderColor, 1));

        this.add(headerLabel, BorderLayout.NORTH);
        dataPanel = new JPanel();
        dataPanel.setBackground(design.dataTableBg);
        dataPanel.setLayout(new GridBagLayout());
        JScrollPane jsp = new JScrollPane(dataPanel);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(jsp, BorderLayout.CENTER);

        saveChanges = new CommonButton("Save", design.btnAddColor, 12);
        this.add(saveChanges, BorderLayout.SOUTH);
        saveChanges.addActionListener(l -> saveBookLoanData(staffPanel, studentPanel));
        saveChanges.setVisible(false);
    }


    public void renderTable(BookLoan[] bookLoans) {
        JLabel studentName, bookTitle, currentStatus, dateIssued, dueDate, overDue, fineAmount;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataPanel.removeAll();
        dataPanel.revalidate();
        dataPanel.repaint();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 1, 1, 5);
        if (bookLoans.length > 0) {
            for (int i = 0; i < bookLoans.length; i++) {
                studentName = new JLabel((studentList.findById(bookLoans[i].getUserID()).getName()));
                gbc.gridy = i;
                gbc.gridx = 0;
                gbc.gridwidth = 5;
                dataPanel.add(studentName, gbc);

                gbc.gridy = i;
                gbc.gridx = 5;
                gbc.gridwidth = 5;
                bookTitle = new JLabel(catalog.getBookByIsbn(bookLoans[i].getISBN()).getTitle());
                dataPanel.add(bookTitle, gbc);

                gbc.gridy = i;
                gbc.gridx = 10;
                gbc.gridwidth = 3;
                currentStatus = new JLabel(bookLoans[i].getStatus());
                currentStatus.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, design.borderGold));
                dataPanel.add(currentStatus, gbc);

                gbc.gridy = i;
                gbc.gridx = 13;
                gbc.gridwidth = 2;
                dateIssued = new JLabel((bookLoans[i].getDateIssued() != null ? sdf.format(bookLoans[i].getDateIssued()) : "N/A"));
                dataPanel.add(dateIssued, gbc);

                gbc.gridy = i;
                gbc.gridx = 15;
                gbc.gridwidth = 2;
                dueDate = new JLabel((bookLoans[i].getDueDate() != null ? sdf.format(bookLoans[i].getDueDate()) : "N/A"));
                dataPanel.add(dueDate, gbc);

                gbc.gridy = i;
                gbc.gridx = 17;
                gbc.gridwidth = 2;
                overDue = new JLabel(String.valueOf(bookLoans[i].getDaysOverDue()));
                dataPanel.add(overDue, gbc);

                gbc.gridy = i;
                gbc.gridx = 19;
                gbc.gridwidth = 2;
                fineAmount = new JLabel((bookLoans[i].getDaysOverDue() > 0) ? "£" + String.valueOf(bookLoans[i].getFineAmount()) : "N/A");
                dataPanel.add(fineAmount, gbc);

                updateButton = new TableButton(String.valueOf(bookLoans[i].getUserID()) + " " + bookLoans[i].getISBN(),
                        design.tableButtonColor, setButtonText(bookLoans[i].getStatus(), staffPanel, studentPanel));
                String updatedText = updateButton.getText();
                updateButton.addActionListener(l -> updateStatus(updateButton.getId(), updatedText, bookLoans));
                updateButton.setEnabled(setUpdateButtonEnabled(bookLoans[i].getStatus(), staffPanel, studentPanel));
                gbc.gridy = i;
                gbc.gridx = 21;
                gbc.gridwidth = 1;
                gbc.insets.right = 0;
                dataPanel.add(updateButton, gbc);
            }
        }
    }

    public String setTableHeader() {
        return "<html><tr>" +
                "<td style='width:100px;'>Name</td>" +
                "<td style='width:100px;'>Title<td>" +
                "<td style='width:50px;'>Status</td>" +
                "<td style='width:30px;'>Date Iss</td>" +
                "<td style='width:30px'>Date Due</td>" +
                "<td style='width:20px;'>Overdue</td>" +
                "<td style='width:30px;'>Fine Amount</td>" +
                "</tr></html>";
    }

    public String setButtonText(String status, StaffPanel staffPanel, StudentPanel studentPanel) {
        if (status.trim().toLowerCase().equals("requested") && staffPanel != null) {
            return "approved";
        }
        if (status.trim().toLowerCase().equals("approved") && staffPanel != null) {
            return "delivered";
        }
        if (status.trim().toLowerCase().equals("delivered") && studentPanel != null) {
            return "ready";
        }
        if (status.trim().toLowerCase().equals("ready") && staffPanel != null) {
            return "collected";
        } else {
            return status;
        }
    }

    public boolean setUpdateButtonEnabled(String status, StaffPanel staffPanel, StudentPanel studentPanel) {
        if(status.trim().toLowerCase().equals("requested") && studentPanel != null){
            return false;
        }
        if (status.trim().toLowerCase().equals("approved") && studentPanel != null) {
            return false;
        }
        if(status.trim().toLowerCase().equals("ready") && studentPanel != null){
            return false;
        }
        if (status.trim().toLowerCase().equals("delivered")  && staffPanel != null) {
            return false;
        } else {
            return true;
        }
    }

    public void updateStatus(String findBookLoan, String newStats, BookLoan [] currentLoans) {
        String data[] = findBookLoan.split(" ");
        bookLoanList.updateStatus(data[1], Integer.parseInt(data[0]), newStats);
        saveChanges.setVisible(true);
        for(int i = 0; i < currentLoans.length; i++){
            if(currentLoans[i].getISBN().equals(data[0])){
                currentLoans[i].setStatus(newStats);
            }
        }
        String operation = "";
        if(newStats.equals("approved")){
            operation = "checkout";
            catalog.upDateQuantity(data[1], operation);
        }else if(newStats.equals("collected")){
            operation = "return";
            catalog.upDateQuantity(data[1], operation);
        }

        System.out.println(bookLoanList.getBookloanByIsbnAndUserId(data[1], Integer.parseInt(data[0].toString())).toString());
        bookLoans = currentLoans;
        renderTable(bookLoans);
    }

    public void saveBookLoanData(StaffPanel staffPanel, StudentPanel studentPanel){
        bookLoanList.saveData();
        catalog.saveData();
    }
}
