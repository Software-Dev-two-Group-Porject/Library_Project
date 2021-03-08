import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
* Created by rebeccamcavoy on 21/02/2021
* BookLoan Class
**/
public class BookLoan
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy hh:mm:ss");
    LocalDate today = LocalDate.now(); //importing LocalDate Library

    //Declare Variables
    int isbn;
    int userID;
    private Date dateIssued;
    private Date dateReturned;
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
      LocalDate nowDate = LocalDate.now(); //getting current date
      LocalDate pastDate = LocalDate.now(); //if storing dateReturned, requires change of data type to LocalDate for 'dateReturned' variable
      return (int) ChronoUnit.DAYS.between(pastDate,nowDate); //return number of days difference
   }

   //or - alternative method to above
   ////instead of being a value within the constructor we can use the
   //      public int getDaysOverDue(){
   //         LocalDate nowDate = LocalDate.now(); //getting current date
   //         LocalDate secondDate = LocalDate.parse(dateReturned,formatter);// correct variable use here? - //parse used to convert String to LocalDate format
   //         long daysDifference = ChronoUnit.DAYS.between(nowDate,secondDate);
   //         return (int) daysDifference;
   //      }


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