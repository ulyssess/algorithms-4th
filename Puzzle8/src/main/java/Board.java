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
                    x = blocks[i][j]/blocks.length + 1;
                    y = blocks[i][j] % blocks.length - 1;
                    this.manhattanDistance += (x + y) - (i + j);
                }
            }
        }

        return this.manhattanDistance;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        int i;
        int j;

        for (i = 0; i < blocks.length; i++) {
            for (j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != i * blocks.length + j + 1)
                    return false;
            }
        }

        if (blocks[i][j] != 0)
            return false;

        return true;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int i;
        int j;
        int temp;

        for (i = 0; i < blocks.length - 1; i++) {
            for (j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i + 1][j + 1] != 0) {
                    temp = blocks[i][j];
                    blocks[i][j] = blocks[i + 1][j + 1];
                    blocks[i + 1][j + 1] = temp;
                    break;
                }
            }
        }

        Board newBoard = new Board(this.blocks);

        temp = blocks[i][j];
        blocks[i][j] = blocks[i + 1][j + 1];
        blocks[i + 1][j + 1] = temp;

        return newBoard;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        int i;
        int j;

        if (y == null)
            throw new java.lang.NullPointerException();

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
                str += String.valueOf(blocks[i][j]);
            }
            str += "\n";
        }

        str += "\n";

        return str;
    }

    public static void main(String[] args) // unit tests (not graded)
    {

    }
}