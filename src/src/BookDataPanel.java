import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created By Jonathon on 11/03/2021
 * Update Comments About Program Here
 **/
public class BookDataPanel extends JPanel {
    Design design = new Design();
    CommonButton addBook, searchButton, bookLoanStatusButton, viewAllButton;
    CommonLabel searchBy, labelCategory, tableHeader;
    JComboBox<String> searchByBox;
    CommonTextField searchTextCategory;
    JPanel mainContainer, topComponentContainer, dataContainer, dataOutput;
    StaffPanel staffPanel;
    StudentPanel studentPanel;
    Catalog catalog;

    BookDataPanel(StudentPanel studentPanel, StaffPanel staffPanel){
        this.setLayout(null);
        this.staffPanel = staffPanel;
        this.studentPanel = studentPanel;

        catalog = new Catalog();
        catalog.initializeCatalogue();

        mainContainer = new JPanel();

        topComponentContainer = new JPanel();
        topComponentContainer.setLayout(null);
        topComponentContainer.setBackground(design.bgColor);
        topComponentContainer.setBorder(BorderFactory.createLineBorder(design.borderColor, 1));
        topComponentContainer.setBounds(0,0, 900, 40);

        addBook = new CommonButton("Add Book", design.btnAddColor, 9);
        addBook.setBounds(10, 10, 85,25);
        addBook.addActionListener(l -> openAddEditBook(""));
        topComponentContainer.add(addBook);

        viewAllButton = new CommonButton("View All", design.tableButtonColor, 9);
        viewAllButton.setBounds(105, 10, 85, 25);
        viewAllButton.addActionListener(l -> renderAll(catalog.getCatalogueList()));
        topComponentContainer.add(viewAllButton);

        searchBy = new CommonLabel("Search By: ", 13);
        searchBy.setBounds(280, 10, 90, 30);
        topComponentContainer.add(searchBy);

        String [] options = {"Isbn", "Title", "Author", "Genre", "Sub Genre"};
        searchByBox = new JComboBox<String>(options);
        searchByBox.setBounds(360, 10, 80, 25);
        searchByBox.setSelectedIndex(0);
        searchByBox.addActionListener(this::setSearchCriteria);
        topComponentContainer.add(searchByBox);

        labelCategory = new CommonLabel( "Isbn:", 13);
        labelCategory.setBounds(445, 10, 55, 30);
        labelCategory.setHorizontalAlignment(JLabel.RIGHT);
        topComponentContainer.add(labelCategory);

        searchTextCategory = new CommonTextField();
        searchTextCategory.setBounds(505, 10, 200, 25);
        topComponentContainer.add(searchTextCategory);

        searchButton = new CommonButton("Search", design.tableButtonColor, 9);
        searchButton.setBounds(710, 10, 70, 25);
        searchButton.addActionListener(e -> filterBooks());
        topComponentContainer.add(searchButton);
        mainContainer = new JPanel(new BorderLayout());

        dataContainer = new JPanel();
        dataContainer.setLayout(new BorderLayout());
        dataContainer.setBackground(design.bgColor);
        dataOutput = new JPanel();

        mainContainer.add(dataContainer, BorderLayout.CENTER);
        tableHeader = new CommonLabel(getTableHeader(), 10);
        tableHeader.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        dataContainer.add(tableHeader, BorderLayout.NORTH);

        dataOutput.setLayout(new GridBagLayout());
        JScrollPane jScrollPane = new JScrollPane(dataOutput);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataContainer.add(jScrollPane, BorderLayout.CENTER);
        mainContainer.setBounds(0, 40, 900, 210);

        this.add(topComponentContainer);
        this.add(mainContainer);

    }


    public TableButton [] getDataTableViewButtons(Book [] arr){
        TableButton [] viewBtns = new TableButton[arr.length];
        for(int i = 0; i < viewBtns.length; i++){
            viewBtns[i] = new TableButton("", design.tableButtonColor, "View");
            viewBtns[i].setId(arr[i].getIsbn());
        }
        return viewBtns;
    }

