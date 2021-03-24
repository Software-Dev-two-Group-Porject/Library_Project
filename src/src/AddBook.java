import javax.swing.*;

/**
 * Created By Jonathon on 15/03/2021
 * Update Comments About Program Here
 **/
public class AddBook extends JFrame {
    CommonLabel headerLabel, isbnLabel, titleLabel, authorLabel, genreLabel, subGenreLabel, quantityLabel, isbnNumLabel;
    CommonTextField isbnField, titleField, authorField, genreField, subGenreField, quantityField;
    CommonButton buttonAdd, buttonClear;
    Catalog catalog;
    Design design = new Design();
    BookDataPanel bookDataPanel;
    Utils utils = new Utils();

    AddBook(Catalog catalog, BookDataPanel bookDataPanel){
        this.bookDataPanel = bookDataPanel;
        this.catalog = catalog;
        this.setLayout(null);

        headerLabel = new CommonLabel("Add Book", 25);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createMatteBorder(0,0, 2, 0, design.borderGold));
        headerLabel.setBounds(110, 15, 180, 30);
        this.add(headerLabel);

        isbnLabel = new CommonLabel("Isbn:", 15);
        isbnLabel.setBounds(50, 60, 100, 25);
        this.add(isbnLabel);

        isbnNumLabel = new CommonLabel("978-", 15);
        isbnNumLabel.setBounds(50, 95, 55, 30);
        isbnNumLabel.setHorizontalAlignment(JLabel.RIGHT);
        isbnNumLabel.setBorder(BorderFactory.createMatteBorder(1,1,1,0, design.borderColor));
        this.add(isbnNumLabel);

        isbnField = new CommonTextField();
        isbnField.setBounds(105, 95, 160, 30);
        this.add(isbnField);

        titleLabel = new CommonLabel("Title:", 15);
        titleLabel.setBounds(50, 125, 100, 25);
        this.add(titleLabel);

        titleField = new CommonTextField();
        titleField.setBounds(50, 150, 250, 30);
        this.add(titleField);

        authorLabel = new CommonLabel("Author:", 15);
        authorLabel.setBounds(50, 190, 100, 25);
        this.add(authorLabel);

        authorField = new CommonTextField();
        authorField.setBounds(50, 215, 250, 30);
        this.add(authorField);

        genreLabel = new CommonLabel("Genre", 15);
        genreLabel.setBounds(50, 255, 100, 25);
        this.add(genreLabel);

        genreField = new CommonTextField();
        genreField.setBounds(50, 280, 250, 30);
        this.add(genreField);

        subGenreLabel = new CommonLabel("Sub Genre:", 15);
        subGenreLabel.setBounds(50, 320, 100, 25);
        this.add(subGenreLabel);

        subGenreField = new CommonTextField();
        subGenreField.setBounds(50, 345, 250, 30);
        this.add(subGenreField);

        quantityLabel = new CommonLabel("Initial Quantity:", 15);
        quantityLabel.setBounds(50, 395, 150, 25);
        this.add(quantityLabel);

        quantityField = new CommonTextField();
        quantityField.setBounds(200, 395, 100, 30);
        this.add(quantityField);

        buttonAdd = new CommonButton("Add Book", design.btnAddColor, 12);
        buttonAdd.setBounds(50, 440, 130, 40);
        buttonAdd.addActionListener(l -> addBookToCatalog());
        this.add(buttonAdd);

        buttonClear = new CommonButton("Clear", design.tableButtonColor, 13);
        buttonClear.setBounds(200, 440, 130, 40);
        buttonClear.addActionListener(l -> clearTextFields());
        this.add(buttonClear);
        this.getContentPane().setBackground(design.bgColor);
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void addBookToCatalog(){
        String validationMessage = "";
        validationMessage += utils.isbnValidation(isbnField.getText());
        validationMessage += utils.bookValidation(titleField.getText());
        validationMessage += utils.nameValidation("Author", authorField.getText());
        validationMessage += utils.nameValidation("Genre", genreField.getText());
        validationMessage += utils.integerValidation("Quantity",quantityField.getText());

        if(validationMessage.equals("")){
            Book book = new Book("978"+isbnField.getText(), titleField.getText(), authorField.getText());
            book.setGenre(genreField.getText());
            book.setSubGenre(subGenreField.getText());
            book.setQuantity(Integer.parseInt(quantityField.getText()));
            catalog.addBookToCatalog(book);
            catalog.saveData();
            catalog.initializeCatalogue();
        }
        else {
            JOptionPane.showMessageDialog(null, validationMessage);
        }
    }

    public void clearTextFields(){
     isbnField.setText(""); titleField.setText("");
     authorField.setText(""); genreField.setText("");
     subGenreField.setText(""); quantityField.setText(""); 
    }
}
