import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Created By Jonathon on 21/02/2021
 * Update Comments About Program Here
 **/
public class BookLoanList {

    private BookLoan [] bookLoans;
    private String file = "src\\Data\\BookLoanList.csv";
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int count;
    static Scanner keyboard = new Scanner(System.in); //added RM- temp

    BookLoanList(){

    }

    public void populateBookLoanList(){
        BufferedReader reader = null;
        int i = 0;
        String line = "";
        String [] str = new String[500];
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                str[i] = line;
                i++;
            }

            String [] newStringArr = Arrays.copyOfRange(str, 1, i); //going from to elim the header;
            bookLoans = new BookLoan[newStringArr.length];
            for(i =0; i < newStringArr.length; i++){
                bookLoans[i] = setBookLoan(newStringArr[i]);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    public BookLoan setBookLoan(String line){
        BookLoan bookLoan = new BookLoan();
        System.out.println(line);
        try {
            String [] data = line.split(",");
            bookLoan.setISBN(data[0]);
            bookLoan.setUserID(Integer.parseInt(data[1]));
            bookLoan.setDateIssued(formatter.parse(data[2]));
            bookLoan.setStatus(data[3]);
            bookLoan.setDueDate(formatter.parse(data[4]));
            bookLoan.setDateReturned(formatter.parse(data[5]));
            bookLoan.setOverdue(false);
            bookLoan.setFinePerDay(2.50);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return bookLoan;
    }

    public BookLoan [] getBookLoanList() {
        return this.bookLoans;
    }

    public BookLoan getBookloanByIsbnAndUserId(String isbn, int id){
        BookLoan returnBookLoan = new BookLoan();
        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].getISBN().equals(isbn) && bookLoans[i].getUserID() == id){
                returnBookLoan = bookLoans[i];
            }
        }
        return returnBookLoan;
    }

    public BookLoan [] getBookLoansByIsbn(String isbn){
        count = 0;
        BookLoan [] returnBookLoanList = new BookLoan[bookLoans.length];

        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].getISBN().equals(isbn.trim())){
                returnBookLoanList[count] = bookLoans[i];
                count++;
            }
        }

        return Arrays.copyOfRange(returnBookLoanList, 0, count);
    }

    public BookLoan[] getBookLoansByUserId(int id){
        count = 0;
        BookLoan [] returnBookList = new BookLoan[bookLoans.length];

        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].getUserID() == id){
                returnBookList[count] = bookLoans[i];
                count++;
            }
        }

        return Arrays.copyOfRange(returnBookList, 0, count);
    }

    //Copied from Catalog to edit
    //Method to remove book from BookLoan List if collected/requested/delivered
    public void removeFromBookLoanList(String isbn, int userid){
        char response;
        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].getISBN().equals(isbn.trim())){   //do I need ID looped through too?
                System.out.println("Record Found");
                System.out.println(isbn); //printHeader()?
                System.out.println(bookLoans[i].toString());
                System.out.println("\n\nA record has been found, would you like to continue? Y/N");
                response = keyboard.nextLine().toUpperCase().charAt(0);
                if(response == 'Y') {
                    BookLoan removeBook = bookLoans[i];
                    bookLoans[i] = bookLoans[0];
                    bookLoans[0] = removeBook;

                    bookLoans = Arrays.copyOfRange(bookLoans, 1, bookLoans.length); //returns new array with item deleted
                } else {
                    System.out.println("You have chose not to delete the record");
                    break;
                }
            }
        }
    }


    //AddBookLoanlist method
    public void addToBookLoanList(BookLoan bkloan, int userid){
        BookLoan [] newArray = new BookLoan[bookLoans.length + 1]; //create new array adding 1 new index for new book
        for(int i = 0; i < bookLoans.length; i++){
            newArray[i] = bookLoans[i];
        }
        newArray[newArray.length -1] = bkloan;
        bookLoans = newArray;
    }

    /**- code with errors - UpdateStatusMethod
     //Update Status Method - Update - find object, update (and set value to it)
     //change status to reflect status of book (Collected, Requested, Delivered)
     **/
    public void updateStatus(String isbn, int userid, String status) {
        for (int i = 0; i < bookLoans.length; i++) {
            if (bookLoans[i].getISBN().trim().equals(isbn.trim()) && bookLoans[i].getUserID() == userid) {
                bookLoans[i].setStatus(status);
                switch(status){
                    case "delivered":
                        bookLoans[i].setDateIssued(new Date());
                        bookLoans[i].setDueDate(new Date());
                        break;
                    case "ready":
                        bookLoans[i].setDateReturned(new Date());
                        break;
                    default:
                        break;
                }
            }
        }

    }
    public int getTotalRequests(){
        int count = 0;
        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].status.equals("requested")){
                count++;
            }
        }

        return count;
    }

    public int getTotalApproved(){
        int count = 0;
        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].status.equals("approved")){
                count++;
            }
        }

        return count;
    }

    public int getTotalReady(){
        int count = 0;
        for(int i = 0; i < bookLoans.length; i++){
            if(bookLoans[i].status.equals("ready")){
                count++;
            }
        }
        return count;
    }


}
