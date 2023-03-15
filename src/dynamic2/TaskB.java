package dynamic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskB {

    public static void main(String[] args) throws IOException {
        TaskB task = new TaskB();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] size = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] grid = new int[size[0]][size[1]];
        for (int i = 0; i < size[0]; ++i) {
            grid[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();
        String[][] directionGrid = new String[size[0]][size[1]];
        directionGrid[0][0] = "";
        for (int i = 0; i < size[0]; ++i) {
            for (int j = 0; j < size[1]; ++j) {
                if (i == j && j == 0) continue;
                int extra = -1;
                String direction = "";
                if (i > 0 && grid[i-1][j] > extra) {
                    direction = directionGrid[i-1][j] + " D";
                    extra = grid[i-1][j];
                }
                if (j > 0 && grid[i][j-1] > extra) {
                    direction = directionGrid[i][j-1] + " R";
                    extra = grid[i][j-1];
                }
                grid[i][j] += extra;
                directionGrid[i][j] = direction;
             }
        }
        System.out.println(grid[size[0]-1][size[1]-1]);
        System.out.println(directionGrid[size[0]-1][size[1]-1].trim());
    }
}
