import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentDataPanel extends JPanel {
    Design design = new Design();
    StudentList studentList;
    JPanel topComponentPanel, mainContainer, dataContainer, dataOutput;
    JComboBox<String> searchByBox;
    CommonLabel labelCategory, searchBy, tableHeader;
    CommonTextField searchTextCategory;
    CommonButton searchButton, addStudentButton, viewAllButton;
    AddEditStudent addEditStudent;
    StaffPanel staffPanel;

    StudentDataPanel(StaffPanel staffPanel){
        //setting to null
        this.setLayout(null);
        studentList = new StudentList();
        studentList.populateList();
        this.staffPanel = staffPanel;


        topComponentPanel = new JPanel();
        topComponentPanel.setBackground(design.bgColor);
        topComponentPanel.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        topComponentPanel.setLayout(null);
        topComponentPanel.setBounds(0, 0, 900, 40);

        addStudentButton =  new CommonButton("Add Student", design.btnAddColor, 9);
        addStudentButton.addActionListener(e -> openAddEditTab(""));
        addStudentButton.setBounds(10, 10, 85, 25);
        topComponentPanel.add(addStudentButton);

        viewAllButton = new CommonButton("View All", design.tableButtonColor, 9);
        viewAllButton.addActionListener(e -> displayAllTableData());
        viewAllButton.setBounds(105, 10, 85, 25);
        viewAllButton.setVisible(false);
        topComponentPanel.add(viewAllButton);

        searchBy = new CommonLabel("Search By: ", 13);
        searchBy.setBounds(280, 10, 90, 30);
        topComponentPanel.add(searchBy);

        String [] options = {"ID", "Name", "Block", "Course"};
        searchByBox = new JComboBox<String>(options);
        searchByBox.setBounds(360, 10, 80, 25);
        searchByBox.setSelectedIndex(0);
        searchByBox.addActionListener(this::setSearchCriteria);
        topComponentPanel.add(searchByBox);

        labelCategory = new CommonLabel( "ID:", 13);
        labelCategory.setBounds(445, 10, 55, 30);
        labelCategory.setHorizontalAlignment(JLabel.RIGHT);
        topComponentPanel.add(labelCategory);

        searchTextCategory = new CommonTextField();
        searchTextCategory.setBounds(505, 10, 200, 25);
        topComponentPanel.add(searchTextCategory);

        searchButton = new CommonButton("Search", design.tableButtonColor, 9);
        searchButton.setBounds(710, 10, 70, 25);
        searchButton.addActionListener(e -> filterStudent());
        topComponentPanel.add(searchButton);

        mainContainer = new JPanel(new BorderLayout());

        dataContainer = new JPanel();
        dataContainer.setLayout(new BorderLayout());
        dataContainer.setBackground(design.bgColor);
        dataOutput = new JPanel();

        mainContainer.add(dataContainer, BorderLayout.CENTER);
        tableHeader = new CommonLabel(getTableHeader(), 15);
        tableHeader.setBorder(BorderFactory.createLineBorder(design.borderColor, 2));
        dataContainer.add(tableHeader, BorderLayout.NORTH);

        dataOutput.setLayout(new GridBagLayout());
        renderTable(studentList.getStudentList());

        studentList.printStudentList();
        JScrollPane jScrollPane = new JScrollPane(dataOutput);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dataContainer.add(jScrollPane, BorderLayout.CENTER);


        mainContainer.setBounds(0, 40, 900, 210);

        this.add(topComponentPanel);
        this.add(mainContainer);

    }

    public void filterStudent(){
        String comboString, textInput;
        comboString = searchByBox.getItemAt(searchByBox.getSelectedIndex()).toLowerCase().trim();
        JOptionPane.showMessageDialog(null, comboString);
        Student [] arr;
        //the following switch statement will determine the validation and method params to re render the data table.
        switch(comboString){
            case "id":
                arr = studentList.findByIdForTable(Integer.parseInt(searchTextCategory.getText()));
                renderTable(arr);
                break;
            case "block":
                arr = studentList.findStudentByBlock(searchTextCategory.getText().toUpperCase().charAt(0));
                renderTable(arr);
                break;
            case "name":
                arr = studentList.findStudentsByName(searchTextCategory.getText().toLowerCase().trim());
                renderTable(arr);
            default:
                break;
        }

        viewAllButton.setVisible(true);

    }



    public TableButton [] getDataTableViewButtons(Student [] arr){
        TableButton [] viewBtns = new TableButton[arr.length];
        for(int i = 0; i < viewBtns.length; i++){
            viewBtns[i] = new TableButton("", design.tableButtonColor, "View");
            viewBtns[i].setId(String.valueOf(arr[i].getUserID()));
            String text  = viewBtns[i].getId();
            viewBtns[i].addActionListener(e -> somethingHappening(e, text));
        }
        return viewBtns;
    }

    public TableButton [] getTableEditButtons(Student [] arr){
        TableButton [] editButtons = new TableButton[arr.length];
        for(int i = 0; i < editButtons.length; i++){
            editButtons[i] = new TableButton(String.valueOf(arr[i].getUserID()), design.btnWarningColor, "Edit");
            editButtons[i].setId(String.valueOf(arr[i].getUserID()));
            String id = editButtons[i].getId();
            editButtons[i].addActionListener(e -> openAddEditTab(id));
        }
        return editButtons;
    }


    public void openAddEditTab(String id){
        int action = 0;
        Student student;
        if(id.equals("")) {
            student = new Student();
        }
        else{
            student = studentList.findById(Integer.parseInt(id));
            action = 1;
        }

        if (addEditStudent != null) {
            addEditStudent.dispose();
        }
        addEditStudent = new AddEditStudent(action, student);
    }

    public void setSearchCriteria(ActionEvent e){

        String text = searchByBox.getItemAt(searchByBox.getSelectedIndex());
        labelCategory.setText(text + ":");
        searchButton.setVisible(true);
        labelCategory.setVisible(true);
        searchTextCategory.setVisible(true);
    }

    public void somethingHappening(ActionEvent e, String val){
        Student student = studentList.findById(Integer.parseInt(val));
        staffPanel.setStudentViewLabels(student);
    }


    public String getTableHeader(){
        return "<html><tr>" +
                "<td style='padding-right:50px; padding-left:10px'>ID</td>" +
                "<td style='padding-right:110px;'>Name</td>"+
                "<td style='padding-right:150px;'>Email</td>"+
                "<td style='padding-right:20px;'>Block</td>"+
                "<td style='padding-right:20px;'>Room</td>"+
                "</tr></html>";
    }

    public void renderTable(Student [] arr){
        //reset table before placing required components in place.
        dataOutput.removeAll();
        dataOutput.revalidate();
        dataOutput.repaint();



        TableButton [] vb = getDataTableViewButtons(arr);
        TableButton [] eb = getTableEditButtons(arr);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,3,20);
        for(int i = 0; i < arr.length; i++){
            new JLabel(String.valueOf(arr[i].getUserID()));
            gbc.gridx = 0; gbc.gridwidth = 2; gbc.gridy = i;
            dataOutput.add(new JLabel(String.valueOf(arr[i].getUserID())), gbc);
            gbc.gridx = 2; gbc.gridwidth = 4;  gbc.gridy = i;
            dataOutput.add(new JLabel(arr[i].getName()), gbc);
            gbc.gridx=6;   gbc.gridwidth =5; gbc.gridy = i;
            dataOutput.add(new JLabel(arr[i].getEmail()), gbc);
            gbc.gridx = 11;  gbc.gridwidth = 5; gbc.gridy =i;
            dataOutput.add(new JLabel(arr[i].getCourse()), gbc);
            gbc.gridx = 16; gbc.gridwidth = 2; gbc.gridy = i;
            dataOutput.add(new JLabel(String.valueOf(arr[i].getBlockLetter()).toUpperCase()), gbc);
            gbc.gridx = 18; gbc.gridwidth = 2; gbc.gridy = i;
            dataOutput.add(new JLabel(String.valueOf(arr[i].getRoomNumber())), gbc);
            gbc.gridwidth =1; gbc.gridx = 20;
            gbc.gridy = i;
            dataOutput.add(vb[i], gbc);
            gbc.gridwidth=1;
            gbc.gridx = 21;
            gbc.gridy = i;
            dataOutput.add(eb[i], gbc);
        }
    }

    public void displayAllTableData(){
        renderTable(studentList.getStudentList());
    }

}
