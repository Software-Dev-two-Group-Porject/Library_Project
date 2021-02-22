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


   //   user = user.getUserByID(160551);
      System.out.print("Please enter a user ID: ");
    //  user = user.getUserByID(keyboard.nextInt());
      user = user.getUserByEmail(keyboard.nextLine());

    //  user = user.getUserByEmail("afarrahfowler@ulster.ac.uk");

      System.out.print("Answer: ");
      System.out.println(user.toString());








   //   User.UserList("all");
   //   System.out.println("List of staff");
   //   User.UserList("student");





   //   System.out.print("Please enter a user name: ");
   //   User.UserSearch("Name", keyboard.nextLine());




   } //main

} //class
