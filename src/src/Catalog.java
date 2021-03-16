import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created By Jonathon on 18/02/2021
 * Update Comments About Program Here
 **/

//implementing serializable so that data object can be easiest saved.
public class Catalog {
    static Scanner input = new Scanner(System.in);
    private Book[] catalogueList;
    int count;

    Catalog(){
    }


    //used as a easier test method to check that data is being displayed correctly. 
    public void printList(Book [] bookList){
        System.out.println("\t\t\tBOOK CATALOGUE");
        System.out.println(printHeader());
        for(int i = 0; i < bookList.length; i++){
            System.out.println(bookList[i].toString());
        }
    }

    private String printHeader(){
        return String.format("%-15s\t%-20s\t%-60s\t%-20s\t%-20s\t%s", "ISBN", "Author", "Title", "Genre", "Sub Genre", "Quantity", "Priority");
    }

    //This method will open and read our csv file and populate our book array
    //to do this we will create a new book object and then set a value in the array to that object.
    public void initializeCatalogue() {
        count = 0;
        String file = "src\\Data\\Book_Data.csv";
        BufferedReader reader = null;
        String line = "";
        String [] strArr = new String [1000];
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                strArr[count] = line;
                count++;
            }

            //using copy of method to create new array of String without the column headers so data can be seperated
            //and placed in setters
            String [] newStrArray = Arrays.copyOfRange(strArr, 1, count);
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
        int quantity;
        Book book = new Book();
        String [] row = line.split("#");
        book.setIsbn(row[0].trim());
        book.setTitle(row[1].trim());
        book.setAuthor(row[2].trim());
        book.setGenre(row[3].trim());
        book.setSubGenre(row[4].trim());
        quantity = Integer.parseInt(row[5].trim());
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
        count = 0;
        Book [] returnList = new Book[this.catalogueList.length];
        for(int i = 0; i  < this.catalogueList.length; i++){
            if(genre.toLowerCase().trim().equals(this.catalogueList[i].getGenre().toLowerCase().trim())){
                Book book = this.catalogueList[i];
                returnList[count] = book;
                count++;
            }
        }

        //using copy of to get an array with no null vals from our existing array
        Book [] bookList = Arrays.copyOfRange(returnList, 0, count);

        return bookList;
    }

    public Book [] getBookByAuthor(String author){
        Book [] returnList = new Book[catalogueList.length];
        count =0;
        for(int i = 0; i < catalogueList.length; i++){
            if(catalogueList[i].getAuthor().toLowerCase().contains(author.trim().toLowerCase())){
                returnList[count] = catalogueList[i];
                count++;
            }
        }

        return Arrays.copyOfRange(returnList, 0, count);
    }

    public Book[] getBooksByTitle(String title){
        Book [] returnList = new Book [catalogueList.length];
        count = 0;
        for(int i = 0; i < catalogueList.length; i++){
            if(catalogueList[i].getTitle().toLowerCase().contains(title.toLowerCase().trim())){
                catalogueList[i].toString();
                returnList[count] = catalogueList[i];
                count++;
            }
        }

        return Arrays.copyOfRange(returnList, 0, count);
    }

    //using this method to re write the cv we have in more readable format and when books are added/deleted and quanities
    //are changed, data can be re written to this file here.
    public void saveData(){
        String file = "src\\Data\\Book_Data.csv";
        BufferedWriter writer = null;
        String line = "";
        try{
            writer = new BufferedWriter( new FileWriter(file));
            for(int i = 0; i < catalogueList.length; i++){
               line += catalogueList[i].saveStringForCSV();
            }
            writer.write(line);
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {

            try {
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    public Book [] getBookByIsbnForTable(String isbn){
        Book [] bookArr = new Book[1];
        bookArr[0] = getBookByIsbn(isbn);
        return bookArr;
    }

    public void addBookToCatalog(Book book){
        //we need to extend the size of our array by one and add the book to that
        //have our catalog list copied with that new element included.
        //creating
        Book [] newArray = new Book[catalogueList.length +1];
        for(int i = 0; i < catalogueList.length; i++){
            newArray[i] = catalogueList[i];
        }
        newArray[newArray.length -1] = book;
        catalogueList = newArray;
    }

    public void upDateQuantity(String isbn, String operation){
        Book book = null;
        count = 0;
       //here we are going to increment and decrement the book value depending on the operation
        for(int i = 0; i < catalogueList.length; i++) {
            if (catalogueList[i].getIsbn().trim().equals(isbn.trim())) {
                //this ensure's we have the right book
                //check that the quantity is correct.
                book = catalogueList[i];
                count = i;
            }
        }
                if(book != null){
                if(book.getQuantity() > 0 && operation == "checkout"){
                    book.setQuantity(book.getQuantity() -1);
                } else if (operation == "return"){
                    book.setQuantity(book.getQuantity() + 1);
                } else {
                    System.out.println("You can't check out from the an empty title");
                }
                catalogueList[count] = book;
            } else {
                System.out.println("Book record not found");
            }
    }

    public void removeFromCatalog(String isbn){
        char response;
        //The goal of this method will be to search for the element
        //swap it with the first element in the array
        //use the Arrays.copy method to return array that no longer contains that element.
         for(int i = 0; i < catalogueList.length; i++){
             if(catalogueList[i].getIsbn().equals(isbn.trim())){
                 System.out.println("Record Found");
                 System.out.println(printHeader());
                 System.out.println(catalogueList[i].toString());
                 System.out.println("\n\nA record has been found, would you like to continue? Y/N");
                 response = input.nextLine().toUpperCase().charAt(0);
                 if(response == 'Y') {
                     Book removeBook = catalogueList[i];
                     catalogueList[i] = catalogueList[0];
                     catalogueList[0] = removeBook;

                     catalogueList = Arrays.copyOfRange(catalogueList, 1, catalogueList.length);
                 } else {
                     System.out.println("You have chose not to delete the record");
                     break;
                 }
             }
         }
    }
    
}
