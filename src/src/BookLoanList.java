import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created By Jonathon on 21/02/2021
 * Update Comments About Program Here
 **/
public class BookLoanList {

    private BookLoan [] bookLoans;
    private String file = "src\\Data\\BookLoanList.csv";
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int count;

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
            bookLoan.setDueDate(formatter.parse(data[3]));
            bookLoan.setDateReturned(formatter.parse(data[4]));
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

    public void addToBookLoanList(BookLoan bkloan){

    }


}
