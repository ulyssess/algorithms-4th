import java.util.Iterator;

/**
 * Created by wangaichao on 17/3/2.
 */
public class Deque<Item> implements Iterable<Item> {

    private int nodeSize = 0;
    private Node first = null;
    private Node last = null;

    // construct an empty deque
    public Deque() {

    }

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    private class DequeIterator implements Iterator<Item> {
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

    public boolean isEmpty()                 // is the deque empty?
    {
        return nodeSize == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return nodeSize;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node temp = new Node();
        temp.item = item;

        if (first == null) {
            first = temp;
            last = temp;
            temp.prev = null;
            temp.next = null;
        }
        else {
            temp.prev = null;
            temp.next = first;
            first = temp;
        }

        nodeSize++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node temp = new Node();
        temp.item = item;

        if (last == null) {
            last = temp;
            first = temp;
            temp.prev = null;
            temp.next = null;
        }
        else {
            temp.prev = last;
            temp.next = null;
            last = temp;
        }

        nodeSize++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException();

        Node old = null;

        if (this.size() == 1) {
            old = first;
            first = null;
            last = null;
        }
        else {
            old = first;
            first = old.next;
            first.prev = null;
        }

        nodeSize--;

        return old.item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException();

        Node old = null;

        if (this.size() == 1) {
            old = last;
            first = null;
            last = null;
        }
        else {
            old = last;
            last = old.prev;
            last.next = null;
        }

        nodeSize--;

        return old.item;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
