package linked_list;

public class MyLinkedList <T> {

    private MyNode<T> head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T value) {
        MyNode<T> newNode = new MyNode<T>(value);

        if (this.head == null) {
            this.head = newNode;
        } else {
            MyNode<T> temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return this.head.value;
    }

    public T get(int index) {
        if (index >= getSize()) {
            throw new IndexOutOfBoundsException("Nie ma elementu listy o indeksie " + index);
        }
        MyNode<T> temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

}
