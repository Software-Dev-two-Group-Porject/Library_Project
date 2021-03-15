import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentPanel extends JPanel implements ActionListener {
    CommonLabel labelBooksRequested, booksOnLoan, booksOverDue;
    HeaderPanel headerPanel;
    Catalog catalog;
    BookDataPanel bookDataPanel;
    BookLoanList bookLoanList;
    JPanel bookDetailPanel;
    JPanel bookLoanDetail;


    StudentPanel(Student student){
        headerPanel = new HeaderPanel("Student", student.getName());
        headerPanel.setBounds(20, 10, 850, 120);
        catalog = new Catalog();
        catalog.initializeCatalogue();

        bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();


        //need to display the current book order.
        // need a smaller data table similar to the catalog,
        //display the name of the book as well as other details so book can be eaily views.
        //have a status update button#




    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
