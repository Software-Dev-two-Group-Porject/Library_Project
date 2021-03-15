import java.io.IOException;
import java.util.Locale;
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

      System.out.println("Editing existing user\n===============\n");

      int id = Utils.askInt("Enter the user's ID: ");

      EditUser.editUser(id);

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
         String course = Utils.askString("Please enter their course: ", "");
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
            addUser.setCourse(course);
            addUser.setOverdue(false); //If it's a new user we assume they aren't overdue

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
