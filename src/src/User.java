import java.util.Scanner;


/**
 * Created By Jonathon on 18/02/2021
 * Read all users from .dat file and create array
 **/

public class User
{
   private String status, name, email, password, block, course;
   private int userID, room;
   private boolean overdue;

   User() {  } //Default constructor

   User(int userID, String status, String name, String email, String password) {
      this.userID = userID;
      this.status = status;
      this.name = name;
      this.email = email;
      this.password = password;

   } //Alt constructor

   public static String printHeader() {
      return String.format("%-10s\t%-10s\t%-40s\t%-50s\t%-5s\t%-5s\t%-10s\t%s", "ID", "Status", "Name", "Email", "Block", "Room", "Overdue", "Course");
   }

   public String toString() {
      return String.format("%-10s\t%-10s\t%-40s\t%-50s\t%-5s\t%-5s\t%-10s\t%s", this.userID, this.status, this.name, this.email, this.block, this.room, this.overdue, this.course);
   } //toString

//   public User[] getUserList() { return this.userList; }

   protected static User setUser(String line) {
      User user = new User();
      int tempUserID, theirRoom;
      boolean overdue;
      String [] info = line.split(",");
      tempUserID = Integer.parseInt(info[0]);
      user.setUserID(tempUserID);
      user.setStatus(info[1]);
      user.setName(info[2]);
      user.setEmail(info[3]);
      user.setPassword(info[4]);
      if (info[1].equals("student")) { //Set student only values
         user.setBlock(info[5]);
         theirRoom = Integer.parseInt(info[6]);
         user.setRoom(theirRoom);
         user.setCourse(info[8]);
         overdue = Boolean.parseBoolean(info[7]);
         user.setOverdue(overdue);
      }
      return user;

   } //setUser

   public static boolean checkExisting(int id) {
      boolean exists = false;
      UserList existingUser = new UserList();
      existingUser.initUserList();

      for (int i = 0; i < existingUser.userList.length; i++)
      {
         if (existingUser.userList[i].getUserID() == id)
         {
            exists = true;
         }
      }
      return exists;

   } //checkExisting



   public String saveStringForUsersDat() {
      return this.userID + "," + this.status + "," + this.name + "," + this.email + "," + this.password + "," + this.block + "," + this.room + "," + this.overdue + "," + this.course + "\n";
   } //Format the string correctly for saving to the dat file

   public int getUserID() {  return userID;  }
   public void setUserID(int userID) { this.userID = userID; }
   public String getStatus() { return status; }
   public void setStatus(String status) { this.status = status; }
   public String getName() { return name; }
   public void setName(String name) { this.name = name; }
   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }
   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }
   public String getBlock() { return block; }
   public void setBlock(String block) { this.block = block; }
   public int getRoom() { return room; }
   public void setRoom(int room) { this.room = room; }
   public String getCourse() { return course; }
   public void setCourse(String course) { this.course = course; }
   public void setOverdue(boolean overdue) { this.overdue = overdue; }


} //class



