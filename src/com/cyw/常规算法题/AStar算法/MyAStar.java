package com.cyw.常规算法题.AStar算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyuwei
 * @create 2020-07-22-23:27
 */
public class MyAStar {
    public static final int[][] MAZE = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    static class Grid {
        public int x;
        public int y;
        public int f;
        public int g;
        public int h;
        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if (parent != null) {
                this.g = parent.g + 1;
            } else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }
    }

    public static Grid aStarSearch(Grid start, Grid end) {
        //创建两个集合用于存储已探索的方格和未探索的方格
        ArrayList<Grid> openList = new ArrayList<>();
        ArrayList<Grid> closeList = new ArrayList<>();
        //将起点放入未探索的集合中
        openList.add(start);
        while (openList.size() > 0) {
            //在未探索的集合中找到f值最小的方格作为当前方格
            Grid currentGrid = findMinGrid(openList);
            //从未探索集合中移除当前节点，并将其放入已探查节点中
            openList.remove(currentGrid);
            closeList.add(currentGrid);
            //找到当前节点的所有相邻节点
            List<Grid> neighbors = findNeighborGrid(currentGrid, openList, closeList);
            for (Grid grid : neighbors) {
                if (!openList.contains(grid)) {
                    //如果临近节点不包含在openList中，则对其进行g，f，h以及parent的标记，并加入到openList中
                    grid.initGrid(currentGrid, end);
                    openList.add(grid);
                }
            }
            for (Grid grid : openList) {
                if (grid.x == end.x && grid.y == end.y) {
                    return grid;
                }
            }
        }
        return null;
    }

    private static List<Grid> findNeighborGrid(Grid currentGrid, ArrayList<Grid> openList, ArrayList<Grid> closeList) {
        ArrayList<Grid> grids = new ArrayList<>();
        if (isValidGrid(currentGrid.x, currentGrid.y - 1, openList, closeList)) {
            grids.add(new Grid(currentGrid.x, currentGrid.y - 1));
        }
        if (isValidGrid(currentGrid.x, currentGrid.y + 1, openList, closeList)) {
            grids.add(new Grid(currentGrid.x, currentGrid.y + 1));
        }
        if (isValidGrid(currentGrid.x - 1, currentGrid.y, openList, closeList)) {
            grids.add(new Grid(currentGrid.x - 1, currentGrid.y));
        }
        if (isValidGrid(currentGrid.x + 1, currentGrid.y, openList, closeList)) {
            grids.add(new Grid(currentGrid.x + 1, currentGrid.y));
        }
        return grids;
    }

    private static boolean isValidGrid(int x, int y, ArrayList<Grid> openList, ArrayList<Grid> closeList) {
        //是否超过边界
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        //是否有障碍物
        if (MAZE[x][y] == 1) {
            return false;
        }
        //是否包含在未探索和已探索两个集合中
        if (isContainsGrid(x, y, openList)) {
            return false;
        }
        if (isContainsGrid(x, y, closeList)) {
            return false;
        }
        return true;
    }

    private static boolean isContainsGrid(int x, int y, ArrayList<Grid> gridList) {
        for (Grid n : gridList) {
            if (n.x == x && n.y == y) {
                return true;
            }
        }
        return false;
    }

    private static Grid findMinGrid(ArrayList<Grid> openList) {
        Grid tempGrid = openList.get(0);
        for (Grid n : openList) {
            if (tempGrid.f > n.f) {
                tempGrid = n;
            }
        }
        return tempGrid;
    }

    public static void main(String[] args) {
        //设置起点和终点
        Grid startGrid = new Grid(2, 1);
        Grid endGrid = new Grid(2, 5);
        //使用A*算法找到终点
        Grid resultGrid = aStarSearch(startGrid, endGrid);
        //回溯迷宫路径
        ArrayList<Grid> path = new ArrayList<>();
        while (resultGrid != null) {
            path.add(new Grid(resultGrid.x, resultGrid.y));
            resultGrid = resultGrid.parent;
        }
        //输出迷宫路径，路径用*表示
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[0].length; j++) {
                if (isContainsGrid(i, j, path)) {
                    System.out.print("* ");
                } else {
                    System.out.print(MAZE[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
