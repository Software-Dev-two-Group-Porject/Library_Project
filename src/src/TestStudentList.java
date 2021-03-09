/**
 * Created by Alisha on 09/03/2021
 * COMMENTS ABOUT PROGRAM HERE
 */
public class TestStudentList {
   public static void main(String[] args) {
      StudentList studentList = new StudentList();

      studentList.populateList();

      Student student;
      student = studentList.findByName("Penny Teller");
      System.out.println(student.toString());
   }//main
}//class