package datastructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * union find set, 并查集
 */
public class Ufs {
    public int[] parent;
    public int[] rank;

    /**
     * 指向的父结点的权值
     */
    public double[] weight;

    public int cnt;

    public int maxRank = 1;

    public Ufs(int n) {
        cnt = n;
        parent = new int[n];
        rank = new int[n];
        weight = new double[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
            weight[i] = 1;
        }
    }

    private int find(int x) {
        while (parent[x] != x) {
            weight[x] *= weight[parent[x]];
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
        // return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public void merge(int x, int y) {
        merge(x, y, 1);
    }

    public void merge(int x, int y, double timesP) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        int rankX = rank[xRoot];
        int rankY = rank[yRoot];
        if (rankX <= rankY) {
            parent[xRoot] = yRoot;
            rank[yRoot] += rankX;
            maxRank = Math.max(maxRank, rank[yRoot]);
            weight[xRoot] = weight[y] * timesP / weight[x];
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] += rankY;
            maxRank = Math.max(maxRank, rank[xRoot]);
            weight[yRoot] = weight[x] / timesP / weight[y];
        }
        --cnt;
    }

    public boolean isUnion(int x, int y) {
        return find(x) == find(y);
    }

    public Integer find(int x, Condition condition) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    interface Condition {
        boolean condition(int x);
    }
}
