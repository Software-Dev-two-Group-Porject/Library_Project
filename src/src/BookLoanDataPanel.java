import javax.swing.*;
import java.awt.*;

/**
 * Created By Jonathon on 18/03/2021
 * Update Comments About Program Here
 **/
public class BookLoanDataPanel extends JPanel {
    BookLoan [] bookLoans;
    CommonLabel headerLabel;
    JLabel studentName, bookTitle, orderStatus, dateIssued, dueDate, overDue, fineAmount;
    JComboBox<String> statusBox;
    CommonButton updateButton;
    CommonButton saveChanges;
    JPanel dataPanel;
    Design design = new Design();
    Catalog catalog;
    GridBagConstraints gbc = new GridBagConstraints();

    BookLoanDataPanel(BookLoan [] bookLoans, String [] statusChanges){
        this.bookLoans = bookLoans;
        this.setLayout(new BorderLayout());
        this.catalog = new Catalog();
        headerLabel = new CommonLabel(setTableHeader(), 12);
        this.add(headerLabel, BorderLayout.NORTH);
        dataPanel = new JPanel();
        this.add(dataPanel, BorderLayout.CENTER);
        dataPanel.setLayout(new GridBagLayout());
        saveChanges = new CommonButton("Save", design.btnAddColor, 12);
        updateButton = new CommonButton("Update", design.tableButtonColor, 10);
        studentName = new JLabel();
        bookTitle = new JLabel();
        statusBox = new JComboBox<>(statusChanges);
        orderStatus = new JLabel();
        dateIssued = new JLabel();
        dueDate = new JLabel();
        fineAmount = new JLabel();

        this.add(saveChanges, BorderLayout.SOUTH);
    }


    public void renderTable(BookLoan [] bookLoans){
        this.removeAll();
        this.repaint();
        this.validate();

    }

    public String setTableHeader(){
        return "<html><tr>" +
                "<td>Name</td>" +
                "<td>Title<td>" +
                "<td>Status</td>"+
                "<td>Date Issued</td>"+
                "<td>Date Due</td>"+
                "</tr></html>";
    }




}
