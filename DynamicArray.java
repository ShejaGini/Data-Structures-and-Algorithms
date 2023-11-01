public class DynamicArray <T> {

    private T[] data;
    private int size = 0;
    private int capacity;

    public DynamicArray(int initialCapacity) {
        this.capacity = initialCapacity;
        data = (T[]) new Object[initialCapacity];
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }


    public void add(T value) {
        if (size == capacity) {
            resize(2 * capacity);  // Double the size if the array is full
        }
        data[size++] = value;
    }

    public int search(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;  // Return the index if value is found
            }
        }
        return -1;  // Return -1 if value is not found
    }

    public boolean delete(T value) {
        int index = search(value);
        if (index == -1) {
            return false;  // Value not found
        }
        
        // Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;  // Reduce the size of the dynamic array
        data[size] = null;  // Clear the last element
        
        // Optional: Halve the capacity if the size is less than 1/4 of the capacity
        if (size < capacity / 4 && capacity > 1) {
            resize(capacity / 2);
        }
        
        return true;  // Value deleted successfully
    }

    public void printArray() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    

    public static void main(String[] args) {
        DynamicArray<Integer> gini = new DynamicArray<>(5);

        gini.add(1);
        gini.add(3);

        gini.printArray();

        gini.add(4);
        

        


    }






    
}
