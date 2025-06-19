import java.sql.*;
import java.util.*;

public class StudentDAO {

    // Add student
    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students (roll_no, name, course, year, gpa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, s.getRollNo());
            pst.setString(2, s.getName());
            pst.setString(3, s.getCourse());
            pst.setInt(4, s.getYear());
            pst.setDouble(5, s.getGpa());

            pst.executeUpdate();
        }
    }

    // Get all students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("roll_no"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getInt("year"),
                    rs.getDouble("gpa")
                ));
            }
        }
        return list;
    }

    // Update student
    public void updateStudent(Student s) throws SQLException {
        String sql = "UPDATE students SET name = ?, course = ?, year = ?, gpa = ? WHERE roll_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, s.getName());
            pst.setString(2, s.getCourse());
            pst.setInt(3, s.getYear());
            pst.setDouble(4, s.getGpa());
            pst.setInt(
