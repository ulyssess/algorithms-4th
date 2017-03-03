package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by wangaichao on 17/3/2.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int queueSize = 0;
    private Item queue[] = null;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int size = queueSize;

        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {

            if (size <= 0) {
                throw new java.util.NoSuchElementException();
            }

            Item item = null;

            int randomIndex = StdRandom.uniform(0, queue.length - 1);

            item = queue[randomIndex];

            size--;

            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private void queueResizeBig() {
        Item newQueue[] = (Item[]) new Object[queue.length * 2];

        for (int i = 0; i < queueSize; i++) {
            newQueue[i] = queue[i];
        }
         queue = newQueue;
    }

    private void queueResizeSmall() {
        Item newQueue[] = (Item[]) new Object[queue.length / 2];

        for (int i = 0; i < queueSize; i++) {
            newQueue[i] = queue[i];
        }

        queue = newQueue;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return queueSize == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return queueSize;
    }

    public void enqueue(Item item)           // add the item
    {
        if (queueSize == queue.length) {
            queueResizeBig();
        }

        queue[queueSize] = item;

        queueSize++;
    }

    public Item dequeue()                    // remove and return a random item
    {
        int backup;

        Item item = null;

        if (this.size() == 0)
            throw new java.util.NoSuchElementException();

        int randomIndex = StdRandom.uniform(0, queue.length - 1);

        backup = randomIndex;

        while (randomIndex >= 0 && randomIndex < queue.length && queue[randomIndex] == null) {
            randomIndex++;
        }

        // 如果没有找到可用的元素
        if (randomIndex == queue.length) {
            randomIndex = backup;
            while (randomIndex >= 0 && randomIndex < queue.length && queue[randomIndex] == null) {
                randomIndex--;
            }
        }

        item = queue[randomIndex];

        queue[randomIndex] = null;

        queueSize--;

        if (queueSize < queue.length/4) {
            queueResizeSmall();
        }

        return item;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        Item item = null;

        int randomIndex = StdRandom.uniform(0, queue.length - 1);

        item = queue[randomIndex];

        return item;
    }


    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
