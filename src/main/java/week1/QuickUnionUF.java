package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/2/21.
 */
public class QuickUnionUF {
    private int id[];
    private int count;

    public QuickUnionUF(int n)
    {
        count = n;
        id = new int[count];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q)
    {
        int p_root;
        int q_root;

        p_root = root(p);
        q_root = root(q);

        if (p_root == q_root)
            return;

        id[p_root] = q;

        count--;
    }

    public int root(int p)
    {
        while(p != id[p]) {
            p = id[p];
        }

        return p;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }


    public void show()
    {
        for (int i = 0; i < id.length; i++) {
            StdOut.println(id[i]);
        }
    }

    public static void main(String[] arguments)
    {
        long now_time = System.currentTimeMillis();

        int n = StdIn.readInt();

        QuickUnionUF uf = new QuickUnionUF(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q))
                continue;

            uf.union(p, q);
            //StdOut.println(p + " " + q);
        }

        StdOut.println("count = " + uf.count);

        StdOut.println(uf.connected(0, 0));
        StdOut.println(uf.connected(0, 1));
        StdOut.println(uf.connected(0, 2));
        StdOut.println(uf.connected(0, 5));
        StdOut.println(uf.connected(0, 6));
        StdOut.println(uf.connected(0, 7));

        StdOut.println(uf.connected(3, 4));
        StdOut.println(uf.connected(3, 8));
        StdOut.println(uf.connected(3, 9));

        StdOut.println(uf.connected(0, 9));

        StdOut.println(System.currentTimeMillis() - now_time);
    }
}