    public String getTableHeader(){
        return "<html><tr>" +
                "<td style='padding-right:55px;'>ISBN</td>" +
                "<td style='padding-right:225px;'>Title</td>" +
                "<td style='padding-right:80px;'>Author</td>" +
                "<td style='padding-right:55px;'>Topic</td>" +
                "<td style='padding-right:40px;'>Sub-Topic</td>" +
                "<td style='padding-right:10px;'>Quantity</td>"
                +
                "</tr></html>";
    }

    public void openAddEditBook(String isbn){
        AddBook addBook = new AddBook(catalog, this);


    }

    public void renderAll(Book [] arr){

    }

    public void setSearchCriteria(ActionEvent e){
        labelCategory.setText(searchByBox.getItemAt(searchByBox.getSelectedIndex())+":");
    }

    public void filterBooks(){
        Book [] arr = null;
        switch(searchByBox.getItemAt(searchByBox.getSelectedIndex()).toLowerCase()){
            case "isbn":
                //get by isbn
                arr = catalog.getBookByIsbnForTable(searchTextCategory.getText());
                break;
            case "title":
                arr = catalog.getBooksByTitle(searchTextCategory.getText());
                //perform search to get title
                break;
            case "author":
                arr = catalog.getBookByAuthor(searchTextCategory.getText());
                break;
            case "genre":
                arr = catalog.getBookByGenre(searchTextCategory.getText());
                break;
            case "sub genre":
                arr = catalog.getBookByGenre(searchTextCategory.getText());
                break;
            default:
                break;
        }

        if(arr != null) {
            renderTable(arr);
        }
        else{
            JOptionPane.showMessageDialog(null,
                    String.format("Your search by %s has returned no results",
                            searchByBox.getItemAt(searchByBox.getSelectedIndex())));
        }
    }

    public void renderTable(Book[] arr){

        dataOutput.removeAll();
        dataOutput.revalidate();
        dataOutput.repaint();

        JLabel isbn, title, author, genre, sub_genre, loantype, quantity;
        TableButton [] vb = getDataTableViewButtons(arr);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,0,10);
        for(int i =0; i < arr.length; i++){
            isbn = new JLabel(arr[i].getIsbn(), JLabel.LEFT);
            gbc.gridx = 0; gbc.gridwidth = 2; gbc.weightx = 0;
            gbc.gridy= i;
            dataOutput.add(isbn, gbc);

            title = new JLabel(arr[i].getTitle(), JLabel.LEFT);
            gbc.gridx = 2; gbc.gridwidth = 3; gbc.gridy = i; gbc.weightx = 5;
            dataOutput.add(title, gbc);

            author = new JLabel(arr[i].getAuthor(), JLabel.LEFT);
            gbc.gridx =6;  gbc.gridwidth = 3; gbc.gridy = i; gbc.weightx =4;
            dataOutput.add(author, gbc);

            genre = new JLabel(arr[i].getGenre(), JLabel.LEFT);
            gbc.gridx= 10; gbc.gridwidth = 3; gbc.gridy = i; gbc.weightx = 3;
            dataOutput.add(genre, gbc);

            sub_genre = new JLabel(arr[i].getSubGenre(), JLabel.LEFT);
            gbc.gridx = 13; gbc.gridwidth = 3; gbc.gridy = i; gbc.weightx =3;
            dataOutput.add(sub_genre, gbc);

            quantity = new JLabel(String.valueOf(arr[i].getQuantity()), JLabel.LEFT);
            gbc.gridx = 20; gbc.gridwidth = 3; gbc.gridy = i; gbc.weightx =3;
            dataOutput.add(quantity, gbc);

            gbc.gridx = 23; gbc.gridwidth = 2; gbc.gridy=i; gbc.weightx = 0;
            dataOutput.add(vb[i], gbc);
        }
    }

    public void displayAllBookData(){
        renderTable(catalog.getCatalogueList());
    }
}
