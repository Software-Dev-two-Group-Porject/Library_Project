import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created By Jonathon on 21/02/2021
 * Update Comments About Program Here
 **/
public class UserList {
    private User [] userList;
    private Student [] studentList;

    UserList(){

    }
    public void populateList() { //This already exists in User as initUserList
        int i = 0;
        String file = "src\\users.dat";
        BufferedReader reader = null;
        String line = "";
        String[] strArr = new String[500];
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                strArr[i] = line;
                i++;
            }

            //using copy of method to create new array of String without the column headers so data can be seperated
            //and placed in setters
            String[] newStrArray = Arrays.copyOfRange(strArr, 1, i);
            //after grabbing from csv file, we need to set a new array, removing the headings
            this.userList = new User[newStrArray.length];//array set to length of existing records
            //here we loop through the String array and set our book object by the values in the csv
            //the order of the csv to the class names.

            for (int j = 0; j < this.userList.length; j++) {
                //this.userList[j] = setBookObject(newStrArray[j]);
            }
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

    public Student setStudentObject(String line){
        Student newStudent = new Student();
        String [] data = line.split(",");
        return newStudent;
    }

    public void setStaffObject(String line){
    }
}

