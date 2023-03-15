package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskE {

    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] counts = new int[N];
        long result = 0;
        for (int i = 0; i < N; i++) {
            counts[i] = Integer.parseInt(br.readLine());
            if (i > 0) {
                result += Math.min(counts[i], counts[i-1]);
            }
        }
        System.out.println(result);
    }
}
