import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentPanel extends JPanel {
    CommonLabel labelBooksRequested, booksOnLoan, booksOverDue;
    HeaderPanel headerPanel;
    Catalog catalog;
    BookDataPanel bookDataPanel;
    BookLoanList bookLoanList;
    JPanel bookDetailPanel;
    JPanel bookLoanDetail;
    JPanel buttonContainer;




    StudentPanel(Student student){
        this.setLayout(null);

        headerPanel = new HeaderPanel("Student", student.getName());
        headerPanel.setBounds(20, 10, 850, 120);
        catalog = new Catalog();
        catalog.initializeCatalogue();

        bookDataPanel = new BookDataPanel(this, null);
        bookDataPanel.setBounds(50, 300, 900, 250);


        this.add(bookDataPanel);


        //need to display the current book order.
        // need a smaller data table similar to the catalog,
        //display the name of the book as well as other details so book can be eaily views.
        //have a status update button#
    }

}
