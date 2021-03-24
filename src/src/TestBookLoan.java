import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by rebeccamcavoy on 24/03/2021
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 **/
public class TestBookLoan
{

   Date date = new Date();
   static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String strDate = formatter.format(date);
   static LocalDate ld;

   public static void main(String[] args)
   {

      try {
         String str = "2021-03-08 12:30:34";
         BookLoan bookLoan = new BookLoan();
         bookLoan.setUserID(1234);
         bookLoan.setISBN("978-Testing ISBN");
         bookLoan.setDateIssued(Date.from(LocalDate.of(2021, 1, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
         bookLoan.setDueDate(formatter.parse( "2021-03-08 12:30:34"));
         System.out.println(str.charAt(0));
         System.out.println( "Getting due date: from " + formatter.format(bookLoan.getDueDate()));
         System.out.println("Printing the date issued: " + formatter.format(bookLoan.getDateIssued()));
         System.out.println(bookLoan.getDaysOverDue());
      } catch (ParseException pe) {
         pe.printStackTrace();

   }//main
}//class
}

