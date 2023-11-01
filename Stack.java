public class Stack<T> {

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> top;
    private int size = 0;

    // Push an item onto the stack
    public void push(T item) {
        Node<T> t = new Node<T>(item);
        t.next = top;
        top = t;
        size++;
    }

    // Remove and return the top item from the stack
    public T pop() {
        if (top == null){
            throw new EmptyStackException();

        }

        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    // Return the top item without popping it
    public T peek() {
        if (top == null){
            throw new EmptyStackException();
        }

        return top.data;
    }

    // Return true if the stack is empty, false otherwise
    public boolean isEmpty() {
        return top == null;
    }

    // Return the number of items in the stack
    public int size() {
        return size;
    }

    // Exception for when attempting to pop or peek from an empty stack
    public static class EmptyStackException extends RuntimeException {
        public EmptyStackException() {
            super("Stack is empty");
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top item: " + stack.peek());
        System.out.println("Stack size: " + stack.size());

        System.out.println("Popped item: " + stack.pop());
        System.out.println("Stack size after pop: " + stack.size());
    }
}
