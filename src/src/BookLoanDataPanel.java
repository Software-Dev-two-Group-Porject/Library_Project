import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created By Jonathon on 18/03/2021
 * Update Comments About Program Here
 **/
public class BookLoanDataPanel extends JPanel {
    BookLoan [] bookLoans;
    CommonLabel headerLabel;
    JLabel studentName, bookTitle, orderStatus, currentStatus, dateIssued, dueDate, overDue, fineAmount;
    JComboBox<String> statusBox;
    TableButton updateButton;
    CommonButton saveChanges;
    JPanel dataPanel;
    Design design = new Design();
    Catalog catalog;
    GridBagConstraints gbc = new GridBagConstraints();
    StudentList studentList;
    String [] statusChanges;

    BookLoanDataPanel(BookLoan [] bookLoans, String [] statusChanges){
        this.statusChanges = statusChanges;
        this.studentList = new StudentList();
        this.setBackground(design.bgColor);
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
        this.add(dataPanel, BorderLayout.CENTER);
        dataPanel.setLayout(new GridBagLayout());

        saveChanges = new CommonButton("Save", design.btnAddColor, 12);
        saveChanges.setVisible(false);
        this.add(saveChanges, BorderLayout.SOUTH);
    }


    public void renderTable(BookLoan [] bookLoans){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.removeAll();
        this.repaint();
        this.validate();
        for(int i = 0; i < bookLoans.length; i++){
            gbc.gridy = i; gbc.gridx = 0; gbc.gridwidth = 4;
            studentName = new JLabel((studentList.findById(bookLoans[i].getUserID()).getName()));
            dataPanel.add(studentName, gbc);

            gbc.gridy = i; gbc.gridx = 4; gbc.gridwidth = 4;
            bookTitle = new JLabel(catalog.getBookByIsbn(bookLoans[i].getISBN()).getTitle());
            dataPanel.add(bookTitle, gbc);

            gbc.gridy = i; gbc.gridx = 8; gbc.gridwidth = 4;
            currentStatus = new JLabel(bookLoans[i].getStatus());
            dataPanel.add(currentStatus, gbc);

            gbc.gridy = i; gbc.gridx = 12; gbc.gridwidth = 4;
            statusBox = new JComboBox<String>(statusChanges);
            dataPanel.add(statusBox, gbc);

            gbc.gridy = i; gbc.gridx = 16; gbc.gridwidth = 2;
            dateIssued = new JLabel((bookLoans[i].getDateIssued() != null ? sdf.format(bookLoans[i].getDateIssued()) : "N/A"));
            dataPanel.add(dateIssued, gbc);

            gbc.gridy = i; gbc.gridx = 18; gbc.gridwidth = 2;
            dueDate = new JLabel((bookLoans[i].getDueDate() != null ? sdf.format(bookLoans[i].getDueDate()) : "N/A"));
            dataPanel.add(dueDate, gbc);

            gbc.gridy = i; gbc.gridx = 12; gbc.gridwidth = 2;
            overDue = new JLabel((bookLoans[i].getDaysOverDue() > 0) ? String.valueOf(bookLoans[i].getDaysOverDue()) : "N/A");
            dataPanel.add(overDue, gbc);

            gbc.gridy = i; gbc.gridx = 14; gbc.gridwidth = 3;
            fineAmount = new JLabel((bookLoans[i].getDaysOverDue() > 0) ? String.valueOf(bookLoans[i].getFineAmount()): "N/A");
            dataPanel.add(fineAmount, gbc);

            gbc.gridy = i; gbc.gridx = 17; gbc.gridwidth = 3;
            updateButton = new TableButton(String.valueOf(bookLoans[i].getUserID()) + " " + bookLoans[i].getISBN(), design.btnAddColor, "Update");
            dataPanel.add(updateButton, gbc);
        }
    }

    public String setTableHeader(){
        return "<html><tr>" +
                "<td style='width:100px;'>Name</td>" +
                "<td style='width:100px;'>Title<td>" +
                "<td style='width:50px;'>Status</td>"+
                "<td style='width:30px;'>Date Iss</td>"+
                "<td style='width:30px'>Date Due</td>"+
                "<td style='width:20px;'>Overdue</td>"+
                "<td style='width:30px;'>Fine Amount</td>"+
                "</tr></html>";
    }




}
