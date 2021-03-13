/**
 * Created By Jonathon on 18/02/2021
 * Update Comments About Program Here
 **/
public class Book {

    private int loanType, quantity;
    private String author, title, isbn;
    private String genre, subGenre;

    //Default constuctor

    Book(){

    }

    //test constructor
    Book(String isbn, String author, String title){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    //getters and setters
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getSubGenre(){
        return this.subGenre;
    }

    public void setSubGenre(String subGenre){
        this.subGenre = subGenre;
    }

    public String toString(){
        return String.format("%s\t%-20s\t%-60s\t%-20s\t%-20s\t%d",
                this.isbn, this.author, this.title, this.genre, this.subGenre, this.quantity);
    }

    public String saveStringForCSV(){
        return String.format("%s\t#%-40s\t#%-60s\t#%-20s\t#%-20s\t#%d\n",
                this.isbn, this.author, this.title, this.genre, this.subGenre, this.quantity);
    }

}
