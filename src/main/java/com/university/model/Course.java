package com.university.model;

    public class Course {
        private int courseId;
        private String courseCode;
        private String title;
        private int capacity;
        private int enrolledCount;

        public Course(int courseId, String courseCode, String title, int capacity, int enrolledCount) {
            this.courseId = courseId;
            this.courseCode = courseCode;
            this.title = title;
            this.capacity = capacity;
            this.enrolledCount = enrolledCount;
        }

        public Course(String courseCode, String title, int capacity) {
            this.courseCode = courseCode;
            this.title = title;
            this.capacity = capacity;
            this.enrolledCount = 0;
        }

        public int getCourseId() { return courseId; }
        public String getCourseCode() { return courseCode; }
        public String getTitle() { return title; }
        public int getCapacity() { return capacity; }
        public int getEnrolledCount() { return enrolledCount; }

        @Override
        public String toString() {
            return String.format("ID: %-3d | Code: %-10s | Title: %-25s | Capacity: %d/%d", 
                    courseId, courseCode, title, enrolledCount, capacity);
        }
    }
    