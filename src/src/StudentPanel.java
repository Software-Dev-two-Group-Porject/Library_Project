import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentPanel extends JPanel {
    CommonLabel labelBooksRequested, booksOnLoan, booksOverDue, isbn,
            title, author, genre, subGenre, quantity;
    HeaderPanel headerPanel;
    Catalog catalog;
    BookDataPanel bookDataPanel;
    BookLoanList bookLoanList;
    BookLoanDataPanel bookLoanDataPanel;
    JPanel studentDetailPanel;
    GridBagConstraints gbc = new GridBagConstraints();
    Design design = new Design();
    String [] statusChanges = {"requested", "ready"};
    StudentList studentList = new StudentList();
    CommonButton requestButton;
    StudentPanel(String email){
        this.setLayout(null);
        this.setBackground(design.bgColor);

        studentList.populateList();
        Student student = studentList.findStudentByEmail(email);

        bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();
        headerPanel = new HeaderPanel("Student", student.getName());
        headerPanel.setBounds(20, 5, 900, 135 );
        this.add(headerPanel);

        bookLoanDataPanel = new BookLoanDataPanel(bookLoanList.getBookLoansByUserId(student.getUserID()), statusChanges); 
        studentDetailPanel = new JPanel();
        studentDetailPanel.setLayout(new GridLayout(3, 0));

        setCurrentBookLoans(bookLoanList.getBookLoansByUserId(student.getUserID()));
        catalog = new Catalog();
        catalog.initializeCatalogue();
        bookDataPanel = new BookDataPanel(this, null);
        bookDataPanel.setBounds(50, 300, 900, 250);
        this.add(bookDataPanel);

        isbn = new CommonLabel("", 12);
        isbn.setBounds(70, 145, 300, 20);
        title = new CommonLabel("", 12);
        title.setBounds(70, 170, 300, 20);

        author = new CommonLabel("", 12);
        author.setBounds(70, 200, 300, 20);

        genre = new CommonLabel("", 12);
        genre.setBounds(70, 225, 300, 20);

        subGenre = new CommonLabel("", 12);
        subGenre.setBounds(70, 250, 300, 20);

        quantity = new CommonLabel("", 12);
        quantity.setBounds(70, 275, 150, 20);

        requestButton = new CommonButton("Request", design.tableButtonColor,10);
        requestButton.setBounds(275, 275, 100, 20);
        requestButton.setVisible(false);

        bookDataPanel.renderTable(catalog.getCatalogueList());

        this.add(isbn); this.add(title); this.add(author); this.add(genre); this.add(subGenre);
        this.add(quantity); this.add(requestButton);

        this.add(bookLoanDataPanel);

        //need to display the current book order.
        // need a smaller data table similar to the catalog,
        //display the name of the book as well as other details so book can be eaily views.
        //have a status update button#
    }


    private void setCurrentBookLoans(BookLoan [] bookLoans){
        //this method will populated the panel with the text for the current book loans.
    }

    public void setBookLabels(Book book){
        isbn.setText("Isbn: " + book.getIsbn());
        title.setText("Title: " + book.getTitle());
        author.setText("Author: " + book.getAuthor());
        genre.setText("Genre: " + book.getGenre());
        subGenre.setText("Sub Genre: " + book.getSubGenre());
        quantity.setText("Quantity: " + String.valueOf(book.getQuantity()));
        if(book.getQuantity() > 0){
            requestButton.setVisible(true);
        }
    }
}
