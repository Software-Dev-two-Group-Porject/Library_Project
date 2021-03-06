import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Created By Jonathon on 18/02/2021
 * Read all users from .dat file and create array
 **/

public class User
{
   private String status, name, email, password, block;
   private int userID, booksOnLoan, room;
   private boolean overdue;
   private User[] userList;
   static Scanner keyboard = new Scanner(System.in);

   User() {  } //Default constructor

   User(int userID, String status, String name) {
      this.userID = userID;
      this.status = status;
      this.name = name;

   } //Alt constructor

   private String printHeader() {
      return String.format("%-10s\t%-10s\t%-50s\t%-80s\t%-5s\t-5s\t-15s\t-15s\t%s", "ID", "Status", "Name", "Email", "Block", "Room", "Books on loan", "Overdue fines");
   }

   public void printUserList(User [] userList) {
      System.out.println("User List");
      printHeader();

      for (int i = 0; i < userList.length; i++) {
         System.out.println(userList[i].toString());
      } //for
   } //Password not shown as in real world would be hashed for security

   public void initUserList() {
      String line = "";

      int i = 0;
      String [] userArray = new String[100];

      try (FileReader file = new FileReader(new File("src\\Data\\users.dat"))) {
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
      int onLoan, tempUserID, theirRoom;
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

   public boolean checkExisting(int id) {
      boolean exists = false;
      for (int i = 0; i < this.userList.length; i++)
      {
         if (this.userList[i].getUserID() == id)
         {
            exists = true;
         }
      }
      return exists;

   } //checkExisting

   public User getUserByEmail(String email) {
      User returnUser = new User();
      for (int i = 0; i < this.userList.length; i++) {
         if(this.userList[i].getEmail().equals(email)) {
            returnUser = this.userList[i];
         }
      } //for
      return returnUser;
   } //getUserByEmail


   public void addUserToList(User user) {
      User [] newUserList = new User[userList.length + 1];

      for (int x = 0; x < userList.length; x++) {
         newUserList[x] = userList[x];
      }//for
      newUserList[newUserList.length-1] = user;
      userList = newUserList;

   } //addUserToList


   public void editUser(int userID) {
      User editUser = new User();
      for (int i = 0; i < this.userList.length; i++) {
         if(this.userList[i].getUserID() == userID) {
            this.userList[i] = editUser;
         }

      } //for

   }//edit user


   public void deleteUser(int userID, int prompt) {
      char delete = 'N';

      for (int x = 0; x < userList.length; x++) {
         if(userList[x].getUserID() == userID) {
            System.out.println(printHeader());
            System.out.println(userList[x].toString());
            if (prompt != 0) {
               System.out.println("Please confirm you would like to delete user. (Y/N)");
               delete = keyboard.nextLine().toUpperCase().charAt(0);
            }
            if ((delete == 'Y') || (prompt == 0)) {
               User deleteUser = userList[x];
               userList[x] = userList[0];
               userList[0] = deleteUser;
               userList = Arrays.copyOfRange(userList,1,userList.length);
               if (prompt !=0) { System.out.println("User has been deleted."); }
            } else {
               System.out.println("User has not been deleted.");
               break;
            }
         }
      }
   }//deleteUser




   public void saveUsers() {
      String file = "src\\Data\\users.dat";
      BufferedWriter writer = null;
      String user = "";
      try {
         writer = new BufferedWriter(new FileWriter(file));
         for (int i=0; i < userList.length; i++) {
            user += userList[i].saveStringForUsersDat();

         }//for
         writer.write(user);

      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         try {
            writer.close();
         } catch (IOException ioe) {
            ioe.printStackTrace();
         } //catch
      } //finally
   } //saveUsers



   public String saveStringForUsersDat() {
      return this.userID + "," + this.status + "," + this.name + "," + this.email + "," + this.password + "," + this.block + "," + this.room + "," + this.booksOnLoan + "," + this.overdue + "\n";
   } //



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
   public int getBooksOnLoan() { return booksOnLoan; }
   public void setBooksOnLoan(int booksOnLoan) { this.booksOnLoan = booksOnLoan; }
   public boolean isOverdue() { return overdue; }
   public void setOverdue(boolean overdue) { this.overdue = overdue; }


} //class



