package hus.oop.sorteddatastructure;

public abstract class MySortedAbstractList implements MySortedList {
    /**
     * Phương thức kiểm tra xem index có nằm trong đoạn [0 - limit] không.
     * @param index
     * @param limit
     * @return true nếu index hợp lệ, false nếu không.
     */
    public boolean checkBoundaries(int index, int limit) {
        // TODO: Implement boundary check
        return index >= 0 && index <= limit;
    }

    /**
     * Mô tả tập dữ liệu.
     * @return mô tả tập dữ liệu theo định dạng [a1, a2, a3, ..., an].
     */
    @Override
    public String toString() {
        // TODO: Implement toString using size() and get()
        if (size() == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            try {
                // Assuming get(i) throws exception for invalid index, though checkBoundaries should prevent it
                sb.append(get(i));
                if (i < size() - 1) {
                    sb.append(", ");
                }
            } catch (IndexOutOfBoundsException e) {
                // This should ideally not happen if size() is correct
                // Handle potential inconsistencies or errors gracefully
                 System.err.println("Error in toString: Index out of bounds at " + i + " for size " + size());
                 sb.append("ERROR"); // Indicate error in output
                 if (i < size() - 1) {
                     sb.append(", ");
                 }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Methods from MySortedList interface are declared abstract implicitly or implemented here/in subclasses
    // public abstract int size();
    // public abstract void clear();
    // public abstract int get(int index);
    // public abstract void add(int value);
    // public abstract void remove(int index);
    // public abstract int binarySearch(int value);
    // public abstract boolean contains(int value);
    // public abstract int[] toArray();
}
