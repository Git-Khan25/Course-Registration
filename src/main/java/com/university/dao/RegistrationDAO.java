package com.university.dao;

    import com.university.model.Course;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class RegistrationDAO {
        public void registerCourse(int studentId, int courseId) {
            String checkCapacitySQL = "SELECT capacity, enrolled_count FROM courses WHERE course_id = ?";
            String insertRegSQL = "INSERT INTO registrations (student_id, course_id) VALUES (?, ?)";
            String updateCountSQL = "UPDATE courses SET enrolled_count = enrolled_count + 1 WHERE course_id = ?";

            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false);

                try (PreparedStatement checkStmt = conn.prepareStatement(checkCapacitySQL)) {
                    checkStmt.setInt(1, courseId);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next()) {
                        int capacity = rs.getInt("capacity");
                        int enrolled = rs.getInt("enrolled_count");
                        if (enrolled >= capacity) {
                            System.out.println("❌ Registration failed: Course is full!");
                            return;
                        }
                    } else {
                        System.out.println("❌ Course not found!");
                        return;
                    }
                }

                try (PreparedStatement regStmt = conn.prepareStatement(insertRegSQL)) {
                    regStmt.setInt(1, studentId);
                    regStmt.setInt(2, courseId);
                    regStmt.executeUpdate();
                }

                try (PreparedStatement updateStmt = conn.prepareStatement(updateCountSQL)) {
                    updateStmt.setInt(1, courseId);
                    updateStmt.executeUpdate();
                }

                conn.commit();
                System.out.println("🎉 Student successfully registered for the course!");

            } catch (SQLException e) {
                System.out.println("❌ Registration failed: " + e.getMessage());
            }
        }

        public List<Course> getEnrolledCourses(int studentId) {
            List<Course> courses = new ArrayList<>();
            String sql = "SELECT c.* FROM courses c JOIN registrations r ON c.course_id = r.course_id WHERE r.student_id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getInt("enrolled_count")
                    ));
                }
            } catch (SQLException e) {
                System.out.println("❌ Error fetching enrolled courses: " + e.getMessage());
            }
            return courses;
        }
    }
    