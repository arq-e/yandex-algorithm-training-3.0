package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskC {
    public static void main(String[] args) throws IOException {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] pairs = new int[param[1]][];
        for (int i = 0; i < param[1]; ++i) {
            pairs[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();
        if (param[0] == 1) {
            System.out.println("NO");
        } else if (param[1] == 0) {
            System.out.println("YES");
        } else {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int i = 0; i < param[1];++i) {
                if (!map.containsKey(pairs[i][0])) {
                    map.put(pairs[i][0], new HashSet<>());
                }
                if (!map.containsKey(pairs[i][1])) {
                    map.put(pairs[i][1], new HashSet<>());
                }
                map.get(pairs[i][0]).add(pairs[i][1]);
                map.get(pairs[i][1]).add(pairs[i][0]);
            }

            int[] colors = new int[param[0]+1];
            String result = "";
            Set<Integer> colored = new HashSet<>();
            while(colored.size() < param[0]) {
                result = dfs(map, param, pairs, colors);
                if (result.equals("NO")) break;
                for (int i = 1; i < colors.length; ++i) {
                    if (colors[i] > 0) colored.add(i);
                }

            }
            System.out.println(result);
        }
    }

    private String dfs(Map<Integer, Set<Integer>> map, int[] param, int[][] pairs,int[] colors) {

        Stack<Integer> stack = new Stack<>();

        int start = 0;
        for (int i = 1; i < colors.length; ++i) {
            if (colors[i] == 0) {
                start = i;
                break;
            }
        }
        stack.add(start);
        colors[start] = 1;
        while (stack.size() > 0) {
            int curr = stack.pop();
            if (map.get(curr) != null) {
                for (int i : map.get(curr)) {
                    if (colors[i] == colors[curr]) return "NO";
                    else if (colors[i] == 0){
                        colors[i] = 3 - colors[curr];
                        stack.push(i);
                    }
                }
            }
        }
        return "YES";
    }
}
