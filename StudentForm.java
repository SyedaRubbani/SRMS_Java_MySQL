import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class StudentForm extends JFrame {
    private JTextField txtRoll, txtName, txtCourse, txtYear, txtGpa;
    private JButton btnAdd, btnLoad;
    private JTable table;
    private DefaultTableModel model;
    private StudentDAO dao;

    public StudentForm() {
        setTitle("Student Record Management System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dao = new StudentDAO();  // instantiate DAO

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

        // Add Student button logic
        btnAdd.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(txtRoll.getText());
                String name = txtName.getText();
                String course = txtCourse.getText();
                int year = Integer.parseInt(txtYear.getText());
                double gpa = Double.parseDouble(txtGpa.getText());

                Student student = new Student(roll, name, course, year, gpa);
                dao.addStudent(student);  // non-static call
                JOptionPane.showMessageDialog(this, "Student added.");
                loadAllStudents();  // refresh the table after adding
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Load All button logic
        btnLoad.addActionListener(e -> {
            loadAllStudents();
        });
    }

    // Helper to refresh table
    private void loadAllStudents() {
        try {
            model.setRowCount(0);
            List<Student> students = dao.getAllStudents();
            for (Student s : students) {
                model.addRow(new Object[]{
                    s.getRollNo(), s.getName(), s.getCourse(), s.getYear(), s.getGpa()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load students: " + e.getMessage());
        }
    }

    // Clear fields after adding
    private void clearFields() {
        txtRoll.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtYear.setText("");
        txtGpa.setText("");
    }
}
