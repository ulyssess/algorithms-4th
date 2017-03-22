import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private int steps;

    private Node first = null;

    private class Node {
        private Board item;
        private Node next;
        private Node prev;
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    }

    public boolean isSolvable()            // is the initial board solvable?
    {

    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {

    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {

    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}