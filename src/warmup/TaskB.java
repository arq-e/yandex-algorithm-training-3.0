package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class TaskB {

    public static void main(String[] args) throws IOException {
        TaskB task = new TaskB();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String str = br.readLine();
        if (k+1 >= str.length()) {
            System.out.println(str.length());
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); ++i) {
            set.add(str.charAt(i));
            if (set.size() == 26) break;
        }
        int max = k+1;
        for (char ch : set) {
            int[] prefixSum = new int[str.length()+1];
            int count = 0;
            int firstPos = -1;
            for (int i = 0; i < str.length(); ++i) {
                prefixSum[i] = count;
                if (str.charAt(i) != ch) {
                    ++count;
                } else if (firstPos == -1) {
                    firstPos = i;
                }
            }
            prefixSum[str.length()] = count;
            int j = 0;
            for (int i = 0; i < str.length(); ++i) {
                while (j + 1 < prefixSum.length && prefixSum[j+1] - prefixSum[i] <= k) {
                    ++j;
                }
                if (j - i > max) {
                    max = j - i;
                }
            }
        }
        System.out.println(max);
    }
}
