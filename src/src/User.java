import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Created By Jonathon on 18/02/2021
 * Read all users from .dat file and create array
 **/

public class User
{
   private String status, name, email, password, block, room;
   private int userID, booksOnLoan;
   private boolean overdue;
   private User[] userList;

   User() {  } //Default constr

   public void printUserList(User [] userList) {
      System.out.println("User List");
      System.out.println(String.format("%-10s\t%-10s\t%-50s\t%-80s\t%-5s\t-5s\t-15s\t-15s\t%s", "ID", "Status", "Name", "Email", "Block", "Room", "Books on loan", "Overdue fines"));

      for (int i = 0; i < userList.length; i++) {
         System.out.println(userList[i].toString());
      } //for
   } //Password not shown as in real world would be hashed for security

   public void initUserList() {
      String line = "";

      int i = 0;
      String [] userArray = new String[100];

      try (FileReader file = new FileReader(new File("src\\users.dat"))) {
         BufferedReader userFile = new BufferedReader(file);

         while((line = userFile.readLine()) != null) {
            userArray[i] = line;
            i++;
         }

      } catch (Exception e) { //error handling
         System.err.format("File does not exist\n");
      }

      String [] newUserArray = Arrays.copyOfRange(userArray, 0, i);

      this.userList = new User[newUserArray.length];

      for(int j = 0; j < newUserArray.length; j++) {
      //   System.out.println("Array " + newUserArray[j]);
       //  System.out.println("Length: " + newUserArray.length);
         this.userList[j] = setUser(newUserArray[j]);
      }

   } //initUserList

   public String toString() {
      return String.format("%-30s\t%-10s\t%-10s\t%-40s\t",this.name, this.userID, this.status, this.email);
   } //toString

   public User[] getUserList() { return this.userList; }

   private User setUser(String line) {
      User user = new User();
      int onLoan;
      boolean overdue;
      String [] info = line.split(",");

      user.setUserID(Integer.parseInt(info[0]));
      user.setStatus(info[1]);
      user.setName(info[2]);
      user.setEmail(info[3]);
      user.setPassword(info[4]);
      if (info[1].equals("student")) { //Set student only values
         user.setBlock(info[5]);
         user.setRoom(info[6]);
         onLoan = Integer.parseInt(info[7]);
         user.setBooksOnLoan(onLoan);
         overdue = Boolean.parseBoolean(info[8]);
         user.setOverdue(overdue);
         //Add user course
      }
      return user;

   } //setUser

   public User getUserByID(int id) {
      User returnUser = new User();

      for (int i = 0; i < this.userList.length; i++) {
         if(this.userList[i].getUserID() == id) {
            returnUser = this.userList[i];
         }

      } //for

      return returnUser;

   } //getUserByID


   public User getUserByEmail(String email) {
      User returnUser = new User();
      for (int i = 0; i < this.userList.length; i++) {
         if(this.userList[i].getEmail().equals(email)) {
            returnUser = this.userList[i];
         }
      } //for
      return returnUser;
   } //getUserByEmail

   public int getUserID() {  return userID;  }
   public void setUserID(int userID) {  this.userID = userID; }
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
   public String getRoom() { return room; }
   public void setRoom(String room) { this.room = room; }
   public int getBooksOnLoan() { return booksOnLoan; }
   public void setBooksOnLoan(int booksOnLoan) { this.booksOnLoan = booksOnLoan; }
   public boolean isOverdue() { return overdue; }
   public void setOverdue(boolean overdue) { this.overdue = overdue; }


} //class



