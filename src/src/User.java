import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created By Jonathon on 18/02/2021
 * Read all users from .dat file and create array
 **/
public class User {
   private String name;
   private int userID;

   public static void UserList(String status)
   {

      String[][] users = new String[0][];
      try (Scanner userFile = new Scanner(new File("C:\\Users\\mike\\Dropbox\\COM558\\JavaPrograms\\src\\Library_Project\\src\\src\\users.dat")))
      {
         List<String[]> lines = new ArrayList<>();
         while (userFile.hasNextLine())
         { //Unknown number of users so keep repeating until no more
            String line = userFile.nextLine().trim();
            String[] splitted = line.split(", ");
            lines.add(splitted);
         } //Create a list

         users = new String[lines.size()][];
         for (int i = 0; i < users.length; i++)
         {
            users[i] = lines.get(i);
         }

         System.out.println("ID\t\tStatus\tName\t\t\tEmail");
            for (int i = 0; i < users.length; i++) {
               if ((status.equals("student")) || (status.equals("staff"))) {
                  if (!users[i][1].equals(status)) {
             //        if (users[i][1].equals(status)) { //Print Staff
                        for (int j = 0; j < users[i].length; j++) {
                           System.out.print(users[i][j] + "\t\t");
                        } //for
                        System.out.print("\n");
                     } //Print staff
             //     } else
              //    { //Print Students
               //      for (int j = 0; j < users[i].length; j++) {
                //        System.out.print(users[i][j] + "\t\t");
                 //    } //for
                 //    System.out.print("\n");
               //   }
               } else {
                  for (int j = 0; j < users[i].length; j++)
                  {
                     System.out.print(users[i][j] + "\t\t");
                  }
                  System.out.print("\n");
               } //Print all
            }

      } catch (FileNotFoundException e)
      { //error handling
         System.err.format("File does not exist\n");
      } //Catch
   } //listUsers

   public static void UserSearchID(String UserID) {

      String[][] users = new String[0][];
      try (Scanner userFile = new Scanner(new File("C:\\Users\\mike\\Dropbox\\COM558\\JavaPrograms\\src\\Library_Project\\src\\src\\users.dat")))
      {
         List<String[]> lines = new ArrayList<>();
         while (userFile.hasNextLine())
         { //Unknown number of users so keep repeating until no more
            String line = userFile.nextLine().trim();
            String[] splitted = line.split(", ");
            lines.add(splitted);
         } //Create a list

         users = new String[lines.size()][];
         for (int i = 0; i < users.length; i++)
         {
            users[i] = lines.get(i);
         }

         Boolean found = false;
         for (int i = 0; i < users.length; i++)
         {
            if (users[i][0].equals(UserID)) { //If ID in database matches search term
               System.out.println("ID\t\tStatus\tName\t\t\tEmail");
               for (int j = 0; j < users[i].length; j++)
               {
                  System.out.print(users[i][j] + "\t\t");
               } //for
               found = true;
               break;
            }
         }// For loop
         if (!found)
            System.out.println("User " + UserID + " not found.");

      } catch (FileNotFoundException e)
      { //error handling
         System.err.format("File does not exist\n");
      } //Catch
   } //listUsers //This will probably be taken out as the UserSearch is better

   public static void UserSearch(String SearchCat, String UserInfo) {

      String[][] users = new String[0][];
      try (Scanner userFile = new Scanner(new File("C:\\Users\\mike\\Dropbox\\COM558\\JavaPrograms\\src\\Library_Project\\src\\src\\users.dat")))
      {
         List<String[]> lines = new ArrayList<>();
         while (userFile.hasNextLine())
         { //Unknown number of users so keep repeating until no more
            String line = userFile.nextLine().trim();
            String[] splitted = line.split(", ");
            lines.add(splitted);
         } //Create a list

         users = new String[lines.size()][];
         for (int i = 0; i < users.length; i++)
         {
            users[i] = lines.get(i);
         }

         int category = 99;

         switch (SearchCat) {
            case "ID":
               category = 0;
               break;
            case "Name":
               category = 2;
               break;
            case "Email":
               category = 3;
               break;
         }

   //      Boolean found = false;

         if (category != 99)
         {

            for (int i = 0; i < users.length; i++)
            {

               try
               {

                  if (users[i][category].equals(UserInfo))
                  { //If search term in database matches search term
                     System.out.println("ID\t\tStatus\tName\t\t\tEmail");
                     for (int j = 0; j < users[i].length; j++)
                     {
                        System.out.print(users[i][j] + "\t\t");
                     } //for
                     //found = true;
                     break;
                  }
               } catch(ArrayIndexOutOfBoundsException e) {
                  System.out.println("User " + SearchCat + " of " + UserInfo + " not found.");
                  break;
               }
            }// For loop
      //      if (!found)
      //         System.out.println("User " + SearchCat + " of " + UserInfo + " not found.");
         } else {
            System.out.println("Invalid category chosen.");
         }

      } catch (FileNotFoundException e)
      { //error handling
         System.err.format("File does not exist\n");
      } //Catch
   } //listUsers






   //Creating User File to confirm merge from just created branch in dev branch
   String id, firstname, surname;

   User(){

   }


   User(String id, String firstname, String surname){
      this.id = id;
      this.firstname = firstname;
      this.surname = surname;
   }


} //class



