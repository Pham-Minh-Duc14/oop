package hus.oop.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String COMMA_DELIMITER = ",";

    public static void readListData(String filePath) {
        BufferedReader dataReader = null;
        try {
            String line;
            dataReader = new BufferedReader(new FileReader(filePath));
            StudentManager studentManager = StudentManager.getInstance(); // Get Singleton instance

            // Read file line by line
            while ((line = dataReader.readLine()) != null) {
                List<String> dataList = parseDataLineToList(line);
                if (dataList.size() != 7) { // Expect 7 fields: id, lastname, firstname, year, math, phys, chem
                    System.err.println("Skipping invalid line (incorrect number of fields): " + line);
                    continue;
                }

                if (dataList.get(0).trim().equalsIgnoreCase("id")) { // Skip header row (case-insensitive)
                    continue;
                }

                /*
                 TODO: Read data, create Student objects using Builder, add to StudentManager.
                 Handle potential exceptions during parsing (e.g., NumberFormatException).
                */
                try {
                    String id = dataList.get(0).trim();
                    String lastname = dataList.get(1).trim();
                    String firstname = dataList.get(2).trim();
                    int yearOfBirth = Integer.parseInt(dataList.get(3).trim());
                    double mathsGrade = Double.parseDouble(dataList.get(4).trim());
                    double physicsGrade = Double.parseDouble(dataList.get(5).trim());
                    double chemistryGrade = Double.parseDouble(dataList.get(6).trim());

                    // Use Builder Pattern to create Student object
                    Student newStudent = new Student.StudentBuilder(id)
                            .withLastname(lastname)
                            .withFirstname(firstname)
                            .withYearOfBirth(yearOfBirth)
                            .withMathsGrade(mathsGrade)
                            .withPhysicsGrade(physicsGrade)
                            .withChemistryGrade(chemistryGrade)
                            .build();

                    // Add student to the manager
                    studentManager.append(newStudent);

                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line (number format error): " + line + " - Error: " + e.getMessage());
                    // Continue to the next line even if one line fails
                } catch (Exception e) { // Catch other potential errors during student creation
                     System.err.println("Skipping invalid line (unexpected error): " + line + " - Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        } finally {
            try {
                if (dataReader != null)
                    dataReader.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
    }

    public static List<String> parseDataLineToList(String dataLine) {
        List<String> result = new ArrayList<>();
        if (dataLine != null) {
            String[] splitData = dataLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                // Trim whitespace from each part
                result.add(splitData[i].trim());
            }
        }
        return result;
    }

    // parseDataLineToArray remains unused based on readListData implementation, but kept as is.
    public static String[] parseDataLineToArray(String dataLine) {
        if (dataLine == null) {
            return null;
        }
        // Trim whitespace before splitting might be better here if needed elsewhere
        // return dataLine.trim().split(COMMA_DELIMITER);
         return dataLine.split(COMMA_DELIMITER);
    }

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

         /* Yêu cầu:
         - Hoàn thiện code chương trình theo mẫu và theo yêu cầu đã cho. (Done)
         - Viết code để test cho tất cả các hàm test. (Done)

         - Thực hiện chạy từng hàm test, lưu kết quả chạy chương trình và file text được đặt tên
           là <TenSinhVien_MaSinhVien_StudentManager>.txt (Ví dụ, NguyenVanA_123456_StudentManager.txt).
         - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
           <TenSinhVien_MaSinhVien_StudentManager>.zip (Ví dụ, NguyenVanA_123456_StudentManager.zip),
           nộp lên classroom.
          */
    }

    public static void init() {
        // Make sure the path is correct relative to the execution directory
        // If "data" is a folder in the project root, this might work.
        // Adjust if necessary, e.g., "src/hus/oop/students/data/students.csv"
        String filePath = "data/students.csv";
        readListData(filePath);
    }

    /*
     * Test in ra danh sách các sinh viên theo thứ tự đọc vào ban đầu.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testOriginalData() {
        // TODO: Get the list from StudentManager and print it
        StudentManager manager = StudentManager.getInstance();
        StudentManager.print(manager.getStudentList());
    }

    /*
     * Test in ra danh sách sinh viên được sắp xếp theo thứ tự tăng dần, đầu tiên tính đến tên, sau đó tính đến họ.
     */
    public static void testSortStudentsByName() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        // The sortStudentsByName method sorts the internal list and returns it
        List<Student> sortedList = manager.sortStudentsByName();
        StudentManager.print(sortedList);
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình tăng dần.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testSortAverageGradeIncreasing() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        // This method returns a *new* sorted list, doesn't modify the original
        List<Student> sortedList = manager.sortAverageGradeIncreasing();
        StudentManager.print(sortedList);
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình giảm dần.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testSortAverageGradeDecreasing() {
        // TODO: Call the sorting method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
         // This method returns a *new* sorted list, doesn't modify the original
        List<Student> sortedList = manager.sortAverageGradeDecreasing();
        StudentManager.print(sortedList);
    }

    /*
     * Test in ra danh sách sinh viên thi đỗ.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testFilterPassStudents() {
        // TODO: Call the filtering method in StudentManager and print the result
        StudentManager manager = StudentManager.getInstance();
        List<Student> passList = manager.filterPassStudents();
        StudentManager.print(passList);
    }

    /*
     * Test in ra danh sách sinh viên thi trượt.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testFilterFailureStudents() {
        // TODO: Call the filtering method in StudentManager and print the result
        // Passing a large number to get all failing students, as the test method
        // doesn't specify 'howMany'. Alternatively, could modify the manager method
        // or add a version without 'howMany'.
        StudentManager manager = StudentManager.getInstance();
        List<Student> failureList = manager.filterFailureStudents(Integer.MAX_VALUE); // Get all failing students
        StudentManager.print(failureList);
    }
}
