import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/3/3.
 */
public class Permutation {
    public static void main(String[] args)
    {
        int size = 0;

        size = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            randomizedQueue.enqueue(temp);
        }

        for (int i = 0; i < size; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}

