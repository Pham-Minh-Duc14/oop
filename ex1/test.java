package hus.oop.students;

// ... (import statements and other methods) ...

public class App {

    // ... (readListData, parseDataLineToList, parseDataLineToArray, init) ...

    public static void main(String[] args) {
        init(); // Load data first

        System.out.println("--- Testing Original Data ---");
        testOriginalData();
        System.out.println("\n--- Testing Sort Students By Name ---");
        testSortStudentsByName(); // Note: This will sort the internal list in StudentManager
        System.out.println("\n--- Testing Original Data (After Sort By Name) ---");
        testOriginalData(); // Print again to show the list is now sorted by name
        System.out.println("\n--- Testing Sort Average Grade Increasing ---");
        testSortAverageGradeIncreasing();
        System.out.println("\n--- Testing Sort Average Grade Decreasing ---");
        testSortAverageGradeDecreasing();
        System.out.println("\n--- Testing Filter Pass Students ---");
        testFilterPassStudents();
        System.out.println("\n--- Testing Filter Failure Students ---");
        testFilterFailureStudents(); // Will get all failing students
    }

    /*
     * Test in ra danh sách các sinh viên theo thứ tự đọc vào ban đầu.
     */
    public static void testOriginalData() {
        // TODO: Get the list from StudentManager and print it
        StudentManager manager = StudentManager.getInstance();
        System.out.println("Original student list:"); // Added description
        StudentManager.print(manager.getStudentList());
    }

    /*
     * Test in ra danh sách sinh viên được sắp xếp theo thứ tự tăng dần, đầu tiên tính đến tên, sau đó tính đến họ.
     */
    public static void testSortStudentsByName() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        // The sortStudentsByName method sorts the internal list and returns it
        System.out.println("Student list sorted by name (Firstname, then Lastname):"); // Added description
        // Calling sortStudentsByName modifies the list in the manager
        manager.sortStudentsByName();
        // Print the (now sorted) list from the manager
        StudentManager.print(manager.getStudentList());
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình tăng dần.
     */
    public static void testSortAverageGradeIncreasing() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        // This method returns a *new* sorted list, doesn't modify the original
        List<Student> sortedList = manager.sortAverageGradeIncreasing();
        System.out.println("Student list sorted by average grade (Increasing):"); // Added description
        StudentManager.print(sortedList);
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình giảm dần.
     */
    public static void testSortAverageGradeDecreasing() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
         // This method returns a *new* sorted list, doesn't modify the original
        List<Student> sortedList = manager.sortAverageGradeDecreasing();
        System.out.println("Student list sorted by average grade (Decreasing):"); // Added description
        StudentManager.print(sortedList);
    }

    /*
     * Test in ra danh sách sinh viên thi đỗ.
     * (Criteria: all grades > 4.0 AND average grade > 5.0)
     */
    public static void testFilterPassStudents() {
        // TODO: Call the filtering method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        List<Student> passList = manager.filterPassStudents();
        System.out.println("Passing students (all grades > 4.0 AND average > 5.0):"); // Added description
        StudentManager.print(passList);
    }

    /*
     * Test in ra danh sách sinh viên thi trượt.
     * (Criteria: at least one grade < 4.0 OR average grade < 5.0)
     */
    public static void testFilterFailureStudents() {
        // TODO: Call the filtering method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        // Get all failing students
        List<Student> failureList = manager.filterFailureStudents(Integer.MAX_VALUE);
        System.out.println("Failing students (at least one grade < 4.0 OR average < 5.0):"); // Added description
        StudentManager.print(failureList);
    }
}
