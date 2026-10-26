CREATE DATABASE IF NOT EXISTS course_db;
    USE course_db;

    CREATE TABLE IF NOT EXISTS students (
        student_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL
    );

    CREATE TABLE IF NOT EXISTS courses (
        course_id INT AUTO_INCREMENT PRIMARY KEY,
        course_code VARCHAR(20) UNIQUE NOT NULL,
        title VARCHAR(100) NOT NULL,
        capacity INT NOT NULL,
        enrolled_count INT DEFAULT 0
    );

    CREATE TABLE IF NOT EXISTS registrations (
        registration_id INT AUTO_INCREMENT PRIMARY KEY,
        student_id INT,
        course_id INT,
        registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
        FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
        UNIQUE KEY unique_enrollment (student_id, course_id)
    );
    