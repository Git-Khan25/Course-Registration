package com.university.dao;

    import com.university.model.Student;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class StudentDAO {
        public void addStudent(Student student) {
            String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, student.getName());
                pstmt.setString(2, student.getEmail());
                pstmt.executeUpdate();
                System.out.println("✅ Student added successfully!");
            } catch (SQLException e) {
                System.out.println("❌ Error adding student: " + e.getMessage());
            }
        }

        public List<Student> getAllStudents() {
            List<Student> students = new ArrayList<>();
            String sql = "SELECT * FROM students";
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email")
                    ));
                }
            } catch (SQLException e) {
                System.out.println("❌ Error fetching students: " + e.getMessage());
            }
            return students;
        }
    }
    