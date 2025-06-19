import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class StudentForm extends JFrame {
    private JTextField txtRoll, txtName, txtCourse, txtYear, txtGpa, txtSearch;
    private JButton btnAdd, btnLoad, btnUpdate, btnDelete, btnSearch, btnSort;
    private JTable table;
    private DefaultTableModel model;

    private StudentDAO dao = new StudentDAO(); // DAO instance

    public StudentForm() {
        setTitle("Student Record Management System (SRMS)");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input Form Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        txtRoll = new JTextField();
        txtName = new JTextField();
        txtCourse = new JTextField();
        txtYear = new JTextField();
        txtGpa = new JTextField();
        txtSearch = new JTextField();

        inputPanel.add(new JLabel("Roll No:"));
        inputPanel.add(txtRoll);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(txtCourse);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(txtYear);
        inputPanel.add(new JLabel("GPA:"));
        inputPanel.add(txtGpa);
        inputPanel.add(new JLabel("Search by Name:"));
        inputPanel.add(txtSearch);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        btnAdd = new JButton("Add");
        btnLoad = new JButton("Load");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");
        btnSort = new JButton("Sort by GPA");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnSort);

        add(buttonPanel, BorderLayout.SOUTH);

        // Table Panel
        model = new DefaultTableModel(new String[]{"Roll No", "Name", "Course", "Year", "GPA"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load selected row to form
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                txtRoll.setText(model.getValueAt(i, 0).toString());
                txtName.setText(model.getValueAt(i, 1).toString());
                txtCourse.setText(model.getValueAt(i, 2).toString());
                txtYear.setText(model.getValueAt(i, 3).toString());
                txtGpa.setText(model.getValueAt(i, 4).toString());
            }
        });

        // Add Button
        btnAdd.addActionListener(e -> {
            try {
                Student s = readForm();
                dao.addStudent(s);
                showMessage("Student added.");
                clearForm();
                loadData();
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        // Load Button
        btnLoad.addActionListener(e -> loadData());

        // Update Button
        btnUpdate.addActionListener(e -> {
            try {
                Student s = readForm();
                dao.updateStudent(s);
                showMessage("Student updated.");
                clearForm();
                loadData();
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        // Delete Button
        btnDelete.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(txtRoll.getText());
                dao.deleteStudent(roll);
                showMessage("Student deleted.");
                clearForm();
                loadData();
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        // Search Button
        btnSearch.addActionListener(e -> {
            try {
                List<Student> students = dao.searchStudents(txtSearch.getText());
                showStudents(students);
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        // Sort Button
        btnSort.addActionListener(e -> {
            try {
                List<Student> students = dao.getStudentsSortedByGPA();
                showStudents(students);
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
    }

    private Student readForm() {
        return new Student(
                Integer.parseInt(txtRoll.getText()),
                txtName.getText(),
                txtCourse.getText(),
                Integer.parseInt(txtYear.getText()),
                Double.parseDouble(txtGpa.getText()));
    }

    private void clearForm() {
        txtRoll.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtYear.setText("");
        txtGpa.setText("");
        txtSearch.setText("");
    }

    private void loadData() {
        try {
            List<Student> students = dao.getAllStudents();
            showStudents(students);
        } catch (SQLException ex) {
            showError(ex.getMessage());
        }
    }

    private void showStudents(List<Student> students) {
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{
                    s.getRollNo(), s.getName(), s.getCourse(), s.getYear(), s.getGpa()
            });
        }
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void showError(String err) {
        JOptionPane.showMessageDialog(this, err, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
