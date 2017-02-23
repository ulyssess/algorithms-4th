import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by wangaichao on 17/2/23.
 */
public class Percolation {

    private static final int BLOCKED = 0;
    private static final int OPENED = 1;


    private int gridSize = 0;
    private int openedSites = 0;
    private int[][] gridStatus;             // 0 - blocked 1 - opened

    private int topVirtualSiteIndex;
    private int bottomVirtualSiteIndex;

    WeightedQuickUnionUF UF;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("argument n ≤ 0");

        gridSize = n;
        UF = new WeightedQuickUnionUF(gridSize * gridSize + 2); // add two virtual site

        gridStatus = new int[gridSize][gridSize];

        // init top virtual site
        topVirtualSiteIndex = gridSize*gridSize;
        for (int i = 0; i < gridSize; i++) {
            UF.union(i, topVirtualSiteIndex);
        }

        // init bottom virtual site
        bottomVirtualSiteIndex = gridSize*gridSize + 1;
        for (int i = (gridSize - 1) * gridSize; i < gridSize * gridSize; i++) {
            UF.union(i, bottomVirtualSiteIndex);
        }
    }

    // validate that p and q is a valid index
    private void validate(int p, int q)
    {
        if (p < 1 || p > gridSize) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + gridSize);
        } else if (q < 1 || q > gridSize) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + gridSize);
        }

    }

    public int getIndex(int row, int col)
    {
        return (row - 1) * gridSize + col - 1;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        validate(row, col);

        if (isOpen(row, col))
            return;

        // open site
        gridStatus[row - 1][col - 1] = OPENED;
        openedSites++;

        // union around site
        // 上
        if (row > 1 && isOpen(row - 1, col)) {
            UF.union(getIndex(row, col), getIndex(row - 1, col));
        }

        // 下
        if (row < gridSize && isOpen(row + 1, col)) {
            UF.union(getIndex(row, col), getIndex(row + 1, col));
        }

        // 左
        if (col > 1 && isOpen(row, col - 1)) {
            UF.union(getIndex(row, col), getIndex(row, col - 1));
        }

        // 右
        if (col < gridSize && isOpen(row, col + 1)) {
            UF.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return gridStatus[row - 1][col - 1] == OPENED;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        validate(row, col);

        if (!isOpen(row, col))
            return false;

        return UF.connected(getIndex(row, col), topVirtualSiteIndex);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openedSites;
    }

    public boolean percolates()              // does the system percolate?
    {
        return UF.connected(topVirtualSiteIndex, bottomVirtualSiteIndex);
    }

    public static void main(String[] args)   // test client (optional)
    {

    }
}
