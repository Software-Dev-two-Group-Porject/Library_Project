import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mike on 19/02/2021
 * UPDATE PROGRAM INFO HERE
 **/
public class TestUsers
{

   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);

      System.out.println("List of everyone");
      User user = new User();

      user.initUserList();

      //Code to add a new student
      //This isn't in the right place but it can be moved,
      System.out.println("Add New Student\n===============\n");
      int id = user.askInt("ID");
      String name = user.askString("name"); //I have no idea why it asks the question but won't allow an answer
      String email = user.askString("email");
      String password = user.askString("password");
      String block = user.askString("block");
      int room = user.askInt("room number");

     // boolean add = user.askReply("Would you like to add this user to the database?");

      System.out.print("Add them? (Y/N)");
      boolean add = false;
      char answer;
      answer = keyboard.nextLine().toUpperCase().charAt(0);
      if (answer == 'Y') {
         add = true;
      }

      if (add) {
         User addUser = new User();
         addUser.setUserID(id);
         addUser.setName(name);
         addUser.setStatus("student");
         addUser.setEmail(email);
         addUser.setPassword(password);
         addUser.setBlock(block);
         addUser.setRoom(room);
         addUser.setBooksOnLoan(0); //If they're a new user we presume they have no books on loan
         addUser.setOverdue(false); //And no overdue fees

         user.addUserToList(addUser);
         user.saveUsers();

         User addedUser = user.getUserByID(id);
         System.out.println(addedUser.toString());


      } //addUser to list









    //  user = user.getUserByID(160551);
    //  System.out.print("Please enter a user ID: ");
    //  user = user.getUserByID(keyboard.nextInt());
    //  user = user.getUserByEmail(keyboard.nextLine());

     // user = user.getUserByEmail("afarrahfowler@ulster.ac.uk");

    //  System.out.print("Answer: ");
   //   System.out.println(user.toString());

      //Testing adding a new user

    //

    //  System.out.println("Hello");
  //    System.out.println(testAddUser.toString());

    //


    //  System.out.println("Delete");
    //  int delID = 7845;
    //  user.deleteUser(delID);


      //user.saveUsers();

      //User deletedUser = user.getUserByID(delID);
      //System.out.println(deletedUser == null);








   //   User.UserList("all");
   //   System.out.println("List of staff");
   //   User.UserList("student");





   //   System.out.print("Please enter a user name: ");
   //   User.UserSearch("Name", keyboard.nextLine());




   } //main

} //class
