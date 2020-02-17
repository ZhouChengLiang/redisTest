package com.zcl.redisTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
//        System.out.println(list);
//        list.addFirst(0);
//        System.out.println(list);
//        list.addLast(6);
//        System.out.println(list);
//        System.out.println(list.remove());
//        System.out.println(list.removeFirst());
//        System.out.println(list.removeLast());
//        System.out.println(list);
//        System.out.println(list.poll());//返回并移除头节点
//        System.out.println(list);

//        System.out.println(list.peek());//返回头节点
//        System.out.println(list);

//        System.out.println(list.peekLast());//返回尾节点
//        System.out.println(list);

//        List<Integer> list1 = Arrays.asList(1, 2, 3);
//        list1.set(0, 4);
//        System.out.println(list1);
        
        
        LinkedList<Integer> origin = new LinkedList<>();
        for(int i= 0 ;i < 10;i++) {
            ListIterator<Integer> li = origin.listIterator(origin.size()/2);
            li.add(i);
//            System.out.println(origin);
//            System.out.println("######################");
        }
        
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        for(int i = list2.size()-1;i >= 0;i--) {
            System.out.print(list2.get(i));
        }
        System.out.println();
        System.out.println("#############1############");
        
        ListIterator<Integer> listIterator = list2.listIterator(list2.size());
        while(listIterator.hasPrevious()) {
            System.out.print(listIterator.previous());
        }
        System.out.println();
        System.out.println("#############2############");
        
        Stack<Integer> stacks = new Stack<>();
        stacks.addAll(list2);
        while(!stacks.isEmpty()) {
            System.out.print(stacks.pop());
        }
        System.out.println();
        System.out.println("#############3############");
        
        Random rand = new Random();
        for(int i = 0; i< 10; i++) {
            System.out.println(rand.nextInt(30));
        }
        System.out.println("#############4############");
        Queue<Integer> pq = new PriorityQueue<>();
        for(int i = 0;i < 10;i++) {
            pq.offer(rand.nextInt(i+10));
        }
        printQ(pq);
        System.out.println();
        System.out.println("#############5############");
        pq.offer(1);
        pq.offer(3);
        pq.offer(5);
        pq.offer(11);
        pq.offer(4);
        pq.offer(6);
        pq.offer(7);
        printQ(pq);
        System.out.println();
        System.out.println(7/2);
        Integer[] arrInt = new Integer[] {1,3,5,7,9,11};
        int right = 3;
        int left = 2;
        Integer a = arrInt[right = left];
        System.out.println("a >>> "+a);
    }
    
    @SuppressWarnings("rawtypes")
    public static void printQ(Queue queue) {
        while(queue.peek() != null) {
            System.out.print(queue.remove()+" ");
        }
    }
    
}
