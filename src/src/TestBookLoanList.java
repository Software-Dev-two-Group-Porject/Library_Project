/**
 * Created By Jonathon on 13/03/2021
 * Update Comments About Program Here
 **/
public class TestBookLoanList {
    public static void main(String [] args){
        BookLoanList bookLoanList = new BookLoanList();
        bookLoanList.populateBookLoanList();
        BookLoan [] testrray = bookLoanList.getBookLoanList();
        for(int i = 0; i < testrray.length; i++){
            System.out.println(testrray[i].toString());
        }
    }
}
