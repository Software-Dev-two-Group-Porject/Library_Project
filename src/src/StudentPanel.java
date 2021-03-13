import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created By Jonathon on 03/03/2021
 * Update Comments About Program Here
 **/
public class StudentPanel extends JPanel implements ActionListener {
    HeaderPanel headerPanel;
    StudentList studentList;

    StudentPanel(Student student){
        headerPanel = new HeaderPanel("Student", student.getName());
        headerPanel.setBounds(20, 10, 850, 120);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
