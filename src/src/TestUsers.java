import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mike on 19/02/2021
 * UPDATE PROGRAM INFO HERE
 **/
public class TestUsers
{
   static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {

      User user = new User();
      user.initUserList();

      //Code to edit a user
      //Also not in the right place but getting it working first!

      System.out.println("Editing existing user\n===============\n");

      int id = Utils.askInt("Enter the user's ID: ");

      if (user.checkExisting(id)) {
         user = user.getUserByID(id);
         System.out.println(user.toString());
         String name = "", email = "", status = "", password = "", block = "";
         int room = 0, choice;
         boolean overdue = false, cancel = false;
         do {
            choice = Utils.askInt("Options\n========================\n1. Name\n2. Email\n2. Password\n4. Block\n5. Room\n6. Overdue\n9. Cancel all changes\n0. Save & Exit\nPlease enter which element you'd like to edit: ");
            switch (choice) {
               case 1:
                  name = Utils.askString("Please enter their updated name: ", "[a-zA-Z\\s'áéíóúÁÉÍÓÚ-]+");
                  while (Utils.countWords(name) < 2) {
                     name = Utils.askString("Please enter their full name: ", "[a-zA-Z\\s'áéíóúÁÉÍÓÚ-]+");
                  }
                  name = Utils.capitalizeString(name);
                  break;
               case 2:
                  email = Utils.askEmail("Please enter their updated email: ");
                  break;
               case 3:
                  password = Utils.askString("Please enter their new password: ", "");
                  break;
               case 4:
                  block = Utils.askString("Please enter their block: ", "[a-zA-Z]"); //Allow only single letter to be entered
                  block = block.toUpperCase();
                  break;
               case 5:
                  room = Utils.askInt("Please enter their new room: ");
                  do
                  { // This would be set to the maximum room number in the largest block
                     room = Utils.askInt("Room number invalid. Please enter their room: ");
                  } while ((room < 1) || (room > 999));
                  break;
               case 6:
                  boolean askOverdue = Utils.askReply("Please press Y to mark their account as overdue, press N to remove the flag: ");
                  if (askOverdue)
                     overdue = true;
                  break;
               case 9:
                  cancel = Utils.askReply("Are you sure you want to cancel? ");
                  break;
               case 0:
               //   System.out.println("Finished editing.");
                  break;

            } //Switch

         } while ((choice != 9) && (choice !=0)); //do

         if (!cancel)
         {

            User editUser = new User();

            if (name.equals(""))
            {
               name = user.getName();
            }
            if (email.equals(""))
            {
               email = user.getEmail();
            }
            if (status.equals(""))
            {
               status = user.getStatus();
            } //Do we need to let them edit the status?
            if (password.equals(""))
            {
               password = user.getPassword();
            }
            if (block.equals(""))
            {
               block = user.getBlock();
            }
            if (room == 0)
            {
               room = user.getRoom();
            }
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
            System.out.println("No changes made.");
         } //else
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
         String name = Utils.askString("Please enter the user's name: ", "[a-zA-Z\\s-]+");
         String email = Utils.askEmail("Please enter the user's email: ");
         String password = Utils.askString("Please enter the user's password: ", "^[^\\\\d\\\\s]{6,20}$"); //Allows no whitespace and must be between 6 and 20 characters
         String block = Utils.askString("Please enter the user's block: ", "[a-zA-Z]");
         int room = Utils.askInt("Please enter the user's room number: ");

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
