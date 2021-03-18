/**
 * Created by mike on 15/03/2021
 * Method to edit an existing user, available only to staff
 **/
public class EditUser
{
   public static void editUser(int id) {
      User user = new User();
      UserList userList = new UserList();
      userList.initUserList();

      if (user.checkExisting(id)) {
         user = userList.getUserByID(id);
         System.out.println(user.toString());
         String name = "", email = "", status = "", password = "", block = "", course = "";
         int room = 0, choice;
         boolean overdue = false, cancel = false;
         do {
            choice = Utils.askInt("Options\n========================\n1. Name\n2. Email\n3. Password\n4. Block\n5. Room\n6. Overdue\n7. Course\n9. Cancel all changes\n0. Save & Exit\nPlease enter which element you'd like to edit: ");
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
                  password = Utils.askString("Please enter their new password: ", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
                  break;
               case 4:
                  block = Utils.askString("Please enter their block: ", "[a-zA-Z]"); //Allow only single letter to be entered
                  block = block.toUpperCase();
                  break;
               case 5:
                  room = Utils.askInt("Please enter their new room: ");
                  while ((room < 1) || (room > 499))
                  { // This would be set to the maximum room number in the largest block
                     room = Utils.askInt("Room number invalid. Please enter their room: ");
                  }
                  break;
               case 6:
                  boolean askOverdue = Utils.askReply("Please press Y to mark their account as overdue, press N to remove the flag: ");
                  if (askOverdue)
                     overdue = true;
                  break;
               case 7:
                  course = Utils.askString("Please enter their course: ", "");
                  course = Utils.capitalizeString(course);
                  break;
               case 9:
                  cancel = Utils.askReply("Are you sure you want to cancel? ");
                  break;
               case 0:
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

            status = user.getStatus(); //They have no need to edit the status of student/staff

            if (password.equals(""))
            {
               password = user.getPassword();
            }
            if (block.equals(""))
            {
               block = user.getBlock();
            }
            if (course.equals(""))
            {
               course = user.getCourse();
            }
            if (room == 0)
            {
               room = user.getRoom();
            }

            editUser.setUserID(id);
            editUser.setName(name);
            editUser.setStatus(status);
            editUser.setEmail(email);
            editUser.setPassword(password);
            editUser.setBlock(block);
            editUser.setRoom(room);
            editUser.setCourse(course);
            editUser.setOverdue(overdue);

            userList.initUserList();
            userList.deleteUser(id, 0); //Delete the existing entry before adding the updated one
            userList.addUserToList(editUser);
            userList.saveUsers();

            User editedUser = userList.getUserByID(id);
            System.out.println("The user has been updated.");
            System.out.println(editedUser.toString());
         } else {
            System.out.println("No changes made.");
         } //else
      } else {
         System.out.println("User doesn't exist in database.");
      }

   } //EditUser

} //class
