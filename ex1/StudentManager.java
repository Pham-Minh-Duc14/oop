package hus.oop.students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {
    // Singleton pattern
    private static StudentManager instance;

    private List<Student> studentList;

    private StudentManager() {
        // TODO: Initialize the studentList
        studentList = new ArrayList<>();
    }

    public static StudentManager getInstance() {
        // TODO: Implement Singleton getter
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    // Getter for the internal list (useful for App)
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Thêm sinh viên vào cuối danh sách.
     * @param student
     */
    public void append(Student student) {
        // TODO: Add student to the end of the list
        if (student != null) {
            this.studentList.add(student);
        }
    }

    /**
     * Thêm sinh viên vào danh sách ở vị trí index.
     * @param student
     * @param index
     */
    public void add(Student student, int index) {
        // TODO: Add student at the specified index, handle index bounds
        if (student != null && index >= 0 && index <= this.studentList.size()) {
            this.studentList.add(index, student);
        }
        // Optionally throw an exception for invalid index
    }

    /**
     * Xóa sinh viên ở vị trí index.
     * @param index
     */
    public void remove(int index) {
        // TODO: Remove student at the specified index, handle index bounds
        if (index >= 0 && index < this.studentList.size()) {
            this.studentList.remove(index);
        }
        // Optionally throw an exception for invalid index
    }

    /**
     * Lấy ra sinh viên ở vị trí index.
     * @param index
     * @return Student or null if index is out of bounds
     */
    public Student studentAt(int index) {
        // TODO: Get student at the specified index, handle index bounds
        if (index >= 0 && index < this.studentList.size()) {
            return this.studentList.get(index);
        }
        return null; // Or throw an exception
    }

    /**
     * Trả về số student trong danh sách.
     * @return
     */
    public int numberOfStudents() {
        // TODO: Return the size of the list
        return this.studentList.size();
    }

    /**
     * Sắp xếp danh sách sinh viên theo thứ tự tăng dần theo tên,
     * nếu tên như nhau thì sắp xếp theo họ.
     * Sử dụng giao diện StudentComparable để sắp xếp.
     * @return The sorted list (modifies the internal list).
     */
    public List<Student> sortStudentsByName() {
        // TODO: Sort the internal list using Student's compareTo method
        // Note: This modifies the internal list directly.
        Collections.sort(this.studentList);
        return this.studentList; // Return the reference to the modified list
    }

    /**
     * Trả về danh sách sinh viên sắp xếp theo thứ tự điểm trung bình tăng dần.
     * Sử dụng giao diện StudentComparator để sắp xếp.
     * Không làm thay đổi thứ tự sinh viên trong danh sách ban đầu.
     * @return A new list sorted by average grade (increasing).
     */
    public List<Student> sortAverageGradeIncreasing() {
        // TODO: Create a copy, sort using a custom comparator, return the copy
        List<Student> sortedList = new ArrayList<>(this.studentList);
        // Define the comparator using lambda or an anonymous class
        StudentComparator comparator = (left, right) -> Double.compare(left.getAverageGrade(), right.getAverageGrade());
        // Can also use Comparator.comparingDouble
        // sortedList.sort(Comparator.comparingDouble(Student::getAverageGrade));
        Collections.sort(sortedList, comparator);
        return sortedList;
    }

    /**
     * Trả về danh sách sinh viên sắp xếp theo thứ tự điểm trung bình giảm dần.
     * Sử dụng giao diện StudentComparator để sắp xếp.
     * Không làm thay đổi thứ tự sinh viên trong danh sách ban đầu.
     * @return A new list sorted by average grade (decreasing).
     */
    public List<Student> sortAverageGradeDecreasing() {
        // TODO: Create a copy, sort using a custom comparator (reversed), return the copy
        List<Student> sortedList = new ArrayList<>(this.studentList);
        // Define the comparator using lambda or an anonymous class (reversed)
        StudentComparator comparator = (left, right) -> Double.compare(right.getAverageGrade(), left.getAverageGrade());
        // Can also use Comparator.comparingDouble().reversed()
        // sortedList.sort(Comparator.comparingDouble(Student::getAverageGrade).reversed());
         Collections.sort(sortedList, comparator);
        return sortedList;
    }

    /**
     * Lọc ra danh sách sinh viên có tất cả các điểm trên 4.0 và điểm trung bình trên 5.0.
     * @return A new list of passing students.
     */
    public List<Student> filterPassStudents() {
        // TODO: Filter the list based on the criteria using streams or a loop
        List<Student> passStudents = new ArrayList<>();
        for (Student student : this.studentList) {
            if (student.getMathsGrade() > 4.0 &&
                student.getPhysicsGrade() > 4.0 &&
                student.getChemistryGrade() > 4.0 &&
                student.getAverageGrade() > 5.0) {
                passStudents.add(student);
            }
        }
        return passStudents;
        /* Stream alternative:
        return this.studentList.stream()
                .filter(s -> s.getMathsGrade() > 4.0 &&
                             s.getPhysicsGrade() > 4.0 &&
                             s.getChemistryGrade() > 4.0 &&
                             s.getAverageGrade() > 5.0)
                .collect(Collectors.toList());
        */
    }

    /**
     * Lọc ra danh sách sinh viên có ít nhất 1 môn dưới 4.0 hoặc điểm trung bình dưới 5.0.
     * @param howMany The maximum number of students to return.
     * @return A new list of the first 'howMany' failing students.
     */
     // Note: The original signature in the UML/request was just filterFailureStudents(),
     // but the provided code has filterFailureStudents(int howMany). We follow the code.
    public List<Student> filterFailureStudents(int howMany) {
        // TODO: Filter based on criteria and limit the result
        List<Student> failureStudents = new ArrayList<>();
        for (Student student : this.studentList) {
            if (student.getMathsGrade() < 4.0 ||
                student.getPhysicsGrade() < 4.0 ||
                student.getChemistryGrade() < 4.0 ||
                student.getAverageGrade() < 5.0) {
                failureStudents.add(student);
                if (failureStudents.size() >= howMany) {
                    break; // Stop once we have enough
                }
            }
        }
        return failureStudents;
        /* Stream alternative:
        return this.studentList.stream()
                .filter(s -> s.getMathsGrade() < 4.0 ||
                             s.getPhysicsGrade() < 4.0 ||
                             s.getChemistryGrade() < 4.0 ||
                             s.getAverageGrade() < 5.0)
                .limit(howMany)
                .collect(Collectors.toList());
        */
    }

    // Helper methods provided in the original code
    public static String idOfStudentsToString(List<Student> studentList) {
        StringBuilder idOfStudents = new StringBuilder();
        idOfStudents.append("[");
        for (Student student : studentList) {
            idOfStudents.append(student.getId()).append(" ");
        }
        return idOfStudents.toString().trim() + "]";
    }

    public static void print(List<Student> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder studentsString = new StringBuilder();
        studentsString.append("[\n");
        for (Student student : studentList) {
            studentsString.append(student.toString()).append("\n");
        }
        // Remove the last newline before closing bracket
        if (studentsString.length() > 2) { // Check if there's more than "[\n"
             studentsString.setLength(studentsString.length() - 1);
        }
        studentsString.append("\n]");
        System.out.print(studentsString.toString());
    }
}
