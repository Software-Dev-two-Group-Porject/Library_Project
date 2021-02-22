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
        //Book [] fictionArray = catalogue.getBookByGenre("fiction");
        //catalogue.printList(fictionArray);
        //catalogue.saveData();
        Book testAdd = new Book();
        String isbn = "9781234567890";
        testAdd.setIsbn(isbn);
        testAdd.setAuthor("McCauley, Johnny");
        catalogue.addBookToCatalog(testAdd);
        Book foundBook = catalogue.getBookByIsbn(isbn);
        System.out.println(foundBook.toString());
        //Testing returning and checking out books
        //catalogue.upDateQuantity("9781234567890", "Return");
        //Book foundBookUpdated = catalogue.getBookByIsbn(isbn);
        //System.out.println(foundBookUpdated.toString());
        //Testing book removal
        System.out.println("Test removing book");
        catalogue.removeFromCatalog(isbn);
        Book removedBook = catalogue.getBookByIsbn(isbn);
        System.out.println((removedBook == null));

    }
    //adding comment to check branching and merging
    //second comment here to check that merging from dev to test is working ok.
    //Comment from Michael 4

}
