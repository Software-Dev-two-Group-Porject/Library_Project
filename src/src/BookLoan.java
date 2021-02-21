import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Created by rebeccamcavoy on 21/02/2021
* BookLoan Class
**/
public class BookLoan
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy hh:mm:ss");
    Date thisDate = new Date();

    //Declare Variables
    int isbn;
    int userID;
    private Date dateIssued;
    private Date dateReturned;
    int daysOverdue;
    boolean isOverdue;
    double finePerDay;

    BookLoan(){
    }//Default Constructor

   BookLoan(int userID, int isbn, Date dateIssued, Date dateReturned, int daysOverdue, boolean isOverdue, double finePerDay){
       this.isbn = isbn;
       this.userID = userID;
       this.dateIssued = dateIssued;
       this.dateReturned = dateReturned;
       this.daysOverdue = daysOverdue;
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

   public int getDaysOverdue()
   {
      return daysOverdue;
   }

   public void setDaysOverdue(int daysOverdue)
   {
      this.daysOverdue = daysOverdue;
   }

   public boolean isOverdue()
   {
      return isOverdue;
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
}//class