package com.gubin.MyArrayList;

import java.util.*;

class TestMyArrayList {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>(Arrays.asList("aaa", null, "bbb"));

        MyArrayList<String> arrayList = new MyArrayList<>(2);
        System.out.println(arrayList.toString());

        arrayList.add("honda");
        arrayList.add("nissan");
        arrayList.add(0, "toyota");
        System.out.println(arrayList.toString());



       /* Iterator<String> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        ListIterator<String> listIterator = arrayList.listIterator();
        System.out.println(listIterator.next());
        System.out.println(listIterator.next());
        System.out.println(listIterator.previous());
        listIterator.set("ford");
        System.out.println(arrayList.toString());

        arrayList.remove("nissan");
        System.out.println(arrayList.toString());

        arrayList.add("aaa");
        arrayList.addAll(2, list);
        System.out.println(arrayList.toString());

        arrayList.removeAll(list);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.indexOf("bbb"));


        arrayList.add("toyota");
        arrayList.add("dodge");
        arrayList.add("nissan");
        System.out.println(arrayList.toString());
        arrayList.retainAll(Arrays.asList("ford","dodge"));
        System.out.println(arrayList.toString());
    }
}
