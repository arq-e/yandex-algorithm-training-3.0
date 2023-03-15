package dynamic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskA {
    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
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
        for (int i = 0; i < size[0]; ++i) {
            for (int j = 0; j < size[1]; ++j) {
                int extra = 10001;
                if (i > 0 && grid[i-1][j] < extra) {
                    extra = grid[i-1][j];
                }
                if (j > 0 && grid[i][j-1] < extra) {
                    extra = grid[i][j-1];
                }
                if (extra < 10001) grid[i][j] += extra;
            }
        }
        System.out.println(grid[size[0]-1][size[1]-1]);
    }
}
