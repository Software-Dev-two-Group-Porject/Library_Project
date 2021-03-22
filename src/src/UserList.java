import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created By Michael on 18/03/2021
 * Methods for handling the user list and the users.dat file
 **/
public class UserList {
    protected User [] userList;
    Scanner keyboard = new Scanner(System.in);

    UserList(){

    }

    //initUser
    //print header
    //printUser

    //This method opens our user.dat and loads all the users into the array
    public void initUserList() {
        String line = "";
        int i = 0;
        String [] userArray = new String[100];

        try (FileReader file = new FileReader(new File("src\\Data\\users.dat"))) {
            BufferedReader userFile = new BufferedReader(file);

            while((line = userFile.readLine()) != null) {
                userArray[i] = line;
                i++;
            }

        } catch (Exception e) { //error handling
            System.err.format("File does not exist\n");
            e.printStackTrace();
        }

        String [] newUserArray = Arrays.copyOfRange(userArray, 0, i);

        this.userList = new User[newUserArray.length];

        for(int j = 0; j < newUserArray.length; j++) {
            this.userList[j] = User.setUser(newUserArray[j]);
        }

        User.quickSort(this.userList, 0, this.userList.length -1);

    } //initUserList

    public User [] getUserList() { return this.userList; }

    public User getUserByID(int id) {
        User user = new User();
        UserList getUser = new UserList();
        getUser.initUserList();

        for (int i = 0; i < getUser.userList.length; i++) {
            if(getUser.userList[i].getUserID() == id) {
                user = getUser.userList[i];
            }

        } //for

        return user;

    } //getUserByID

    public User getUserByEmail(String email) {
        User returnUser = new User();
        UserList getUser = new UserList();
        getUser.initUserList();
        for (int i = 0; i < getUser.userList.length; i++) {
            if(getUser.userList[i].getEmail().equals(email.trim())) {
                returnUser = getUser.userList[i];
            }
        } //for
        return returnUser;
    } //getUserByEmail

    public void addUserToList(User user) {
        User [] newUserList = new User[userList.length + 1];

        for (int x = 0; x < userList.length; x++) {
            newUserList[x] = userList[x];
        }//for
        newUserList[newUserList.length-1] = user;
        userList = newUserList;

    } //addUserToList

    public void saveUsers() {
        String file = "src\\Data\\users.dat";
        BufferedWriter writer = null;
        String user = "";
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i=0; i < userList.length; i++) {
                user += userList[i].saveStringForUsersDat();

            }//for
            writer.write(user);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } //catch
        } //finally
    } //saveUsers

    public void deleteUser(int userID, int prompt) {
        char delete = 'N';

        for (int x = 0; x < userList.length; x++) {
            if(userList[x].getUserID() == userID) {
                if (prompt != 0) { //Show user info before deleting unless prompts are hidden
                    System.out.println(User.printHeader());
                    System.out.println(userList[x].toString());
                    System.out.println("Please confirm you would like to delete user. (Y/N)");
                    delete = keyboard.nextLine().toUpperCase().charAt(0);
                }
                if ((delete == 'Y') || (prompt == 0)) {
                    User deleteUser = userList[x];
                    userList[x] = userList[0];
                    userList[0] = deleteUser;
                    userList = Arrays.copyOfRange(userList,1,userList.length);
                    if (prompt !=0) { System.out.println("User has been deleted."); }
                } else {
                    System.out.println("User has not been deleted.");
                    break;
                }
            }
        }
    }//deleteUser


   /* public void populateList() { //This already exists in User as initUserList
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
    } */
}

