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
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(ind(x1,y1,maze[0].length));
//TOdo: fix index not using the good indexes , it should use the unflatend index
        HashMap<Integer,Integer> parentMap= new HashMap<>();
        parentMap.put(ind(x1,y1,maze[0].length),null);

        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            if (currentNode == ind(x2,y2,maze[0].length)){
                return constructPath(parentMap,ind(x2,y2,maze[0].length));
            }
            for (int i = 0; i < maze[currentNode].length; i++) {
                if (maze[currentNode][i]!=-0 && !parentMap.containsKey(i)){
                    parentMap.put(i,currentNode);
                    queue.add(i);
                };
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
