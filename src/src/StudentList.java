import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created By Jonathon on 05/03/2021
 * Update Comments About Program Here
 **/
public class StudentList {
   private Student[] studentList;


   StudentList() {

   }

   public void populateList() {
      BufferedReader reader = null;
      String file = "src\\Data\\users.dat";
      int s = 0;
      int i = 0;
      String line = "";
      String[] arr = new String[500];
      Student[] stdArr = new Student[arr.length];
      try {
         reader = new BufferedReader(new FileReader(file));
         while ((line = reader.readLine()) != null) {
            arr[i] = line;
            String[] data = line.split(",");
            if (data[1].toLowerCase().trim().equals("student")) {
               stdArr[s] = new Student(
                     Integer.parseInt(data[0]), //UserID
                     data[1], //User Status
                     data[2], //Username
                     data[4],
                     data[5].toUpperCase().charAt(0), //Student Block Letter
                     Integer.parseInt(data[6]) //Student Room Number.
               );
               s++;
            }
         }

         this.studentList = Arrays.copyOfRange(stdArr, 0, s);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            reader.close();

         } catch (IOException io) {
            io.printStackTrace();
         }
      }
   }


   public void printStudentList() {

      for (int i = 0; i < studentList.length; i++) {
         System.out.println(studentList[i].toString());
      }
   }

   public Student findByID(int id) {
      Student student = new Student();
      for (int i = 0; i < studentList.length; i++) {
         if (studentList[i].getUserID() == id) {
            student = studentList[i];
         }
      }
      return student;
   }
   public Student findByName(String n) {
      Student student = new Student();
      for (int i = 0; i < studentList.length; i++) {
         System.out.println(studentList[i].getName());
         if (studentList[i].getName().toLowerCase().trim().equals(n.toLowerCase().trim())) {
            student = studentList[i];
         }
      }
      return student;
   }
   public Student[] findByBlock(char block) {
      Student[] studentArray = new Student[studentList.length];
      int b = 0;
      for (int i = 0; i < studentList.length; i++) {
         if (studentList[i].getBlockID() == block) {
            studentArray[b] = studentList[i];
            b++;
         }
      }
      return Arrays.copyOfRange(studentArray,0, b);
   }
   public Student[] findByCourseName(String courseName) {
      Student[] studentArray = new Student[studentList.length];
      int b = 0;
      for (int i = 0; i < studentList.length; i++) {
         if (studentList[i].getCourseName().toLowerCase().trim().equals(courseName.toLowerCase().trim())) {
            studentArray[b] = studentList[i];
            b++;
         }
      }
      return Arrays.copyOfRange(studentArray, 0, b);
   }
   public Student findByBlockAndRoom(char block, int roomNumber) {
      Student student = new Student();
      for (int i = 0; i < studentList.length; i++) {
         if (studentList[i].getBlockID() == block && studentList[i].getRoomNumber() == roomNumber) {
            student = studentList[i];
         }
      }
      return student;
   }

}
