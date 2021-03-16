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

    //  int id = Utils.askInt("Enter the user's ID: ");

      System.out.println("Name: ");
      String name = keyboard.nextLine();

      String test = Utils.nameValidation(name);

      System.out.println("Error: " + test);
      System.out.println("Result: " + name);


      user.printUserList(user.getUserList());

   //   user = user.getUserByID(id);
      System.out.println("HH");

    //  System.out.println(user.toString());





   //   System.out.println("Add New Student\n===============\n");

   //   AddUser.addUser(id);


   } //main

} //class
