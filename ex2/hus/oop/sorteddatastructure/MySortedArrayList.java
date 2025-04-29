package hus.oop.sorteddatastructure;

import java.util.Arrays;

public class MySortedArrayList extends MySortedAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private int[] data;
    private int size;

    /**
     * Hàm dựng để khởi tạo dữ liệu.
     */
    public MySortedArrayList() {
        // TODO: Initialize data array and size
        this.data = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Lấy kích thước của tập dữ liệu.
     * @return size
     */
    @Override
    public int size() {
        // TODO: Return current size
        return this.size;
    }

    @Override
    public void clear() {
        // TODO: Reset the list
        this.size = 0;
        // Optional: reset array if memory usage is a concern
        // this.data = new int[DEFAULT_CAPACITY];
    }

    /**
     * Lấy giá trị của phần tử ở vị trí index.
     * @param index
     * @return value at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public int get(int index) {
        // TODO: Check bounds and return element
        if (!checkBoundaries(index, this.size - 1)) { // Check against actual occupied size - 1
             throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        return this.data[index];
    }

    /**
     * Thêm phần tử dữ liệu vào danh sách theo thứ tự tăng dần.
     * Nếu mảng không còn chỗ, mở rộng mảng.
     * @param value là giá trị của phần tử dữ liệu được thêm vào.
     */
    @Override
    public void add(int value) {
        // TODO: Add element while maintaining sorted order and allocate more if needed
        if (this.size >= this.data.length) {
            allocateMore();
        }

        // Find insertion point using a variation of binary search or linear scan
        int insertionPoint = 0;
        // Find the first index where data[index] >= value
        while (insertionPoint < this.size && this.data[insertionPoint] < value) {
             insertionPoint++;
        }
        // // Alternative: Use Arrays.binarySearch to find insertion point
        // int insertionPoint = Arrays.binarySearch(this.data, 0, this.size, value);
        // if (insertionPoint < 0) {
        //     insertionPoint = -(insertionPoint + 1); // Convert negative result to insertion point
        // } // If found (insertionPoint >= 0), insert before the first equal element or after last equal

        // Shift elements to the right
        if (insertionPoint < this.size) {
            System.arraycopy(this.data, insertionPoint, this.data, insertionPoint + 1, this.size - insertionPoint);
        }

        // Insert the value
        this.data[insertionPoint] = value;
        this.size++;
    }


    /**
     * Xóa phần tử dữ liệu tại vị trí index.
     * Chỉ xóa được nếu index nằm trong đoạn [0 - (size - 1)]
     * @param index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void remove(int index) {
        // TODO: Check bounds and remove element by shifting
         if (!checkBoundaries(index, this.size - 1)) {
             throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
         }

         // Shift elements to the left
         int numMoved = this.size - index - 1;
         if (numMoved > 0) {
             System.arraycopy(this.data, index + 1, this.data, index, numMoved);
         }

         this.size--;
         // Optional: Null out the last element if necessary (not needed for primitive int)
         // this.data[this.size] = 0; // Or some default value
    }

    /**
    * Tìm chỉ số của giá trị value trong list, nếu không có trả về -1.
    * Sử dụng tìm kiếm nhị phân.
    * @param value
    * @return index or -1
    */
    @Override
    public int binarySearch(int value) {
        // TODO: Implement binary search
        int low = 0;
        int high = this.size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid potential overflow
            if (this.data[mid] < value) {
                low = mid + 1;
            } else if (this.data[mid] > value) {
                high = mid - 1;
            } else {
                // Value found, but we need the *first* occurrence if duplicates exist
                // For simplicity in this context, returning the first one found is often acceptable.
                // If the requirement is strictly the *first* index, need to scan left from mid.
                // Let's assume finding *any* index is sufficient based on typical binarySearch usage.
                return mid;
            }
        }
        return -1; // Value not found
        // Alternative using built-in:
        // int result = Arrays.binarySearch(this.data, 0, this.size, value);
        // return (result < 0) ? -1 : result; // Return -1 if not found
    }


    @Override
    public boolean contains(int value) {
        // TODO: Implement contains using binarySearch
        return binarySearch(value) >= 0;
    }

    /**
     * Mở rộng gấp đôi kích thước mảng nếu hết chỗ để chứa dữ liệu.
     */
    private void allocateMore() {
        // TODO: Double the array capacity
        int newCapacity = this.data.length * 2;
        this.data = Arrays.copyOf(this.data, newCapacity);
        // Or:
        // int[] newData = new int[newCapacity];
        // System.arraycopy(this.data, 0, newData, 0, this.size);
        // this.data = newData;
    }

    /**
     * Lấy ra dữ liệu được lưu theo cấu trúc dữ liệu kiểu mảng.
     * @return a copy of the internal data array (only the used part).
     */
    @Override
    public int[] toArray() {
        // TODO: Return a copy of the array containing elements
        return Arrays.copyOf(this.data, this.size);
    }
}
