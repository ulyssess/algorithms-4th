import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

    private int steps;
    private boolean isSolved;

    private MinPQ<Board> openList;
    private List<Board> closeList;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        Comparator<Board> comparato = new Comparator<Board>() {
            public int compare(Board o1, Board o2) {
                if (o1.hamming() == o2.hamming())
                    return o1.manhattan() - o2.manhattan();

                return o1.hamming() - o2.hamming();
            }
        };

        openList = new MinPQ<Board>(comparato);
        closeList = new ArrayList<Board>();

        openList.insert(initial);

        isSolved = dealPuzzle8();
    }

    private boolean inCloseList(Board node)
    {
        if (closeList.isEmpty())
            return false;

        for (Board board: closeList) {
            if (board.equals(node))
                return true;
        }

        return false;
    }

    private boolean dealPuzzle8()
    {
        Board searchNode;

        while (true) {
            // 从openList中获取一个board
            if (openList.isEmpty())
                return false;
            searchNode = openList.delMin();

            // 判断是否是正解
            if (searchNode.isGoal()) {
                closeList.add(searchNode);
                break;
            }

            // 将neighbor放入openList中
            for (Board board: searchNode.neighbors()) {
                // 排除在closeList中的board
                if (inCloseList(board))
                    continue;

                openList.insert(board);
            }

            // 将当前board放入closeList
            closeList.add(searchNode);

            // 增加移动步数
            steps++;
        }

        return true;
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return isSolved;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return closeList.size();
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        return closeList;
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