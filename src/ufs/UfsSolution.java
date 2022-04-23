package ufs;

import datastructor.Ufs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                            unionFind.union(getIndex(i, j), getIndex(newX, newY));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
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

}
