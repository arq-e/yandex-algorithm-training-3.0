package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskH {

    public static void main(String[] args) throws IOException {
        TaskH task = new TaskH();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int[] minCoordinates = new int[]{1000000000,1000000000};
        int[] maxCoordinates = new int[]{-1000000000,-1000000000};
        for (int i = 0;i < K; i++) {
            int[] coordinates = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (coordinates[0] < minCoordinates[0]) {
                minCoordinates[0] = coordinates[0];
            }
            if (coordinates[0] > maxCoordinates[0]) {
                maxCoordinates[0] = coordinates[0];
            }
            if (coordinates[1] < minCoordinates[1]) {
                minCoordinates[1] = coordinates[1];
            }
            if (coordinates[1] > maxCoordinates[1]) {
                maxCoordinates[1] = coordinates[1];
            }
        }
        System.out.println(minCoordinates[0] + " " + minCoordinates[1]
                + " " + maxCoordinates[0] + " " + maxCoordinates[1]);
    }
}
