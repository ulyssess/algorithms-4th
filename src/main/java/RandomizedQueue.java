import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by wangaichao on 17/3/2.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int nodeSize = 0;
    private Node first = null;

    private class Node {
        private Item item;
        private Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node now = first;

        public boolean hasNext() {
            return now != null;
        }

        public Item next() {
            if (now == null) {
                throw new java.util.NoSuchElementException();
            }

            Node temp = now;
            now = now.next;

            return temp.item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return nodeSize == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return nodeSize;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node temp = new Node();
        temp.item = item;

        if (first == null) {
            first = temp;
            temp.next = null;
        }
        else {
            temp.next = first;
            first = temp;
        }

        nodeSize++;
    }

    public Item dequeue()                    // remove and return a random item
    {
        int i;

        if (this.isEmpty())
            throw new java.util.NoSuchElementException();

        Node now = null;
        Node prev = null;

        if (this.size() == 1) {
            now = first;
            first = null;
        }
        else {
            int index = StdRandom.uniform(0, nodeSize);
            for (i = 0, now = first; i < index; i++) {
                prev = now;
                now = now.next;
            }

            if (prev != null) {
                prev.next = now.next;
            }
            else {
                first = now.next;
            }
        }

        nodeSize--;

        return now.item;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        int i;

        if (this.isEmpty())
            throw new java.util.NoSuchElementException();

        Node now = null;
        Node prev = null;

        if (this.size() == 1) {
            now = first;
        }
        else {
            int index = StdRandom.uniform(0, nodeSize);
            for (i = 0, now = first; i < index; i++) {
                prev = now;
                now = now.next;
            }
        }

        return now.item;
    }


    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
