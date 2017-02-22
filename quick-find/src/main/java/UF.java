import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/2/21.
 */
public class UF {
    private int[] id;
    private int count;

    public UF(int n)
    {
        int i;

        count = n;
        id = new int[count];

        for (i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q)
    {
        int i;
        int backup_p;
        int backup_q;

        if (connected(p, q))
            return;

        backup_p = id[p];
        backup_q = id[q];

        for (i = 0; i < id.length; i++) {
            if (id[i] == backup_p)
                id[i] = backup_q;
        }

        count--;
    }

    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }

    public void show()
    {
        for (int i = 0; i < id.length; i++) {
            StdOut.println(id[i]);
        }
    }

    public static void main(String[] arguments)
    {
        int n = StdIn.readInt();

        UF uf = new UF(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q))
                continue;

            uf.union(p, q);
            StdOut.println(p + " " + q);
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
    }
}
