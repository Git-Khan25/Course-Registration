package com.university.dao;

    import com.university.model.Course;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class CourseDAO {
        public void addCourse(Course course) {
            String sql = "INSERT INTO courses (course_code, title, capacity) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, course.getCourseCode());
                pstmt.setString(2, course.getTitle());
                pstmt.setInt(3, course.getCapacity());
                pstmt.executeUpdate();
                System.out.println("✅ Course added successfully!");
            } catch (SQLException e) {
                System.out.println("❌ Error adding course: " + e.getMessage());
            }
        }

        public List<Course> getAllCourses() {
            List<Course> courses = new ArrayList<>();
            String sql = "SELECT * FROM courses";
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
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
                System.out.println("❌ Error fetching courses: " + e.getMessage());
            }
            return courses;
        }
    }
    