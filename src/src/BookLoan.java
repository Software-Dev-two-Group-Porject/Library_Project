import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
* Created by rebeccamcavoy on 21/02/2021
* BookLoan Class
**/
public class BookLoan
{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //used for formatting due date
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy hh:mm:ss");
    LocalDate today = LocalDate.now(); //importing LocalDate Library

    //Declare Variables
    int isbn;
    int userID;
    private Date dateIssued;
    private Date dateReturned;
    private String dueDate;           //or LocalDate dueDate?
    boolean isOverdue;
    double finePerDay;
    String status;

    BookLoan(){
    }//Default Constructor

   BookLoan(int userID, int isbn, Date dateIssued, Date dateReturned, boolean isOverdue, double finePerDay){
       this.isbn = isbn;
       this.userID = userID;
       this.dateIssued = dateIssued;
       this.dateReturned = dateReturned;
       this.isOverdue = isOverdue;
       this.finePerDay = finePerDay;
    }//constructor


   //Get and Set Methods
   public int getISBN()
   {
      return isbn;
   }

   public void setISBN(int ISBN)
   {
      this.isbn = ISBN;
   }

   public int getUserID()
   {
      return userID;
   }

   public void setUserID(int userID)
   {
      this.userID = userID;
   }

   public Date getDateIssued()
   {
      return dateIssued;
   }

   public void setDateIssued(Date dateIssued)
   {
      this.dateIssued = dateIssued;
   }

   public Date getDateReturned()
   {
      return dateReturned;
   }

   public void setDateReturned(Date dateReturned)
   {
      this.dateReturned = dateReturned;
   }

   //instead of being a value within the constructor we can use the
   public int getDaysOverDue(){
      LocalDate secondDate = LocalDate.parse(dueDate,formatter); //convert from String type to readable Date
      LocalDate todayDate = LocalDate.now(); //creating object for current date
      if (todayDate.compareTo(secondDate)>0){
      } //if today's date is past due date
      long daysOverDue = ChronoUnit.DAYS.between(secondDate,todayDate); //calc difference to find days overdue
      return (int) daysOverDue;
   }

   //or - alternative method to above
   ////instead of being a value within the constructor we can use the
   //public static int getDaysOverDue(){
      //LocalDate secondDate = LocalDate.of(2021,03,05); //need to input date here?
      //LocalDate todayDate = LocalDate.now(); //creating object for current date
      //if (todayDate.compareTo(secondDate)>0){
      //} //if today's date is past due date
      //long daysOverDue = ChronoUnit.DAYS.between(secondDate,todayDate); //calc difference to find days overdue
      //return (int) daysOverDue;
   //}


   public void setOverdue(boolean overdue)
   {
      isOverdue = overdue;
   }

   public double getFinePerDay()
   {
      return finePerDay;
   }

   public void setFinePerDay(double finePerDay)
   {
      this.finePerDay = finePerDay;
   }
}//class