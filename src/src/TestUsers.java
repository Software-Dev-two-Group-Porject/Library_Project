import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mike on 19/02/2021
 * UPDATE PROGRAM INFO HERE
 **/
public class TestUsers
{
   static Scanner keyboard = new Scanner(System.in);

   public static String askString(String question) {
      System.out.print(question);
      return keyboard.nextLine();
   } //askString

   public static int askInt(String question) {
      System.out.print(question);
      String number = keyboard.nextLine();
      int retNumber = Integer.parseInt(number);
      return retNumber;
   } //askInt

   public static boolean askReply(String question) {
      System.out.print(question + " (Y/N)");
      boolean reply = false;
      char answer;
      answer = keyboard.nextLine().toUpperCase().charAt(0);
      if (answer == 'Y') {
         reply = true;
      }
      return reply;
   } //askReply


   public static void main(String[] args) throws IOException
   {

      User user = new User();
      user.initUserList();

      //Code to edit a user
      //Also not in the right place but getting it working first!

      System.out.println("Editing existing user\n===============\n");

      int id = askInt("Enter the user's ID: ");

      if (user.checkExisting(id)) {
         user = user.getUserByID(id);
         System.out.println(user.toString());
         String name = "", email = "", status = "", password = "", block = "";
         int room = 0, choice;
         boolean overdue = false;
         do {
            choice = askInt("Options\n========================\n1. Name\n2. Email\n2. Password\n4. Block\n5. Room\n6. Overdue\n0. Exit\nPlease enter which element you'd like to edit: ");
            switch (choice) {
               case 1:
                  name = askString("Please enter their updated name: ");
                  break;
               case 2:
                  email = askString("Please enter their updated email: ");
                  break;
               case 3:
                  password = askString("Please enter their new password: ");
                  break;
               case 4:
                  block = askString("Please enter their block: ");
                  break;
               case 5:
                  room = askInt("Please enter their new room: ");
               case 6:
                  int askOverdue = askInt("Please press 1 to mark their account as overdue, press 2 to remove the flag: ");
                  if (askOverdue == 1)
                     overdue = true;
               case 0:
                  System.out.println("Finished editing.");
                  break;

            } //Switch

         } while (choice != 00); //do
         User editUser = new User();

         if (name.equals("")) { name = user.getName(); }
         if (email.equals("")) { email = user.getEmail(); }
         if (status.equals("")) { status = user.getStatus(); } //Do we need to let them edit the status?
         if (password.equals("")) { password = user.getPassword(); }
         if (block.equals("")) { block = user.getBlock(); }
         if (room == 0) { room = user.getRoom(); }
         int booksOnLoan = user.getBooksOnLoan();

         editUser.setUserID(id);
         editUser.setName(name);
         editUser.setStatus(status);
         editUser.setEmail(email);
         editUser.setPassword(password);
         editUser.setBlock(block);
         editUser.setRoom(room);
         editUser.setBooksOnLoan(booksOnLoan);
         editUser.setOverdue(overdue);

         user.initUserList(); //Tbh I can't work out why I have to do this again, but it was the only way to get it to work
         user.deleteUser(id, 0); //Delete the existing entry before adding the updated one
         user.addUserToList(editUser);
         user.saveUsers();

         User editedUser = user.getUserByID(id);
         System.out.println("The user has been updated.");
         System.out.println(editedUser.toString());
      } else {
         System.out.println("User doesn't exist in database.");  //Add the code to jump to add user menu?
      }

   System.out.println("=================================================");

      //Code to add a new student
      //This isn't in the right place but it can be moved,
      System.out.println("Add New Student\n===============\n");

      //int id = askInt("Please enter the user's ID: "); //Keep this! Only commented out as id is already declared above

      if (user.checkExisting(id)) {
         System.out.println("User already exists in database."); //Add function to go straight to edit user??
      } else {
         String name = askString("Please enter the user's name: ");
         String email = askString("Please enter the user's email: ");
         String password = askString("Please enter the user's password: ");
         String block = askString("Please enter the user's block: ");
         int room = askInt("Please enter the user's room number: ");

         // boolean add = askReply("Would you like to add this user to the database?");

         System.out.print("Add them? (Y/N): ");
         boolean add = false;
         char answer;
         answer = keyboard.nextLine().toUpperCase().charAt(0);
         if (answer == 'Y')
         {
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
      } //Else - user already exists









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
