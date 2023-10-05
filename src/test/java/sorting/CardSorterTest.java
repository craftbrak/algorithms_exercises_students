package sorting;

import org.javagrader.Grade;
import org.javagrader.CustomGradingResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.*;

@Grade
public class CardSorterTest {

    // BEGIN STRIP
    public void test(int[] array) {
        LinkedListImpl l = new LinkedListImpl(array);

        CardSorter.sort(l);

        int pops = l.getnPops();
        int swaps = l.getnSwaps();

        assertTrue(l.isSorted());
        assertTrue(pops <= array.length * array.length);
        assertTrue(swaps <= array.length * array.length);

    }


    public void runAll(int[][] arrays)  {
        for (int[] i : arrays) {
            test(i);
        }
    }
    // END STRIP

    @Test
    public void testExample() {
        LinkedListImpl l = new LinkedListImpl(new int[]{7, 8, 2, 22, 102, 1});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    // BEGIN STRIP
    @Test
    @Grade(value = 100, custom = true, cpuTimeout = 1000)
    public void alreadySorted() throws CustomGradingResult {
        int[][] arrays = new int[25][100];

        Random r = new Random(582);

        for (int i = 0; i < 25; i++) {
            arrays[i][0] = r.nextInt() % 10000;
            for (int j = 1; j < 100; j++)
                arrays[i][j] = arrays[i][j - 1] + Math.abs((r.nextInt() % 10000));
        }

        runAll(arrays);
    }

    @Test
    @Grade(value = 100, custom = true, cpuTimeout = 1000)
    public void reverseSorted() throws CustomGradingResult {
        int[][] arrays = new int[25][100];

        Random r = new Random(582);

        for (int i = 0; i < 25; i++) {
            arrays[i][99] = r.nextInt() % 10000;
            for (int j = 98; j >= 0; j--)
                arrays[i][j] = arrays[i][j + 1] + Math.abs((r.nextInt() % 10000));
        }

        runAll(arrays);
    }

    @Test
    @Grade(value = 100, custom = true, cpuTimeout = 1000)
    public void allSortedButOne() throws CustomGradingResult {
        int[][] arrays = new int[25][100];

        Random r = new Random(582);

        for (int i = 0; i < 25; i++) {
            arrays[i][0] = r.nextInt() % 10000;
            for (int j = 1; j < 100; j++)
                arrays[i][j] = arrays[i][j - 1] + Math.abs((r.nextInt() % 10000));

            int pos = r.nextInt(99);
            arrays[i][pos] = arrays[i][99] + Math.abs((r.nextInt() % 10000));
        }

        runAll(arrays);
    }

    @Test
    @Grade(value = 100, custom = true, cpuTimeout = 1000)
    public void twoDifferent() throws CustomGradingResult {
        int[][] arrays = new int[25][100];

        Random r = new Random(582);

        for (int i = 0; i < 25; i++) {
            int a = r.nextInt(1000000);
            int b = r.nextInt(1000000);
            for (int j = 0; j < 100; j++)
                arrays[i][j] = r.nextBoolean() ? a : b;
        }

        runAll(arrays);
    }

    @Test
    @Grade(value = 100, custom = true, cpuTimeout = 1000)
    public void bimonotonous() throws CustomGradingResult {
        int[][] arrays = new int[25][100];

        Random r = new Random(582);

        for (int i = 0; i < 25; i++) {
            int midPos = r.nextInt(98) + 1;
            boolean direction = r.nextBoolean();
            int multiplier = direction ? -1 : 1;

            arrays[i][midPos] = r.nextInt(10000);
            for (int j = midPos - 1; j >= 0; j--)
                arrays[i][j] = arrays[i][j + 1] + multiplier * r.nextInt(10000);
            for (int j = midPos + 1; j < 100; j++)
                arrays[i][j] = arrays[i][j - 1] + multiplier * r.nextInt(10000);
        }

        runAll(arrays);
    }

    @Test
    @Grade(value = 500, custom = true, cpuTimeout = 10000)
    public void random() throws CustomGradingResult {
        int[][] arrays = new int[125][100];

        Random r = new Random(582);

        for (int i = 0; i < 125; i++) {
            for (int j = 0; j < 100; j++)
                arrays[i][j] = r.nextInt();
        }

        runAll(arrays);
    }
    // END STRIP
}


