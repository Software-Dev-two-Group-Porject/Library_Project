/**
 * Created By Jonathon on 12/03/2021
 * Update Comments About Program Here
 **/
public class TestCatalog {

    public static void main(String [] args){
        //Testing search and save and update methods

        //Test Case 1
        Book book = new Book();
        book.setIsbn("9780132350884");
        book.setTitle("Clean Code");
        book.setAuthor("Martin, Robert C");
        book.setGenre("Tech");
        book.setSubGenre("computer science");
        book.setQuantity(3);

        System.out.println("Test Case 1");
        System.out.println(book.toString());

        //Test Case 2
        System.out.println("Test Case 2");
        Catalogue catalogue = new Catalogue();
        catalogue.initializeCatalogue();
        Book [] bookArr = catalogue.getCatalogueList();
        catalogue.printList(bookArr);


        //Test Case 3 search by isbn
        System.out.println("Test Case 3");
        Book test = catalogue.getBookByIsbn("9780132350884");
        System.out.println(test.toString());

        //Test Case 4 search by author
        System.out.println("Test case 4");
        Book [] authorList = catalogue.getBookByAuthor("Larsson");
        catalogue.printList(authorList);

        //Test Case 5 search by title
        System.out.println("Test case 5");
        Book [] titleList = catalogue.getBooksByTitle("data");
        catalogue.printList(titleList);

        System.out.println("Test Case 6");
        Book [] genrelist = catalogue.getBookByGenre("fiction");
        catalogue.printList(genrelist);

        System.out.println("Test 7");
        Book bookTest7 = new Book();
        bookTest7.setIsbn("9780132350884");
        bookTest7.setTitle("Clean Code");
        bookTest7.setAuthor("Martin, Robert C");
        bookTest7.setGenre("Tech");
        bookTest7.setSubGenre("computer science");
        bookTest7.setQuantity(3);
        catalogue.addBookToCatalog(bookTest7);

        Book findBookTest7 = catalogue.getBookByIsbn("9780132350884");
        System.out.println("Test 7 retrieved");
        System.out.println(findBookTest7.toString());


        //Test 8
        catalogue.removeFromCatalog("9780132350884");
        Book findBookTest8 = catalogue.getBookByIsbn("9780132350884");
        System.out.println(findBookTest8.toString());

        //Test 9
        catalogue.addBookToCatalog(findBookTest7);
        catalogue.saveData();


    }
}
