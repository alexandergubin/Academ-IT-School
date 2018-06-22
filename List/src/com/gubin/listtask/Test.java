package com.gubin.listtask;

public class Test {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> myList = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> myList2;

        System.out.println("add elements  1,2,3,4:");
        System.out.println(myList.toString());
        myList.addLast(null);
        System.out.println(myList.toString());
        myList.addFirst(2);
        myList.addFirst(1);
        myList.addLast(3);
        myList.add(0, 4);
        myList.addLast(null);
        System.out.println(myList.toString());

        System.out.println("copy list to myList2");
        myList2 = myList.copy();
        System.out.println(myList2.toString());

        System.out.println("reverse list");
        System.out.println(myList.reverseList().toString());

        System.out.println("remove element with index = 1");
        myList.remove(1);
        System.out.println(myList.toString());

        System.out.println("remove first element");
        myList.removeFirst();
        System.out.println(myList.toString());

        System.out.println("remove element \"2\"");
        myList.remove((Integer) 2);
        System.out.println(myList.toString());

        System.out.println("add last \"7\"");
        myList.addLast(7);
        System.out.println(myList.toString());
    }
}
