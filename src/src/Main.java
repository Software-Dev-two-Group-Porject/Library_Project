import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created By Jonathon on 15/02/2021
 * Update Comments About Program Here
 **/
public class Main {
    Date date = new Date();
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = formatter.format(date);
   static LocalDate ld;

    public static void main(String [] args){
        StudentList studentList = new StudentList();
        studentList.populateList();
        studentList.printStudentList();

        //start to test the book loanList object,
        //this will need a file and will need to be read from and written too.
        try {
            String str = "2021-03-08 12:30:34";
            BookLoan bookLoan = new BookLoan();
            bookLoan.setUserID(1234);
            bookLoan.setISBN("978-whatever the fuck we want");
            bookLoan.setDateIssued(Date.from(LocalDate.of(2021, 1, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            bookLoan.setDueDate(formatter.parse( "2021-03-08 12:30:34"));
            System.out.println(str.charAt(0));
            System.out.println( "Getting due date" + formatter.format(bookLoan.getDueDate()));
            System.out.println("Printing the date issued" + formatter.format(bookLoan.getDateIssued()));
            System.out.println(bookLoan.getDaysOverDue());
        } catch (ParseException pe) {
            pe.printStackTrace();

        }
    }

}
