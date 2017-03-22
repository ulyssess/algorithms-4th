import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Board {

    private int[][] blocks;

    private int hannmingNumber;
    private int manhattanDistance;

    private Board[] neighbors;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null)
            throw new java.lang.NullPointerException();

        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++)
                this.blocks[i][j] = blocks[i][j];
        }
    }

    // board dimension n
    public int dimension()
    {
        return blocks.length;
    }

    public int hamming()                    // number of blocks out of place
    {
        this.hannmingNumber = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != i * blocks.length + j + 1)
                    this.hannmingNumber++;
        }

        return this.hannmingNumber;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int x;
        int y;
        this.manhattanDistance = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != 0) {
                    if (blocks[i][j] > blocks.length) {
                        x = blocks[i][j]/blocks.length;
                        y = blocks[i][j]%blocks.length;
                        if (blocks[i][j]%blocks.length == 0) {
                            x--;
                            y = blocks.length - 1;
                        } else {
                            y--;
                        }
                    } else {
                        x = 0;
                        y = blocks[i][j] - 1;
                    }
                    this.manhattanDistance += java.lang.Math.abs(x - i) + java.lang.Math.abs(y - j);
                }
            }
        }

        return this.manhattanDistance;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        int i;
        int j = 0;

        for (i = 0; i < blocks.length; i++) {
            for (j = 0; j < blocks.length; j++) {
                if (i == blocks.length - 1 && j == blocks.length - 1) {
                    if (blocks[i][j] != 0)
                        return false;
                    continue;
                }

                if (blocks[i][j] != i * blocks.length + j + 1)
                    return false;
            }
        }

        return true;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int i = 0;
        int j = 0;
        int x = 0;
        int y = 0;
        int stopFlag = 0;
        int temp;

        for (i = 0; i < blocks.length - 1 && stopFlag == 0; i++) {
            for (j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i + 1][j + 1] != 0) {
                    x = i;
                    y = i;
                    temp = blocks[i][j];
                    blocks[i][j] = blocks[i + 1][j + 1];
                    blocks[i + 1][j + 1] = temp;
                    stopFlag = 1;
                    break;
                }
            }
        }

        Board newBoard = new Board(this.blocks);

        temp = blocks[x][y];
        blocks[x][y] = blocks[x + 1][y + 1];
        blocks[x + 1][y + 1] = temp;

        return newBoard;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        int i;
        int j;

        if (y == null)
            throw new java.lang.NullPointerException();

        if (getClass() != y.getClass())
            return false;

        Board that = (Board) y;

        if (this.blocks.length != that.blocks.length)
            return false;

        for (i = 0; i < blocks.length; i++) {
            for (j = 0; j < blocks.length; j++) {
                if (this.blocks[i][j] != that.blocks[i][j])
                    return false;
            }
        }

        return true;
    }

    private class neighborIterator implements Iterator<Board> {

        private int index = 0;

        public boolean hasNext() {
            return index < neighbors.length;
        }

        public Board next() {
            if (hasNext()) {
                return neighbors[index++];
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private void findNeighbors()
    {
        int x = 0;
        int y = 0;
        int temp;
        int index;

        Board[] boardList;

        boardList = new Board[4];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (this.blocks[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        index = 0;

        // 上
        if (x - 1 > 0) {
            Board that = new Board(this.blocks);

            temp = that.blocks[x][y];
            that.blocks[x][y] = that.blocks[x - 1][y];
            that.blocks[x - 1][y] = temp;

            boardList[index++] = that;
        }

        // 下
        if (x + 1 < blocks.length) {
            Board that = new Board(this.blocks);

            temp = that.blocks[x][y];
            that.blocks[x][y] = that.blocks[x + 1][y];
            that.blocks[x + 1][y] = temp;

            boardList[index++] = that;
        }

        // 左
        if (y - 1 > 0) {
            Board that = new Board(this.blocks);

            temp = that.blocks[x][y];
            that.blocks[x][y] = that.blocks[x][y - 1];
            that.blocks[x][y - 1] = temp;

            boardList[index++] = that;
        }

        // 右
        if (y + 1 < blocks.length) {
            Board that = new Board(this.blocks);

            temp = that.blocks[x][y];
            that.blocks[x][y] = that.blocks[x][y + 1];
            that.blocks[x][y + 1] = temp;

            boardList[index++] = that;
        }

        if (index < 1)
            return;

        neighbors = new Board[index];

        for (int k = 0; k < index; k++) {
            neighbors[k] = boardList[k];
        }
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        if (neighbors == null) {
            findNeighbors();
        }

        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new neighborIterator();
            }
        };
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        int i;
        int j;
        String str;

        str = String.valueOf(blocks.length) + "\n";
        for (i = 0; i < blocks.length; i++) {
            for (j = 0; j < blocks.length; j++) {
                str += " ";
                str += String.valueOf(blocks[i][j]);
            }
            str += "\n";
        }

        str += "\n";

        return str;
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        StdOut.println(initial.toString());

        for (Board temp: initial.neighbors() ) {
            StdOut.println(temp.toString());
        }

        StdOut.println(initial.hamming());
        StdOut.println(initial.manhattan());

        StdOut.println(initial.twin().toString());

        if (initial.equals(initial.twin())) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }

        if (initial.equals(initial)) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }

        if (initial.isGoal()) {
            StdOut.println("is Goal");
        } else {
            StdOut.println("not Goal");
        }
    }
}