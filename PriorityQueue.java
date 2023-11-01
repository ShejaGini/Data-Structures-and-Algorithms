import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<T> {
    private ArrayList<Element> heap;
    private Comparator<T> comparator;

    public PriorityQueue(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    private class Element {
        T value;
        int priority;

        public Element(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    public void insert(T value, int priority) {
        Element element = new Element(value, priority);
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T extract() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        Element result = heap.get(0);
        Element lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return result.value;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.get(0).value;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Element current = heap.get(index);
            Element parent = heap.get(parentIndex);
            if (comparator.compare(current.value, parent.value) > 0) {
                heap.set(index, parent);
                heap.set(parentIndex, current);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (index < heap.size() / 2) {  // While the current node has at least one child
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largerChildIndex = leftChildIndex;
            if (rightChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex).value, heap.get(rightChildIndex).value) < 0) {
                largerChildIndex = rightChildIndex;
            }

            if (comparator.compare(heap.get(index).value, heap.get(largerChildIndex).value) < 0) {
                Element tmp = heap.get(index);
                heap.set(index, heap.get(largerChildIndex));
                heap.set(largerChildIndex, tmp);
                index = largerChildIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of the priority queue with integer elements and natural ordering
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));

        // Insert elements with associated priorities
        pq.insert(10, 1);
        System.out.println("Inserted 10 with priority 1");

        pq.insert(20, 2);
        System.out.println("Inserted 20 with priority 2");

        pq.insert(15, 3);
        System.out.println("Inserted 15 with priority 3");

        // Extract the highest priority element
        int max = pq.extract();
        System.out.println("Extracted element with highest priority: " + max);

        // Peek at the highest priority element
        int maxPeek = pq.peek();
        System.out.println("Peeked at element with highest priority: " + maxPeek);

        // Extract remaining elements
        while (!pq.isEmpty()) {
            int elem = pq.extract();
            System.out.println("Extracted: " + elem);
        }
    }



}
