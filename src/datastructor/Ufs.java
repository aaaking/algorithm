package datastructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * union find set, 并查集
 */
public class Ufs {
    /**
     * 连通分量的个数
     */
    private int count;
    private int[] parent;
    private int[] rank;

    public int getCount() {
        return count;
    }

    public Ufs(int n) {
        this.count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] <= rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else {
            parent[yRoot] = xRoot;
        }
        if (rank[xRoot] == rank[yRoot]) {
            rank[yRoot]++;
        }
        count--;
    }
}