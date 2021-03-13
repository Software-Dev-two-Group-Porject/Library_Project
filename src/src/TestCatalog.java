/**
 * Created By Jonathon on 12/03/2021
 * Update Comments About Program Here
 **/
public class TestCatalog {

    public static void main(String [] args){
        //Testing search and save and update methods

        Catalogue catalogue = new Catalogue();
        catalogue.initializeCatalogue();
        Book [] bookArr = catalogue.getCatalogueList();


        //Test 10
        catalogue.getBookByIsbn("9780132350884");
        catalogue.upDateQuantity("9780132350884", "checkout");
        Book book = catalogue.getBookByIsbn("9780132350884");
        System.out.println(book.toString());
        catalogue.saveData();
    }
}
