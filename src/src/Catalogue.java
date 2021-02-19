import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created By Jonathon on 18/02/2021
 * Update Comments About Program Here
 **/
public class Catalogue {
    private Book [] catalogueList = new Book[500];

    Catalogue(){

    }

    Catalogue(Book [] bookArray){
        this.catalogueList = bookArray;
    }


    //used as a easier test method to check that data is being displayed correctly. 
    public void printList(){
        int i = 0;
        while(i < this.catalogueList.length){
            System.out.println(catalogueList[i].toString());
            i++;
        }
    }


    //This method will open and read our csv file and populate our book array
    //to do this we will create a new book object and then set a value in the array to that object.
    public void initializeCatalogue(){
        String file = "src\\BookList.csv";
        BufferedReader reader = null;
        int i = 0;
        String line ="";
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String row [] = line.split(",");
                System.out.println(row.length);
                if(i > 0){

                }
                i++;
            }

            System.out.println(i);

        } catch (Exception e){
          e.printStackTrace();
        }
        finally{
           try{
               reader.close();
           } catch(IOException io){
               io.printStackTrace();
           }
        }
    }


}
