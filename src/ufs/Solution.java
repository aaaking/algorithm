package ufs;

import java.util.*;

public class Solution {
    class Node {
        public int value;
        public int rank = 1;
        public Node parent = this;
        public int i;
        public int j;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return i == node.i && j == node.j;
        }

        @Override
        public String toString() {
            return "{" +
                    "i=" + i +
                    ", j=" + j +
                    ", rank=" + rank +
                    ", parent i=" + parent.i +
                    ", parent j=" + parent.j +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    /**
     * [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]]
     * <p>
     * [
     * ["1","1","1"],
     * ["0","1","0"],
     * ["1","1","1"]]
     * <p>
     * [
     * ["1","1","1","1","1","0","1","1","1","1"],
     * ["1","0","1","0","1","1","1","1","1","1"],
     * ["0","1","1","1","0","1","1","1","1","1"],
     * ["1","1","0","1","1","0","0","0","0","1"],
     * ["1","0","1","0","1","0","0","1","0","1"],5
     * ["1","0","0","1","1","1","0","1","0","0"],6
     * ["0","0","1","0","0","1","1","1","1","0"],7
     * ["1","0","1","1","1","0","0","1","1","1"],8
     * ["1","1","1","1","1","1","1","1","0","1"],9
     * ["1","0","1","1","1","1","1","1","1","0"]]10
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
//        char[][] chars = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//        char[][] chars = {{'1','1','0'},{'0','1','0'},{'1','1','1'}};
        char[][] chars = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
        };
        System.out.println(solution.numIslands(chars));
        ;
    }

    public int numIslandsDfs(char[][] grid) {
        return 1;
    }

    public int numIslandsBfs(char[][] grid) {
        return 1;
    }

    public int numIslands(char[][] grid) {
        Set<Node> datas = new HashSet<>();
        Set<Node> ret = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    Node node = checkNode(datas, i, j);

                    // left
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        Node nodeLeft = checkNode(datas, i, j - 1);
                        merge(node, nodeLeft);
                    }

                    // up
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        Node nodeUp = checkNode(datas, i - 1, j);
                        merge(node, nodeUp);
                    }

                    //right
                    if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
                        Node nodeRight = checkNode(datas, i, j + 1);
                        merge(node, nodeRight);
                    }

                    //down
                    if (i + 1 < grid.length && grid[i + 1][j] == '1') {
                        Node nodeDown = checkNode(datas, i + 1, j);
                        merge(node, nodeDown);
                    }
                }
            }
        }

        int rootNum = 0;
        for (Node node : datas) {
            if (node.parent == node) {
                rootNum++;
                System.out.println(node);
            }
        }
        System.out.println(datas);
        return rootNum;
    }

    private Node checkNode(Set<Node> datas, int i, int j) {
        Node node = new Node();
        node.i = i;
        node.j = j;
        for (Node nodeI : datas) {
            if (nodeI.equals(node)) {
                return nodeI;
            }
        }
        datas.add(node);
        return node;
    }

    private void merge(Node a, Node b) {
        Node aP = a.parent;
        Node bP = b.parent;
        if (aP.rank <= bP.rank) {
            aP.parent = bP;
        } else {
            bP.parent = aP;
        }
        if (aP.rank == bP.rank && aP != bP) {
            bP.rank += 1;
        }
    }
}
