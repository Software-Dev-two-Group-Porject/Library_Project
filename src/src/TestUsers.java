import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by mike on 19/02/2021
 * UPDATE PROGRAM INFO HERE
 **/
public class TestUsers
{

   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);

      System.out.println("List of everyone");
      User.UserList("all");
      System.out.println("List of staff");
   //   User.UserList("student");



     // System.out.print("Please enter a user ID: ");
      //User.UserSearch("ID", keyboard.nextLine());

      System.out.print("Please enter a user name: ");
      User.UserSearch("Name", keyboard.nextLine());




   } //main

} //class
