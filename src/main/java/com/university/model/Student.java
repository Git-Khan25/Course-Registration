package com.university.model;

    public class Student {
        private int studentId;
        private String name;
        private String email;

        public Student(int studentId, String name, String email) {
            this.studentId = studentId;
            this.name = name;
            this.email = email;
        }

        public Student(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public int getStudentId() { return studentId; }
        public String getName() { return name; }
        public String getEmail() { return email; }

        @Override
        public String toString() {
            return String.format("ID: %-3d | Name: %-20s | Email: %s", studentId, name, email);
        }
    }
    