import edu.princeton.cs.algs4.*;

/**
 * Created by wangaichao on 17/2/24.
 */
public class PercolationStats {
    private double[] probabilitys;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        int openSites;
        probabilitys = new double[trials];

        // 测试trials次
        for (int i = 0; i < trials; i++) {
            Percolation pc = new Percolation(n);

            while (!pc.percolates()) {
                // 随机open
                openSites = StdRandom.uniform(0, n*n - 1);
                pc.open(openSites/n + 1, openSites%n + 1);
            }

            probabilitys[i] = (double)pc.numberOfOpenSites()/(n * n);

            if (n == 1)
                probabilitys[i] = 1.0;
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(probabilitys);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(probabilitys);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - (1.96 * stddev() / Math.sqrt(probabilitys.length));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + (1.96 * stddev() / Math.sqrt(probabilitys.length));
    }

    public static void main(String[] args)        // test client (described below)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        if (n <= 0)
            throw new java.lang.IllegalArgumentException("argument n ≤ 0");

        if (trials <= 0)
            throw new java.lang.IllegalArgumentException("argument trials ≤ 0");

        PercolationStats ps = new PercolationStats(n, trials);

        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
