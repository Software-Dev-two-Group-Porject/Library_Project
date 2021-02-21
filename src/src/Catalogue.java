import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created By Jonathon on 18/02/2021
 * Update Comments About Program Here
 **/

//implementing serializable so that data object can be easiest saved.
public class Catalogue {
    private Book[] catalogueList;

    Catalogue(){
    }


    //used as a easier test method to check that data is being displayed correctly. 
    public void printList(Book [] bookList){
        System.out.println("\t\t\tBOOK CATALOGUE");
        String header = String.format("%-15s\t%-20s\t%-60s\t%-20s\t%-20s\t%s", "ISBN", "Author", "Title", "Genre", "Sub Genre", "Quantity");
        System.out.println(header);
        for(int i = 0; i < bookList.length; i++){
            System.out.println(bookList[i].toString());
        }
    }

    //This method will open and read our csv file and populate our book array
    //to do this we will create a new book object and then set a value in the array to that object.
    public void initializeCatalogue() {
        int i = 0;
        String file = "src\\Book_Data.csv";
        BufferedReader reader = null;
        String line = "";
        String [] strArr = new String [500];
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                strArr[i] = line;
                i++;
            }

            //using copy of method to create new array of String without the column headers so data can be seperated
            //and placed in setters
            String [] newStrArray = Arrays.copyOfRange(strArr, 1, i);
            //after grabbing from csv file, we need to set a new array, removing the headings
            this.catalogueList = new Book[newStrArray.length];//array set to length of existing records
            //here we loop through the String array and set our book object by the values in the csv
            //the order of the csv to the class names.

            for(int j = 0; j < this.catalogueList.length; j++){
                this.catalogueList[j] = setBookObject(newStrArray[j]);
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

    public Book[] getCatalogueList() {
        return this.catalogueList;
    }

    //set to private as method will only be used within this class.
    private Book setBookObject(String line){
        int isbn, quantity;
        Book book = new Book();
        String [] row = line.split("#");
        book.setTitle(row[0]);
        book.setAuthor(row[1]);
        book.setGenre(row[2]);
        book.setSubGenre(row[5]);
        book.setIsbn(row[6]);
        quantity = Integer.parseInt(row[10]);
        book.setQuantity(quantity);
        return book;
    }

    public Book getBookByIsbn(String isbn){
        Book returnBook = new Book();
        for(int i = 0; i< this.catalogueList.length; i++){
            if(this.catalogueList[i].getIsbn().equals(isbn.trim())){
                returnBook = this.catalogueList[i];
            }
        }
        return returnBook;
    }

    public Book [] getBookByGenre(String genre){
        int j =0;
        Book [] returnList = new Book[this.catalogueList.length];
        for(int i = 0; i  < this.catalogueList.length; i++){
            if(genre.toLowerCase().trim().equals(this.catalogueList[i].getGenre().toLowerCase().trim())){
                Book book = this.catalogueList[i];
                returnList[j] = book;
                j++;
            }
        }

        //using copy of to get an array with no null vals from our existing array
        Book [] bookList = Arrays.copyOfRange(returnList, 0, j);

        return bookList;
    }
    
}
