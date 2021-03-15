/**
 * Created by mike on 15/03/2021
 * Method to add a new user, staff or student, after checking the user ID doesn't already exist in database
 **/
public class AddUser
{
   public static void addUser(int id)
   {
      User user = new User();
      user.initUserList();
      if (user.checkExisting(id))
      {
         System.out.println("User already exists in database."); //Add function to go straight to edit user??
      } else
      {
         String name = Utils.askString("Please enter the user's name: ", "[a-zA-Z\\s'áéíóúÁÉÍÓÚ-]+");
         while (Utils.countWords(name) < 2)
         {
            name = Utils.askString("Please enter their full name: ", "[a-zA-Z\\s'áéíóúÁÉÍÓÚ-]+");
         }
         String email = Utils.askEmail("Please enter the user's email: ");
         String password = Utils.askString("Please enter the user's password: ", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"); //Allows no whitespace and must be between 6 and 20 characters
         boolean student = Utils.askReply("Is this user a student?");
         String status = "staff";
         String block = "null";
         int room = 0;
         String course = "null";
         if (student)
         {
            status = "student";
            block = Utils.askString("Please enter the user's block: ", "[a-zA-Z]");
            block = block.toUpperCase();
            room = Utils.askInt("Please enter the user's room number: ");
            while ((room < 1) || (room > 499))
            { // This would be set to the maximum room number in the largest block
               room = Utils.askInt("Room number invalid. Please enter their room: ");
            }
            course = Utils.askString("Please enter their course: ", "^(?=[\\S\\s]{1,50}$)[\\S\\s]*");
         }
         boolean add = Utils.askReply("Would you like to add this user to the database?");

         if (add)
         {
            User addUser = new User();
            addUser.setUserID(id);
            addUser.setName(name);
            addUser.setEmail(email);
            addUser.setPassword(password);
            if (student)
            {
               addUser.setStatus(status);
               addUser.setBlock(block);
               addUser.setRoom(room);
               addUser.setCourse(course);
            }
            addUser.setOverdue(false); //If it's a new user we assume they aren't overdue

            user.addUserToList(addUser);
            user.saveUsers();

            User addedUser = user.getUserByID(id);
            System.out.println(addedUser.toString());

         } //addUser to list
      } //Else
   } //addUser
} //class
