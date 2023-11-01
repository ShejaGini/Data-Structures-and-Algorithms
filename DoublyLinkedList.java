// Node class to represent each element in the doubly linked list
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Doubly linked list class
class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Inserts a new node at the beginning of the list
    public void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Inserts a new node at the end of the list
    public void insertAtTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Removes the node at the beginning of the list
    public void removeAtHead() {
        if (head == null) return;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    // Removes the node at the end of the list
    public void removeAtTail() {
        if (tail == null) return;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    // Removes a node with the given data from the list
    public void remove(T data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            removeAtHead();
            return;
        }
        if (tail.data.equals(data)) {
            removeAtTail();
            return;
        }

        Node<T> current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }

        if (current != null) {
            current.prev.next = current.next;
            if (current.next != null) {
                current.next.prev = current.prev;
            }
        }
    }

    // Searches for a node with the given data and returns it
    public Node<T> search(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Converts the list to a string for easy visualization
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.next;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 5); // Remove last arrow
        }
        return sb.toString();
    }


public static void main(String[] args) {
    
}

}