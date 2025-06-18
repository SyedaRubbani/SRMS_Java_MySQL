import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StudentForm extends JFrame {
    private JTextField txtRoll, txtName, txtCourse, txtYear, txtGpa;
    private JButton btnAdd, btnLoad;
    private JTable table;
    private DefaultTableModel model;

    public StudentForm() {
        setTitle("Student Record Management System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        txtRoll = new JTextField();
        txtName = new JTextField();
        txtCourse = new JTextField();
        txtYear = new JTextField();
        txtGpa = new JTextField();

        panel.add(new JLabel("Roll No:"));
        panel.add(txtRoll);
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Course:"));
        panel.add(txtCourse);
        panel.add(new JLabel("Year:"));
        panel.add(txtYear);
        panel.add(new JLabel("GPA:"));
        panel.add(txtGpa);

        btnAdd = new JButton("Add Student");
        btnLoad = new JButton("Load All");

        panel.add(btnAdd);
        panel.add(btnLoad);

        add(panel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Roll No", "Name", "Course", "Year", "GPA"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAdd.addActionListener(e -> {
            int roll = Integer.parseInt(txtRoll.getText());
            String name = txtName.getText();
            String course = txtCourse.getText();
            int year = Integer.parseInt(txtYear.getText());
            double gpa = Double.parseDouble(txtGpa.getText());

            Student student = new Student(roll, name, course, year, gpa);
            StudentDAO.addStudent(student);
            JOptionPane.showMessageDialog(this, "Student added.");
        });

        btnLoad.addActionListener(e -> {
            model.setRowCount(0);
            List<Student> students = StudentDAO.getAllStudents();
            for (Student s : students) {
                model.addRow(new Object[]{s.getRollNo(), s.getName(), s.getCourse(), s.getYear(), s.getGpa()});
            }
        });
    }
}