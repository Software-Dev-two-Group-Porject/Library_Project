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

   static String name = "[a-zA-Z\\sáéíóúÁÉÍÓÚ-]+"; //Allows only letters, hyphens and spaces
   static String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+[.]+[A-Za-z.]+$"; //Email format & allowed characters
   static String password = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&-+=()])(?=\\S+$).{8,20}$"; //Must have 1 each uppercase, lowercase, number, special character & no whitespace
   static String numbers = "[0-9]+"; //Numbers only
   static String block = "[a-hA-h]"; //Would be set to only accept blocks that the university has
   final static int MAXROOM = 499; //Highest room number in any block
   static String course = "[a-zA-Z\\s'áéíóúÁÉÍÓÚ&,-]+"; //Allows only letters, ampersand, commas, hyphens and spaces
   static String bookName = "[a-zA-Z0-9\\sáéíóúÁÉÍÓÚ-'&(),]+"; //Allows letters, numbers, spaces and certain special characters

   Utils(){

   }

   public static String emailValidation(String input){
      String validationMessage = "";
      Pattern pattern = Pattern.compile(email);
      Matcher matcher = pattern.matcher(input);
      if(!matcher.find()) validationMessage += "Please enter a valid email address\n";
      if(!input.contains("@ulster.ac.uk")) validationMessage += "Please enter an Ulster academic email\n";
      return validationMessage;
   } //emailValidation

   public static String nameValidation(String input) {
      String validationMessage = "";
      if (!stringCheck(input, name)) validationMessage += "Name must only have letters, hyphens and spaces\n";
      if (!charCount(4, 50, input)) validationMessage += "Name must have between 4 and 50 characters\n";
      if (countWords(input) < 2) validationMessage += "Name must be at least two words\n";
      return validationMessage;
   } //nameValidation

   public static String passwordValidation(String input, String input2) {
      String validationMessage = "";
      if (!charCount(8,20,input)) validationMessage += "Password must have between 8 and 20 characters\n";
      if (!stringCheck(input, password))  validationMessage += "Password must include at least one each of lowercase, uppercase, number and special character\n";
      if (!input.equals(input2)) validationMessage += "Passwords must match";
      return validationMessage;
   } //passwordValidation

   public static String isbnValidation(String input) {
      String validationMessage = "";
      if (!stringCheck(input, numbers)) validationMessage += "ISBN must only have numbers and no spaces\n";
      if (input.length() != 10) validationMessage += "ISBN must have ten numbers\n";
      return validationMessage;
   } //isbnValidation

   public static String blockValidation(String input) {
      String validationMessage = "";
      if (!stringCheck(input, block)) validationMessage += "Please enter a block from A-H\n";
      if (input.length() != 1) validationMessage += "Please enter a single letter\n";
      return validationMessage;
   } //blockValidation

   public static String roomValidation(String input) {
      String validationMessage = "";
      if (Integer.parseInt(input) > MAXROOM) validationMessage += "Invalid room number";
      if (!stringCheck(input, numbers)) validationMessage += "Room must only have numbers and no spaces\n";
      if (!charCount(0,4,input)) validationMessage += "Room number must have one, two or three digits\n";
      return validationMessage;
   } //roomValidation

   public static String courseValidation(String input) {
      String validationMessage = "";
      if (!stringCheck(input, course)) validationMessage += "Course must only have letters, hyphens and spaces\n";
      if (!charCount(4, 60, input)) validationMessage += "Course must have between 4 and 60 characters\n";
      return validationMessage;
   } //courseValidation

   public static String bookValidation(String input) {
      String validationMessage = "";
      if (!stringCheck(input, bookName)) validationMessage += "Book name must only have letters, numbers and - ' & () ,\n";
      if (!charCount(4, 60, input)) validationMessage += "Book name must have between 4 and 60 characters\n";
      return validationMessage;
   } //courseValidation

   private static boolean charCount(int min, int max, String input) {
      boolean pass = true;
      input = input.trim();
      if ((input.length() <= min) || (input.length() >= max)) pass = false;
      return pass;
   } //checkInt

   private static boolean stringCheck(String input, String regexType) {
      boolean regexPass = true;
      input = input.trim();
      if (!input.matches(regexType)) regexPass = false;
      return regexPass;
   } //askString

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
      boolean regexPass;
      String answer;
      System.out.println(question);
      answer = keyboard.nextLine();
      do
      {
         Pattern pattern = Pattern.compile(email);
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

   public static int countWords(String string) { //Used to check how many words have been entered
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