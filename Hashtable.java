import java.util.LinkedList;

class HashTable<K, V> {
    private class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] chainArray;
    private int capacity; // size of the hash table
    private int size; // number of key-value pairs

    public HashTable() {
        chainArray = new LinkedList[10];
        capacity = 10;
        size = 0;

        // Initialize the chains
        for (int i = 0; i < capacity; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void add(K key, V value) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> head = chainArray[index];

        // Check if key is already present
        for (HashNode<K, V> node : head) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        size++;
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        head.addFirst(newNode);
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> head = chainArray[index];

        // Search the chain at index
        for (HashNode<K, V> node : head) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        // Key not found
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        LinkedList<HashNode<K, V>> head = chainArray[index];
        HashNode<K, V> prev = null;

        // Search for key
        for (HashNode<K, V> node : head) {
            if (node.key.equals(key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    head.remove(node);
                    size--;
                    return node.value;
                }
            }
            prev = node;
        }

        // Key not found
        return null;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        
        HashTable<String,String> gini = new HashTable();

        gini.add("Gini","Sheja");

        System.out.println(gini);

    }
}
