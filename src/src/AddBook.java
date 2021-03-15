import javax.swing.*;

/**
 * Created By Jonathon on 15/03/2021
 * Update Comments About Program Here
 **/
public class AddBook extends JFrame {
    CommonLabel headerLabel, isbnLabel, titleLabel, authorLabel, genreLabel, subGenreLabel, quantityLabel;
    CommonTextField isbnField, titleField, authorField, genreField, subGenreField, quantityField;
    CommonButton buttonAdd, buttonClear;
    Catalog catalog;
    Book book;
    Design design = new Design();


    AddBook(Catalog catalog){
        this.catalog = catalog;
        this.setLayout(null);

        headerLabel = new CommonLabel("Add Book", 25);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBounds(50, 10, 200, 30);
        this.add(headerLabel);

        isbnLabel = new CommonLabel("Isbn", 15);
        isbnLabel.setBounds(50, 50, 100, 30);
        this.add(isbnLabel);

        isbnField = new CommonTextField();
        isbnField.setBounds(50, 80, 200, 30);
        this.add(isbnField);

        titleLabel = new CommonLabel("Title", 15);
        titleLabel.setBounds(50, 130, 100, 30);
        this.add(titleLabel);

        titleField = new CommonTextField();
        this.setBounds(50, 160, 200, 30);
        this.add(titleField);

        authorLabel = new CommonLabel("Author", 15);
        authorLabel.setBounds(50, 210, 100, 30);
        this.add(authorLabel);

        authorField = new CommonTextField();
        authorField.setBounds(50, 240, 200, 30);
        this.add(authorField);

        genreLabel = new CommonLabel("Genre", 15);
        genreLabel.setBounds(50, 280, 100, 30);
        this.add(genreLabel);

        genreField = new CommonTextField();
        genreField.setBounds(50, 310, 200, 30);
        this.add(genreField);

        subGenreLabel = new CommonLabel("Sub Genre", 15);
        subGenreLabel.setBounds(50, 340, 100, 30);
        this.add(subGenreLabel);

        subGenreField = new CommonTextField();
        subGenreField.setBounds(50, 370, 200, 30);
        this.add(subGenreField);

        quantityLabel = new CommonLabel("Initial Quantity:", 15);
        quantityLabel.setBounds(50, 420, 100, 30);
        this.add(quantityLabel);

        buttonAdd = new CommonButton("Add Book", design.btnAddColor, 13);
        buttonAdd.setBounds(50, 470, 200, 40);
        buttonAdd.addActionListener(l -> addBookToCatalog());
        this.add(buttonAdd);

        this.getContentPane().setBackground(design.bgColor);
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addBookToCatalog(){
        Book book = new Book();

    }
}
