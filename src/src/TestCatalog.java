/**
 * Created By Jonathon on 12/03/2021
 * Update Comments About Program Here
 **/
public class TestCatalog {

    public static void main(String [] args){
        //Testing search and save and update methods

        Catalog catalog = new Catalog();
        catalog.initializeCatalogue();
        Book [] bookArr = catalog.getCatalogueList();


        //Test 10
        System.out.println(catalog.getBookByIsbn("9780132350884").toString());
        catalog.upDateQuantity("9780132350884", "checkout");
        catalog.saveData();
        catalog.initializeCatalogue();
        catalog.removeFromCatalog("9780132350884");
        catalog.saveData();
        catalog.initializeCatalogue();
        System.out.println(catalog.getBookByIsbn("9780132350884").toString());


    }
}
