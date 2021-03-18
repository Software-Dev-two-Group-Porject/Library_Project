import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentPanel extends JPanel {
    CommonLabel labelBooksRequested, booksOnLoan, booksOverDue,
            title, author, genre, subgenre, quantity;
    HeaderPanel headerPanel;
    Catalog catalog;
    BookDataPanel bookDataPanel;
    BookLoanList bookLoanList;
    JPanel studentDetailPanel;
    JPanel bookLoanDetail;
    JPanel buttonContainer;
    GridBagConstraints gbc = new GridBagConstraints();

    StudentPanel(Student student){
        this.setLayout(null);
        bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();
        headerPanel = new HeaderPanel("Student", student.getName());
        headerPanel.setBounds(20, 10, 850, 120);

        studentDetailPanel = new JPanel();
        studentDetailPanel.setLayout(new GridLayout(3, 0));

        bookLoanDetail = new JPanel();
        bookLoanDetail.setLayout(new GridBagLayout());
        bookLoanDetail.setBounds(300, 80, 400, 200);

        setCurrentBookLoans(bookLoanList.getBookLoansByUserId(student.getUserID()));
        catalog = bookDataPanel.getBookCatalog();

        this.add(bookLoanDetail);
        bookDataPanel = new BookDataPanel(this, null);
        bookDataPanel.setBounds(50, 300, 900, 250);
        this.add(bookDataPanel);

        //need to display the current book order.
        // need a smaller data table similar to the catalog,
        //display the name of the book as well as other details so book can be eaily views.
        //have a status update button#

    }


    private void setCurrentBookLoans(BookLoan [] bookLoans){
        //this method will populated the panel with the text for the current book loans.
        CommonLabel title = new CommonLabel("Title", 12);
        CommonLabel status = new CommonLabel("Status", 12);

    }
}
