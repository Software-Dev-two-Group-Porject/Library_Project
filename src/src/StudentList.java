import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created By Jonathon on 05/03/2021
 * Update Comments About Program Here
 **/
public class StudentList {
   private Student [] studentList;


    StudentList(){

    }

    public void populateList(){
        BufferedReader reader = null;
        String file = "src\\Data\\users.dat";
        int s = 0;
        int i = 0;
        String line = "";
        String [] arr = new String [500];
        Student [] stdArr = new Student[arr.length];
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                arr[i] = line;
                String [] data = line.split(",");
                for(int d = 0; d < data.length; d++){
                    System.out.println(d + " " + data[d]);
                }
                if(data[1].toLowerCase().trim().equals("student")){
                    stdArr[s] = new Student(
                            Integer.parseInt(data[0]), //UserID
                            data[1], //User Status
                            data[2], //Username
                            data[3], //email,
                            data[4],//password
                            data[5].toUpperCase().charAt(0), //Student Block Letter
                            Integer.parseInt(data[6]), //Student Room Number.
                            data[8]//the course
                            );
                    s++;
                }
            }

            this.studentList = Arrays.copyOfRange(stdArr, 0, s);
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                reader.close();

            } catch(IOException io){
                io.printStackTrace();
            }
        }
    }

    public Student [] getStudentList(){
        return this.studentList;
    }


   public void printStudentList(){

        for(int i = 0; i < studentList.length; i++){
            System.out.println(studentList[i].toString());
        }
   }

   public Student [] findStudentsByName(String name){
        Student [] returnList = new Student [studentList.length];
        int s = 0;
        for (int i = 0; i < studentList.length; i++){
            if(studentList[i].getName().toLowerCase().trim().contains(name)){
                returnList[s] = studentList[i];
                s++;
            }
        }
        return Arrays.copyOfRange(returnList, 0, s);
   }

   public Student [] findStudentByBlock(char block){
        Student [] returnList = new Student[studentList.length];
        int s = 0;
        for (int i = 0; i < studentList.length; i++){
            if(studentList[i].getBlockLetter() == block){
                returnList[s] = studentList[i];
            }
        }
        return Arrays.copyOfRange(returnList, 0, s);
   }

   public Student findById (int id){
        Student student = null;
        for(int i = 0; i < studentList.length; i++){
            if(studentList[i].getUserID() == id){
                student = studentList[i];
            }
        }
        return student;
   }

   public Student [] findByIdForTable(int id){
        Student [] studentArr = new Student[1];
        studentArr[0] = findById(id);
        return studentArr;
   }


}
