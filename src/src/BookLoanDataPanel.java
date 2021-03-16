import javax.swing.*;

/**
 * Created By Jonathon on 13/03/2021
 * Update Comments About Program Here
 **/
public class BookLoanDataPanel extends JPanel {
    StudentPanel studentPanel;

    JPanel topContainer, dataContainer, dataOuput;
    JLabel username, bookTitle, dueDate, dateReleased, status, isOverDue, fineAmount;



    BookLoanDataPanel(StudentPanel studentPanel){
        this.studentPanel = studentPanel;
    }


}
