package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class TaskE {

    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[N];
        Arrays.fill(result,-1);
        for (int i = 0; i < N; i++) {
            int next = Integer.parseInt(nums[i]);
            while (stack.size() > 0 && stack.peek()[1] > next) {
                int[] prev = stack.pop();
                result[prev[0]] = i;
            }
            stack.push(new int[]{i, next});
        }
        for (int i = 0; i < N; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
