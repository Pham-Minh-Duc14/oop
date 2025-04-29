package hus.oop.sorteddatastructure;

import java.util.Random;

public class TestSortedDataStructure {
    public static void main(String[] args) {
        /* TODO: Call test methods with proper formatting */
        System.out.println("--- Test MySortedArrayList ---");
        testSortedArrayList();
        System.out.println("\n--- Test MySortedLinkedList ---");
        testSortedLinkedList();

         /*
         Yêu cầu:
         - Hoàn thiện code chương trình theo mẫu đã cho. (Done)
         - Viết code để test cho tất cả các hàm test bên dưới. (Done)

         - Thực hiện chạy từng hàm test theo format: (Done via System.out.println in main)
               Tên test:
               Kết quả chạy chương trình.

           Lưu kết quả chạy chương trình và file text được đặt tên
           là <TenSinhVien_MaSinhVien_SortedDataStructure>.txt (Ví dụ, NguyenVanA_123456_SortedDataStructure.txt).
         - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
           <TenSinhVien_MaSinhVien_SortedDataStructure>.zip (Ví dụ, NguyenVanA_123456_SortedDataStructure.zip),
           nộp lên classroom.
          */
    }

    public static void testSortedArrayList() {
        /* TODO: Implement test logic for MySortedArrayList */
        MySortedArrayList sortedList = new MySortedArrayList();
        Random random = new Random();

        // Generate n (20-30)
        int n = random.nextInt(11) + 20; // 0-10 + 20 => 20-30
        System.out.println("Adding " + n + " random elements (10-100) to SortedArrayList:");

        // Add n elements and print after each add
        for (int i = 0; i < n; i++) {
            int valueToAdd = random.nextInt(91) + 10; // 0-90 + 10 => 10-100
            sortedList.add(valueToAdd);
            System.out.println(sortedList.toString()); // Print current list state
        }
        System.out.println("Final size: " + sortedList.size());

        // Test contains and binarySearch
        System.out.println("\nTesting contains/binarySearch with 5 random values (0-120):");
        for (int i = 0; i < 5; i++) {
            int valueToTest = random.nextInt(121); // 0-120
            boolean contains = sortedList.contains(valueToTest);
            System.out.print("Test value: " + valueToTest + " -> Contains: " + contains);
            if (contains) {
                int index = sortedList.binarySearch(valueToTest);
                System.out.print(" -> Index (binarySearch): " + index);
                // Verification check (optional)
                if (index != -1 && sortedList.get(index) == valueToTest) {
                    System.out.print(" (Verified OK)");
                } else if (index != -1) {
                     System.out.print(" (Verification FAILED: get(" + index + ") returned " + sortedList.get(index) + ")");
                } else {
                     System.out.print(" (Verification ERROR: contains=true but binarySearch=-1)");
                }
            }
            System.out.println();
        }

         // Test remove (optional but good)
         if (sortedList.size() > 0) {
             int removeIndex = sortedList.size() / 2; // Remove middle element
             int removedValue = sortedList.get(removeIndex);
             System.out.println("\nRemoving element at index " + removeIndex + " (value: " + removedValue + ")");
             sortedList.remove(removeIndex);
             System.out.println("List after removal: " + sortedList);
             System.out.println("Checking if removed value " + removedValue + " is still contained: " + sortedList.contains(removedValue));
         }

         // Test clear (optional)
         System.out.println("\nClearing the list...");
         sortedList.clear();
         System.out.println("List after clear: " + sortedList);
         System.out.println("Size after clear: " + sortedList.size());
    }

    public static void testSortedLinkedList() {
         /* TODO: Implement test logic for MySortedLinkedList */
         MySortedLinkedList sortedList = new MySortedLinkedList();
         Random random = new Random();

         // Generate n (20-30)
         int n = random.nextInt(11) + 20; // 0-10 + 20 => 20-30
         System.out.println("Adding " + n + " random elements (10-100) to SortedLinkedList:");

         // Add n elements and print after each add
         for (int i = 0; i < n; i++) {
             int valueToAdd = random.nextInt(91) + 10; // 0-90 + 10 => 10-100
             sortedList.add(valueToAdd);
             System.out.println(sortedList.toString()); // Print current list state
         }
         System.out.println("Final size: " + sortedList.size());

         // Test contains and "binarySearch" (linear search implementation)
         System.out.println("\nTesting contains/search with 5 random values (0-120):");
         for (int i = 0; i < 5; i++) {
             int valueToTest = random.nextInt(121); // 0-120
             boolean contains = sortedList.contains(valueToTest);
             System.out.print("Test value: " + valueToTest + " -> Contains: " + contains);
             if (contains) {
                 // Note: binarySearch in LinkedList implementation is linear search
                 int index = sortedList.binarySearch(valueToTest);
                 System.out.print(" -> Index (linear search): " + index);
                  // Verification check (optional)
                 if (index != -1 && sortedList.get(index) == valueToTest) {
                     System.out.print(" (Verified OK)");
                 } else if (index != -1) {
                      System.out.print(" (Verification FAILED: get(" + index + ") returned " + sortedList.get(index) + ")");
                 } else {
                      System.out.print(" (Verification ERROR: contains=true but search=-1)");
                 }
             }
             System.out.println();
         }

          // Test remove (optional but good)
          if (sortedList.size() > 0) {
              int removeIndex = sortedList.size() / 2; // Remove middle element
              int removedValue = sortedList.get(removeIndex);
              System.out.println("\nRemoving element at index " + removeIndex + " (value: " + removedValue + ")");
              sortedList.remove(removeIndex);
              System.out.println("List after removal: " + sortedList);
              System.out.println("Checking if removed value " + removedValue + " is still contained: " + sortedList.contains(removedValue));
          }

          // Test clear (optional)
          System.out.println("\nClearing the list...");
          sortedList.clear();
          System.out.println("List after clear: " + sortedList);
          System.out.println("Size after clear: " + sortedList.size());
    }
}
