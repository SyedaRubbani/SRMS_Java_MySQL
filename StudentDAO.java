import java.sql.*;
import java.util.*;

public class StudentDAO {
    public static void addStudent(Student student) {
        String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, student.getRollNo());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getYear());
            stmt.setDouble(5, student.getGpa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getInt("year"),
                        rs.getDouble("gpa")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}