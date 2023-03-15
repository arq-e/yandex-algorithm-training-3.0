package warmup;

import java.io.*;
import java.util.stream.Stream;

public class TaskI {

    public static void main(String[] args) throws IOException {
        TaskI task = new TaskI();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] settings = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] matrix = new int[settings[0]][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[][] preSumMatrix = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < preSumMatrix.length; ++i) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; ++j) {
                preSumMatrix[i][j] = sum;
                sum += matrix[i][j];
            }
            preSumMatrix[i][preSumMatrix[i].length-1] = sum;
        }
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < settings[2]; ++i) {
            int[] request = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int res = 0;
            for (int j = request[0]-1; j <= request[2]-1; ++j) {
                res += preSumMatrix[j][request[3]] - preSumMatrix[j][request[1]-1];
            }
            pw.println(res);
        }
        br.close();
        pw.close();
    }
}
