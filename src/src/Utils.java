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
      answer = keyboard.nextLine();
      if (regex != "") { //Empty string allows any character to be used
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
      }
      return answer;
   } //askString

   public static String askEmail(String question) {
      String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+[.]+[A-Za-z.]+$"; //Email format & allowed characters
      boolean regexPass;
      String answer;
      System.out.println(question);
      answer = keyboard.nextLine();
      do
      {
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(answer);
         regexPass = matcher.find(); //Checks if input string matches the regex and returns true or false
         if (!regexPass) {
            System.out.println("Invalid email entered, please try again:");
            regexPass = false;
            answer = keyboard.nextLine();
         } else {
            regexPass = true;
         }
      } while (!regexPass);
      answer = answer.toLowerCase();
      return answer;
   } //askString

   public static int askInt(String question) {
      int retNumber;
      System.out.println(question);
      while (true) {
         try {
            retNumber = Integer.parseInt(keyboard.nextLine());
            break;
         } catch (NumberFormatException error) {
            System.out.println("Please enter only numbers: ");
         }
      } //while
      return retNumber;
   } //AskInt

   public static boolean askReply(String question) {
      System.out.print(question + " (Y/N) ");
      boolean reply = false;
      char answer;
      answer = keyboard.nextLine().toUpperCase().charAt(0);
      if (answer == 'Y') {
         reply = true;
      }
      return reply;
   } //askReply

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
   } //capitalizeString


} //class