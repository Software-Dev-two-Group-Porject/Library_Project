/**
 * Created By Jonathon on 15/02/2021
 * Update Comments About Program Here
 **/
public class Main {
    public static void main(String [] args){
        System.out.println("Hello World!");
        Book [] bookArray = new Book[1000];
        Catalogue catalogue = new Catalogue(bookArray);
        catalogue.initializeCatalogue();
        catalogue.printList();
    }
    //adding comment to check branching and merging
    //second comment here to check that merging from dev to test is working ok.
    //Comment from Michael 4

}
