package ufs;

import datastructor.Ufs;

import java.util.*;

public class UfsSolution {

    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        // 空地的数量
        int spaces = 0;
        Ufs unionFind = new Ufs(rows * cols);
        int[][] directions = {{1, 0}, {0, 1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    spaces++;
                } else {
                    // 此时 grid[i][j] == '1'
                    for (int[] direction : directions) {
                        int newX = i + direction[0];
                        int newY = j + direction[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地
                        if (newX < rows && newY < cols && grid[newX][newY] == '1') {
                            unionFind.merge(getIndex(i, j), getIndex(newX, newY));
                        }
                    }
                }
            }
        }
        return unionFind.cnt - spaces;
    }

    private int getIndex(int i, int j) {
        return i * cols + j;
    }

    // 399. 除法求值 https://leetcode-cn.com/problems/evaluate-division/ todo ufs不太一样
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Ufs ufs = new Ufs(equations.size() * 2);
        int id = 0;
        Map<String, Integer> hashMap = new HashMap<>(2 * equations.size());
        for (int i = 0; i < equations.size(); i++) {
            List<String> strPair = equations.get(i);
            double times = values[i];
            String aStr = strPair.get(0);
            String bStr = strPair.get(1);
            int aId = 0;
            int bId = 0;
            if (!hashMap.containsKey(aStr)) {
                aId = id++;
                hashMap.put(aStr, aId);
            } else {
                aId = hashMap.get(aStr);
            }
            if (!hashMap.containsKey(bStr)) {
                bId = id++;
                hashMap.put(bStr, bId);
            } else {
                bId = hashMap.get(bStr);
            }
//            ufs.union(aId, bId, times);
        }

        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> strPair = queries.get(i);
            String aStr = strPair.get(0);
            String bStr = strPair.get(1);
            if (!hashMap.containsKey(aStr) || !hashMap.containsKey(bStr)) {
                ret[i] = -1;
                continue;
            }
            double times = 0; // ufs.calc(hashMap.get(aStr), hashMap.get(bStr));
            ret[i] = times;
        }
        return ret;
    }

    // 695. 岛屿的最大面积 https://leetcode-cn.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) { // todo review
        int m = grid.length;
        int n = grid[0].length;
        Ufs ufs = new Ufs(m*n);
        boolean hasIsland = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    hasIsland = true;
                    int index = i*n + j;
                    //up
                    if (i-1 >=0 && i-1<m && grid[i-1][j] == 1) {
                        ufs.merge(index, index-n);
                    }
                    //left
                    if (j-1 >=0 && j-1<n && grid[i][j-1] == 1) {
                        ufs.merge(index, index-1);
                    }
                    //down
                    if (i+1 >=0 && i+1<m && grid[i+1][j] == 1) {
                        ufs.merge(index, index+n);
                    }
                    //right
                    if (j+1 >=0 && j+1<n && grid[i][j+1] == 1) {
                        ufs.merge(index, index+1);
                    }
                }
            }
        }
        return hasIsland ? ufs.maxRank : 0;
    }

    // 886. 可能的二分法 https://leetcode-cn.com/problems/possible-bipartition/
    public boolean possibleBipartition(int n, int[][] dislikes) { // todo review
        int edgeCnt = dislikes.length;
        if (edgeCnt <= 0) {
            return true;
        }
        int cnt = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Ufs ufs = new Ufs(n+1);
        for (int i = 0; i < edgeCnt; i++) {
            int[] dislike = dislikes[i];
            List<Integer> dislikeA = map.getOrDefault(dislike[0], new ArrayList<>());
            dislikeA.add(dislike[1]);
            map.put(dislike[0], dislikeA);
            List<Integer> dislikeB = map.getOrDefault(dislike[1], new ArrayList<>());
            dislikeB.add(dislike[0]);
            map.put(dislike[1], dislikeB);
        }
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            int v = entry.getKey();
            List<Integer> myDislikes = entry.getValue();
            for (int x : myDislikes) {
                if (ufs.isUnion(v, x)) {
                    return false;
                }
                ufs.merge(x, myDislikes.get(0));
            }
        }
        return true;
    }

}
