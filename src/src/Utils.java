import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mike on 08/03/2021
 * Class containing methods for inputs and validation
 **/
public class Utils
{
   static Scanner keyboard = new Scanner(System.in);
   public static String askString(String question, String regex) {
      boolean regexPass;
      String answer;
      System.out.println(question);
      keyboard.nextLine(); //Necessary to create new line after nextInt input
      answer = keyboard.nextLine();
      if (regex == "") {
         regex = "^[^\\\\d\\\\s]+$"; //Non digits or whitespace and at least one character
      }
      do
         {
            if (!answer.matches(regex)) {
               System.out.println("Invalid characters entered, please try again:");
               regexPass = false;
               answer = keyboard.nextLine();
            } else
            {
               regexPass = true;
            }
         } while (!regexPass);
      return answer;
   } //askString

   public static String askEmail(String question) {
      String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+[.]+[A-Za-z.]+$";
   //   String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@↵\n" +
     //       "(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$"; //Email format & allowed characters
      boolean regexPass;
      String answer;
      System.out.println(question);
      answer = keyboard.next();
      do
      {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(answer);
      regexPass = matcher.find();

         if (!regexPass) {
            System.out.println("Invalid email entered, please try again:");
            regexPass = false;
            answer = keyboard.next();
         } else {
            regexPass = true;
         }
      } while (!regexPass);
      return answer;
   } //askString

   public static int askInt(String question) {
      int retNumber = 0;
      System.out.print(question);
      while (true) {
         try
         {
            retNumber = keyboard.nextInt();
            break;
         } catch (InputMismatchException error) {
            System.out.println("Please enter only numbers: ");
            keyboard.next();
         }
      }
      return retNumber;
   } //askInt

   public static boolean askReply(String question) {
      System.out.print(question + " (Y/N) ");
      boolean reply = false;
      char answer;
      answer = keyboard.next().toUpperCase().charAt(0);
      if (answer == 'Y') {
         reply = true;
      }
      return reply;
   } //I still need to fix this - Michael askReply

   public static int countWords(String string) { //Used to check at least two words have been entered
      String[] words;
      if (string == null || string.isEmpty()) {
         return 0;
      } else {
         words = string.split("\\s+");
      }
      return words.length;
   } //countWords

   public static String capitalizeString(String string) { //Capitalise first letter of every word in a string
      char[] chars = string.toLowerCase().toCharArray();
      boolean found = false;
      for (int i = 0; i < chars.length; i++) {
         if (!found && Character.isLetter(chars[i])) {
            chars[i] = Character.toUpperCase(chars[i]);
            found = true;
         } else if (Character.isWhitespace(chars[i]) || chars[i]=='\'') {
            found = false;
         }
      }
      return String.valueOf(chars);
   }




} //class
