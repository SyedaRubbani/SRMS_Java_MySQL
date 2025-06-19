import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Create (INSERT)
    public void addStudent(Student s) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO students (roll_no, name, course, year, gpa) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, s.getRollNo());
        pst.setString(2, s.getName());
        pst.setString(3, s.getCourse());
        pst.setInt(4, s.getYear());
        pst.setDouble(5, s.getGpa());
        pst.executeUpdate();
        conn.close();
    }

    // Read (SELECT ALL)
    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(new Student(
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getInt("year"),
                rs.getDouble("gpa")
            ));
        }
        conn.close();
        return list;
    }

    // Update
    public void updateStudent(Student s) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE students SET name=?, course=?, year=?, gpa=? WHERE roll_no=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, s.getName());
        pst.setString(2, s.getCourse());
        pst.setInt(3, s.getYear());
        pst.setDouble(4, s.getGpa());
        pst.setInt(5, s.getRollNo());
        pst.executeUpdate();
        conn.close();
    }

    // Delete
    public void deleteStudent(int rollNo) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM students WHERE roll_no=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, rollNo);
        pst.executeUpdate();
        conn.close();
    }

    // Search by name
    public List<Student> searchStudents(String keyword) throws SQLException {
        List<Student> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            list.add(new Student(
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getInt("year"),
                rs.getDouble("gpa")
            ));
        }
        conn.close();
        return list;
    }

    // Sort by GPA (descending)
    public List<Student> getStudentsSortedByGPA() throws SQLException {
        List<Student> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students ORDER BY gpa DESC";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(new Student(
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getInt("year"),
                rs.getDouble("gpa")
            ));
        }
        conn.close();
        return list;
    }
}
