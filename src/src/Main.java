/**
 * Created By Jonathon on 15/02/2021
 * Update Comments About Program Here
 **/
public class Main {
    public static void main(String [] args){
        System.out.println("Hello World!");
        Catalogue catalogue = new Catalogue();
        catalogue.initializeCatalogue();
        //Book [] bookArray = catalogue.getCatalogueList();
        //catalogue.printList(bookArray);
        Book book = new Book();
        book = catalogue.getBookByIsbn("9787995994348");
        System.out.println("Book Found!");
        System.out.println(book.toString());
        Book [] fictionArray = catalogue.getBookByGenre("fiction");
        catalogue.printList(fictionArray);
    }
    //adding comment to check branching and merging
    //second comment here to check that merging from dev to test is working ok.
    //Comment from Michael 4

}
