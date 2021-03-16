import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat df = new SimpleDateFormat("dd/mm/yy hh:mm:ss");
    DateTimeFormatter formatterWithSecs = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");
    LocalDate today = LocalDate.now(); //importing LocalDate Library

    String date = "2021-03-08 12:30:34";
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    //Declare Variables
    String isbn;
    int userID;
    private Date dateIssued;
    private Date dateReturned;
    private Date dueDate;           //or LocalDate dueDate?
    boolean isOverdue;
    double finePerDay;
    String status;
    Instant instant;

    BookLoan(){
    }//Default Constructor

   BookLoan(int userID, String isbn, Date dateIssued, Date dueDate,  Date dateReturned, boolean isOverdue, double finePerDay){
       this.isbn = isbn;
       this.userID = userID;
       this.dateIssued = dateIssued;
       this.dueDate = dueDate;
       this.dateReturned = dateReturned;
       this.isOverdue = isOverdue;
       this.finePerDay = finePerDay;
    }//constructor


   //Get and Set Methods
   public String getISBN()
   {
      return isbn;
   }

   public void setISBN(String isbn)
   {
      this.isbn = isbn;
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
       System.out.println("At get days overdue" + dateFormat.format(dueDate));
       //System.out.println(date.charAt(16));
       Date newDate = new Date();
       System.out.println("Todays date as string:" + dateFormat.format(newDate));
      LocalDate secondDate = LocalDate.parse(dateFormat.format(dueDate), format); //convert from String type to readable Date
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

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
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

   public String toString(){
        return String.format("%d, %s, %s, %s, %s, %d", this.userID, this.isbn,
                dateFormat.format(this.dateIssued), dateFormat.format(this.dateReturned), this.isOverdue, this.getDaysOverDue());
   }
}//class