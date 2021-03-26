/**
 * Created by Alisha on 20/02/2021
 * UPDATE PROGRAM COMMENTS ABOUT PROJECT HERE
 **/
public class Student extends User
{
   private int roomNumber;
   private String  course;
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


