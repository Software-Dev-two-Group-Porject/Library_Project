/**
 * Created by Alisha on 20/02/2021
 * UPDATE PROGRAM COMMENTS ABOUT PROJECT HERE
 **/
public class Student
{
   int userID, blockNumber, roomNumber, booksOnLoan = 0;
   String userName, userPassword;
   int MAXBOOKSONLOAN = 5;

   public Student(int userID, int blockNumber, int roomNumber, int booksOnLoan, String userName, String userPassword)
   {
      this.userID = userID;
      this.blockNumber = blockNumber;
      this.roomNumber = roomNumber;
      this.booksOnLoan = booksOnLoan;
      this.userName = userName;
      this.userPassword = userPassword;
   }

   public static void main(String[] args)
   {


      

   }//main
}//class
