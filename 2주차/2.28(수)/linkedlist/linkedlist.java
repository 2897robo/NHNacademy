interface list {
    void add(int value);
    void add(int index, int value);
    int remove(int index);
    int get(int index);
}

interface Iterator {
    boolean hasNext();
    int next();
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class linkedlist implements list {
    private Node head;
    private int size;

    public linkedlist() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int data;
        if (index == 0) {
            data = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return data;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }


    public static void main(String args[]) {
        linkedlist linking = new linkedlist();
        linking.add(1);
        linking.add(2);
        linking.add(3);
        linking.add(2, 9);
        linking.add(5);
        linking.remove(3);
        System.out.println(linking.get(0));
        System.out.println(linking.get(1));
        System.out.println(linking.get(2));
        System.out.println(linking.get(3));
        //System.out.println(linking.get(4));
    }
}