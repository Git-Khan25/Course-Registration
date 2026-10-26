package com.university;

    import com.university.dao.CourseDAO;
    import com.university.dao.RegistrationDAO;
    import com.university.dao.StudentDAO;
    import com.university.model.Course;
    import com.university.model.Student;

    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();
            RegistrationDAO registrationDAO = new RegistrationDAO();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n========================================");
                System.out.println("   UNIVERSITY COURSE REGISTRATION SYSTEM ");
                System.out.println("========================================");
                System.out.println("1. Register New Student");
                System.out.println("2. View All Students");
                System.out.println("3. Add New Course");
                System.out.println("4. View All Courses");
                System.out.println("5. Register Student for Course");
                System.out.println("6. View Student's Enrolled Courses");
                System.out.println("7. Exit");
                System.out.print("Choose an option (1-7): ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Student Email: ");
                        String email = scanner.nextLine();
                        studentDAO.addStudent(new Student(name, email));
                        break;
                    case 2:
                        System.out.println("\n--- Registered Students ---");
                        studentDAO.getAllStudents().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Enter Course Code: ");
                        String code = scanner.nextLine();
                        System.out.print("Enter Course Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Capacity: ");
                        int capacity = scanner.nextInt();
                        courseDAO.addCourse(new Course(code, title, capacity));
                        break;
                    case 4:
                        System.out.println("\n--- Available Courses ---");
                        courseDAO.getAllCourses().forEach(System.out::println);
                        break;
                    case 5:
                        System.out.print("Enter Student ID: ");
                        int sId = scanner.nextInt();
                        System.out.print("Enter Course ID: ");
                        int cId = scanner.nextInt();
                        registrationDAO.registerCourse(sId, cId);
                        break;
                    case 6:
                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();
                        System.out.println("\n--- Enrolled Courses ---");
                        registrationDAO.getEnrolledCourses(studentId).forEach(System.out::println);
                        break;
                    case 7:
                        System.out.println("Exiting system. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("❌ Invalid choice.");
                }
            }
        }
    }
    