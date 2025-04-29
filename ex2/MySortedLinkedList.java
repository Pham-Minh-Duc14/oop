package hus.oop.sorteddatastructure;

public class MySortedLinkedList extends MySortedAbstractList {
    private Node head;
    private int size;

    /**
     * Hàm dựng khởi tạo list để chứa dữ liệu.
     */
    public MySortedLinkedList() {
        // TODO: Initialize head and size
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        // TODO: Return current size
        return this.size;
    }

    @Override
    public void clear() {
        // TODO: Reset the list
        this.head = null;
        this.size = 0;
    }

    /**
     * Phương thức lấy Node ở vị trí index. Helper method.
     * @param index
     * @return Node at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private Node getNodeByIndex(int index) {
        // TODO: Implement getNodeByIndex helper
        if (!checkBoundaries(index, this.size - 1)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Lấy giá trị của phần tử ở vị trí index.
     * @param index
     * @return value at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public int get(int index) {
        // TODO: Get value using getNodeByIndex
        Node node = getNodeByIndex(index); // This already checks bounds
        return node.data;
    }

    /**
     * Thêm phần phần tử vào danh sách theo thứ tự tăng dần.
     * @param value giá trị của phần tử dữ liệu được thêm vào.
     */
    @Override
    public void add(int value) {
        // TODO: Add element while maintaining sorted order
        Node newNode = new Node(value);
        if (this.head == null || value <= this.head.data) {
            // Insert at the beginning or into empty list
            newNode.next = this.head;
            this.head = newNode;
        } else {
            // Find the node before the insertion point
            Node current = this.head;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }
            // Insert after current
            newNode.next = current.next;
            current.next = newNode;
        }
        this.size++;
    }

    /**
     * Xóa phần tử dữ liệu tại vị trí index.
     * Chỉ xóa được nếu index nằm trong đoạn [0 - (size() - 1)]
     * @param index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void remove(int index) {
        // TODO: Check bounds and remove element by adjusting pointers
        if (!checkBoundaries(index, this.size - 1)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        if (index == 0) {
            // Remove head
            this.head = this.head.next;
        } else {
            // Find the node before the one to remove
            Node prev = getNodeByIndex(index - 1);
            // Skip the node at index
            prev.next = prev.next.next;
        }
        this.size--;
    }

     /**
     * Tìm chỉ số của giá trị value trong list, nếu không có trả về -1.
     * Do cấu trúc Linked List, thực hiện tìm kiếm tuần tự.
     * @param value
     * @return index or -1
     */
    @Override
    public int binarySearch(int value) {
        // TODO: Implement linear search (binary search is inefficient on linked list)
        Node current = this.head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                return index; // Found
            }
            if (current.data > value) {
                return -1; // Value cannot exist further (list is sorted)
            }
            current = current.next;
            index++;
        }
        return -1; // Reached end, not found
    }


    @Override
    public boolean contains(int value) {
        // TODO: Implement contains using linear search (can stop early)
        Node current = this.head;
        while (current != null) {
             if (current.data == value) {
                 return true; // Found
             }
             if (current.data > value) {
                 return false; // Value cannot exist further (list is sorted)
             }
             current = current.next;
        }
        return false; // Reached end, not found
    }


    /**
     * Lấy ra dữ liệu được lưu theo cấu trúc dữ liệu kiểu mảng.
     * @return an array containing all elements in order.
     */
    @Override
    public int[] toArray() {
        // TODO: Convert linked list to array
        int[] array = new int[this.size];
        Node current = this.head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
}
