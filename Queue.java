public class Queue<T> {
    private Node<T> front, rear;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(T item) {
        Node<T> temp = front;
        while (temp != null) {
            if (temp.data.equals(item)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }
        if (front.data.equals(item)) {
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            return true;
        }

        Node<T> temp = front;
        while (temp.next != null && !temp.next.data.equals(item)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            if (temp.next == rear) {
                rear = temp;
            }
            temp.next = temp.next.next;
            size--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.contains(2));  // Outputs: true
        System.out.println(queue.remove(2));    // Outputs: true
        System.out.println(queue.contains(2));  // Outputs: false
        System.out.println(queue.peek());       // Outputs: 1
        System.out.println(queue.dequeue());    // Outputs: 1
        System.out.println(queue.size());       // Outputs: 1
    }
}
