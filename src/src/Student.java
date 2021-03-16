/**
 * Created by Alisha on 20/02/2021
 * UPDATE PROGRAM COMMENTS ABOUT PROJECT HERE
 **/
public class Student extends User
{
   private int roomNumber;
   private String  course;
   private final int MAXBOOKSONLOAN = 5;
   private boolean fine_defaulter;
   private double fine;
   private String[] bookLoanISBNS;
   private char blockLetter;

   Student()
   {
      super();
   }

   //<<<<<<< Updated upstream
   public Student(int id, String status, String name, String email, String password, char block, int roomNumber, String course)
   {
      super(id, status, name, email, password);
      this.blockLetter = block;
      this.setEmail(email);
      this.roomNumber = roomNumber;
      this.course = course;
   }
//=======
//   public Student(String id, String firstname, String surname, int userID, int blockNumber, int roomNumber, int booksOnLoan, String userName, String userPassword, boolean fine_defaulter, double fine, ArrayList<Loan> bookLoans)
//   {
//      super(id, firstname, surname);
//      this.userID = userID;
//      this.blockNumber = blockNumber;
//      this.roomNumber = roomNumber;
//      this.booksOnLoan = booksOnLoan;
//      this.userName = userName;
//      this.userPassword = userPassword;
//      this.fine_defaulter = fine_defaulter;
//      this.fine = fine;
//   }

//>>>>>>> Stashed changes

   public int getRoomNumber()
   {
      return roomNumber;
   }

   public void setRoomNumber(int roomNumber)
   {
      this.roomNumber = roomNumber;
   }

   public char getBlockLetter() {
      return blockLetter;
   }

   public String getCourse(){
      return this.course;
   }

   public void setCourse(String course){
      this.course = course;
   }

   public void setBlockLetter(char blockLetter){
      this.blockLetter = blockLetter;
   }
   public String toString()
   {
      return String.format(super.toString() + " " + this.course + " " +this.blockLetter + " " + this.roomNumber);
   } //Password not shown as in real world would be hashed for security
}

//   public String ViewUserInformation()
//<<<<<<< Updated upstream
//      System.out.println("Username:\t\t\t" + username + "\nBooks on Loan:\t\t" + booksOnLoan + "\nFines:\t\t\t" + fineAmount);
//      System.out.println("\nPlease be aware fines are charged individually on books at a rate of " + BOOKFINEPERDAY + "per day. " +
//      "Please ensure to return borrowed books before fines may occur.";)
//
//   public String ChangeUserInformation()
//=======
//   {
//      System.out.println("Username:\t\t\t" + username + "\nBooks on Loan:\t\t" + booksOnLoan + "\nFines:\t\t\t" + fineAmount);
//     System.out.println("\nPlease be aware fines are charged individually on books at a rate of " + BOOKFINEPERDAY + "per day. " +
//            "Please ensure to return borrowed books before fines may occur.";)
//   }

   //public String ChangeUserInformation() {
//>>>>>>> Stashed changes
//      public void renewBook()
//      public void requestBook)
//      public double payFine()
//      public String viewLoanInformation()
//<<<<<<< Updated upstream
//
//
//      System.out.println("Book Title:" + bookTitle + "\t" + "Issue date:" + issue_date + "\t" + "Due date" + due_date + "\t"
//      + "Return_date:" + return_date + "\t" + "User ID " + userID + "\t" + "Books on Loan :"
//      + bookID + "\t" + "Fine status  :" + fine_status + "\t"
//
//
//=======

   //}

    //  System.out.println("Book Title:" + bookTitle + "\t" + "Issue date:" + issue_date + "\t" + "Due date" + due_date + "\t"
    //  + "Return_date:" + return_date + "\t" + "User ID " + userID + "\t" + "Books on Loan :"
    //  + bookID + "\t" + "Fine status  :" + fine_status + "\t");


//>>>>>>> Stashed changes

