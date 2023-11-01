class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;


    public LinkedList() {
        this.head = null;
    }

    // Add a new node to the end of the list
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode;
            tail = newNode;

        }
       
    }

    // Add a new node to the beginning of the list
    public void prepend(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    // Remove from the head
    public void removeHead() {
        if (head != null) {
            head = head.next;
        }
    }

    // Remove from the tail
    public void removeTail() {
        if (head == null) return;  // Empty list, nothing to remove

        if (head.next == null) {
            head = null;  // Only one node in the list
            return;
        }

        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    // Remove from the middle based on value
    public void remove(T value) {
        if (head == null) return;  // Empty list, nothing to remove

        if (head.data.equals(value)) {
            head = head.next;  // The head is the node to remove
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Method to search for a value in the list
    public boolean search(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return true; // Value found
            }
            current = current.next;
        }
        return false; // Value not found
    }

    
    
    

    // Display the list
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.display();  // 1 -> 2 -> 3 -> null

        list.prepend(0);
        list.display();  // 0 -> 1 -> 2 -> 3 -> null

        list.remove(2);
        list.display();  // 0 -> 1 -> 3 -> null
    }
}
