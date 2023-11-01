import java.util.ArrayList;
import java.util.List;


public class MinHeap<T extends Comparable<T>> {
    private List<T> items;

    public MinHeap() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
        heapifyUp();
    }

    public T getMin() {
        if (items.size() == 0) return null;
        return items.get(0);
    }

    public T removeMin() {
        if (items.size() == 0) return null;

        T item = items.get(0);
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        heapifyDown();

        return item;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    private void heapifyUp() {
        int index = items.size() - 1;
        while (hasParent(index) && parent(index).compareTo(items.get(index)) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items.get(index).compareTo(items.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    private void swap(int index1, int index2) {
        T temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < items.size();
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < items.size();
    }

    private T parent(int index) {
        return items.get(getParentIndex(index));
    }

    private T leftChild(int index) {
        return items.get(getLeftChildIndex(index));
    }

    private T rightChild(int index) {
        return items.get(getRightChildIndex(index));
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();

        // Adding elements to the heap
        int[] numbersToAdd = {15, 10, 4, 17, 21, 2};
        System.out.println("Adding elements:");
        for (int number : numbersToAdd) {
            System.out.println(number);
            minHeap.add(number);
        }

        // Removing and printing elements in ascending order
        System.out.println("\nRemoving elements in ascending order:");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.removeMin());
        }
    }
}
