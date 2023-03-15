package final_contest;

import java.io.*;
import java.util.*;

public class TaskA {

    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<String> stack = new Stack<>();
        Map<String, Stack<Integer>> counts = new HashMap<>();
        Map<String, Long> totalCounts = new HashMap<>();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; ++i) {
            String[] command = br.readLine().split(" ");
            if (command[0].equals("add")) {
                if (!counts.containsKey(command[2])) {
                    counts.put(command[2], new Stack());
                }
                int add = Integer.parseInt(command[1]);
                counts.get(command[2]).push(add);
                totalCounts.put(command[2],totalCounts.getOrDefault(command[2],0l)+add);
                stack.push(command[2]);
            } else if (command[0].equals("delete")) {
                long count = Integer.parseInt(command[1]);
                while (count > 0) {
                    String type = stack.pop();
                    int countOfType = counts.get(type).pop();
                    if (countOfType > count) {
                        counts.get(type).push((int)(countOfType-count));
                        totalCounts.put(type, totalCounts.get(type) - count);
                        stack.push(type);
                        break;
                    } else {
                        count -= countOfType;
                        totalCounts.put(type, totalCounts.get(type) - countOfType);
                    }
                }
            } else if (command[0].equals("get")) {
                if (!totalCounts.containsKey(command[1])) {
                    pw.println(0);
                } else {
                    pw.println(totalCounts.get(command[1]));
                }
            }
        }
        br.close();
        pw.close();
    }
}
