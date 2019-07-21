package linked_list;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");

        System.out.println(myLinkedList.getFirst());



    }
}
