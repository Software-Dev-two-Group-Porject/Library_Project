/**
 * Created By Jonathon on 18/02/2021
 * Update Comments About Program Here
 **/
public class Book {

    private String isbn, loanType, availability;
    private String author, title;
    private String categories [];

    //Default constuctor
    Book(String isbn, String author, String title){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }


    public String toString(){
        return String.format("%s,   %s,   %s   ", this.isbn, this.author, this.title);
    }

}
