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
   public static String askString(String question) {
      boolean regexPass;
      String answer;
      System.out.println(question);
      answer = keyboard.next();
      do
      {
         if (!answer.matches("[a-zA-Z\\s-]+")) { //Currently this only allows spaces, letters and hypens, I have to finish it later to be used for various cases
            System.out.println("Invalid characters entered, please try again:");
            regexPass = false;
            answer = keyboard.next();
         } else {
            regexPass = true;
         }
      } while (!regexPass);
      return answer;
   } //askString

   public static String askEmail(String question) {
      String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
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
      System.out.print(question + " (Y/N)");
      boolean reply = false;
      char answer;
      answer = keyboard.nextLine().toUpperCase().charAt(0);
      if (answer == 'Y') {
         reply = true;
      }
      return reply;
   } //askReply




} //class
