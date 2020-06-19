package com.enjoy.foundation;

import java.util.*;

public class CollectionSample {

    public static void main(String[] args) {

        TreeMap treeMap=new TreeMap();
        treeMap.put(new A(3),"");
        treeMap.put(new A(3),"");

        System.out.println(treeMap);


//        Hashtable<A, String> map = new Hashtable<>();
//        map.put(new A(6000), "Java");
//        map.put(new A(3000), "C#");
//
//        for (Map.Entry<A, String> o : map.entrySet()) {
//            System.out.println(o.getKey()+":"+o.getValue());
//        }
//
//        Iterator<A> iterator = map.keySet().iterator();
//        A a = (A) iterator.next();
//        a.count = 3000;
//
//        map.remove(new A(3000));
//
//        System.out.println(map.get(new A(6000)));
//        System.out.println(map.get(new A(3000)));
//        System.out.println("-----------------");
//        for (Map.Entry<A, String> o : map.entrySet()) {
//            System.out.println(o.getKey()+":"+o.getValue());
//        }

//        Collection collection = getSetSample();
//        foreachCollection(collection);
//        iteratorCollection(collection);
//        deleteCollection(collection);
//        System.out.println(collection);
    }

    public static Collection getSetSample() {
        Collection list = new HashSet();
//        Collection list = new LinkedHashSet();
//        Collection list = new TreeSet();
//        Collection list = EnumSet.allOf();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        list.add("Java");
        list.add(null);
        list.add(null);
        return list;
    }

    public static Collection getListSample() {
//        Collection list = new ArrayList();
        Collection list = new LinkedList();
//        Collection list = new Stack();
//        Collection list = new Vector();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        return list;
    }

    private static void foreachCollection(Collection collection) {
        for (Object o : collection) {
            System.out.println(o);
        }
    }

    private static void iteratorCollection(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            System.out.println(object);
        }
    }

    private static void deleteCollection(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.equals("Java")) {
//                System.out.println(object);
                iterator.remove();
            }
        }
    }

    public static class A {

        private int count;

        public A(int count) {
            this.count = count;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj != null && obj.getClass() == A.class) {
                A a = (A) obj;
                return a.count == this.count;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.count;
        }
    }

}
