package graphs;

import java.util.*;

/**
 * We are interested in solving a maze represented
 * by a matrix of integers 0-1 of size nxm.
 * This matrix is a two-dimensional array.
 * An entry equal to '1' means that there
 * is a wall and therefore this position is not accessible,
 * while '0' means that the position is free.
 * We ask you to write a Java code to discover
 * the shortest path between two coordinates
 * on this matrix from (x1, y1) to (x2, y2).
 * The moves can only be vertical (up/down) or horizontal (left/right)
 * (not diagonal), one step at a time.
 * The result of the path is an Iterable of
 * coordinates from the origin to the destination.
 * These coordinates are represented by integers
 * between 0 and n * m-1, where an integer 'a'
 * represents the position x =a/m and y=a%m.
 * If the start or end position is a wall
 * or if there is no path, an empty Iterable must be returned.
 * The same applies if there is no path
 * between the origin and the destination.
 */
public class Maze {
    public static Iterable<Integer> shortestPath(int[][] maze, int x1, int y1, int x2, int y2) {
        int n = maze.length, m = maze[0].length;
        boolean[][] visited = new boolean[n][m];  // Visited array to keep track of visited cells

        // Check if start or end is a wall or out of bounds
        if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m || x2 < 0 || x2 >= n || y2 < 0 || y2 >= m ||
          maze[x1][y1] == 1 || maze[x2][y2] == 1) return new ArrayList<>();

        Queue<Integer> queue = new ArrayDeque<>();
        HashMap<Integer, Integer> parentMap = new HashMap<>();
        int startInd = ind(x1, y1, m);
        int endInd = ind(x2, y2, m);

        queue.add(startInd);
        visited[x1][y1] = true;
        parentMap.put(startInd, null);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentX = row(current, m), currentY = col(current, m);

            if (current == endInd) {
                return constructPath(parentMap, endInd);
            }

            // Check all 4 directions
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newX = currentX + dir[0], newY = currentY + dir[1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY] && maze[newX][newY] == 0) {
                    int newIndex = ind(newX, newY, m);
                    queue.add(newIndex);
                    visited[newX][newY] = true;
                    parentMap.put(newIndex, current);
                }
            }
        }
        return new ArrayList<>();
    }
    private static List<Integer> constructPath(HashMap<Integer,Integer> parentPath,Integer end){
        List<Integer> path = new ArrayList<>();
        Integer curr = end;
        while (curr != null){
            path.add(curr);
            curr= parentPath.get(curr);
        }
        Collections.reverse(path);
        return path;
    }
    public static int ind(int x, int y, int lg) {
        return x * lg + y;
    }

    public static int row(int pos, int mCols) {
        return pos / mCols;
    }

    public static int col(int pos, int mCols) {
        return pos % mCols;
    }
}
