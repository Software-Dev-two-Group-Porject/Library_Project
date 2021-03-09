/**
 * Created by Alisha on 20/02/2021
 * UPDATE PROGRAM COMMENTS ABOUT PROJECT HERE
 **/
public class Student extends User {
   private int roomNumber, booksOnLoan = 0;
   private String userName = " ", userPassword = " ", courseName = " ";
   private String[] bookLoans;
   private char blockID;

   Student() {
      super();
   }

   //<<<<<<< Updated upstream
   public Student(int id, String status, String name, String userPassword, char block, int roomNumber) {
      super(id, status, name);
      this.blockID = block;
      this.roomNumber = roomNumber;
      this.userPassword = userPassword;
   }

      public int getRoomNumber() {
         return this.roomNumber;
      }

      public void setRoomNumber ( int roomNumber) {
         this.roomNumber = roomNumber;
      }

      public String getUserName ()
      {
         return userName;
      }

      public void setUserName (String userName)
      {
         this.userName = userName;
      }

      public String getUserPassword ()
      {
         return userPassword;
      }

      public void setUserPassword (String userPassword)
      {
         this.userPassword = userPassword;
      }

      public char getBlockID() {
         return blockID;
      }

      public void setBlockID(char blockID) {
         this.blockID = blockID;
      }

      public String getCourseName() {
         return courseName;
      }

      public void setCourseName(String courseName) {
         this.courseName = courseName;
      }

   public String toString ()
      {
         return String.format(super.toString() + " " + this.blockID + " " + this.roomNumber);
      } //Password not shown as in real world would be hashed for security


}