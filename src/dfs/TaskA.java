package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskA {

    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] edges = new int[param[1]][2];
        for (int i = 0; i < param[1]; ++i) {
            edges[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        if (param[0] == 0) {
            System.out.println(0);
        } else if (param[1] == 0) {
            System.out.println(1);
            System.out.println(1);
        } else {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int i = 0; i < param[1]; ++i) {
                if (!map.containsKey(edges[i][0])) {
                    map.put(edges[i][0], new HashSet<>());
                }
                if (!map.containsKey(edges[i][1])) {
                    map.put(edges[i][1], new HashSet<>());
                }
                map.get(edges[i][0]).add(edges[i][1]);
                map.get(edges[i][1]).add(edges[i][0]);
            }
            if (map.get(1) == null) {
                System.out.println(1);
                System.out.println(1);
            } else dfs(param, map);
        }
    }

    private void dfs(int[] param, Map<Integer, Set<Integer>> map) {
        Set<Integer> nodes = new HashSet<>();
        Stack<Integer> stack = new Stack();
        nodes.add(1);
        stack.add(1);
        while(stack.size() > 0) {
            int curr = stack.pop();
            for (int i: map.get(curr)) {
                if (!nodes.contains(i)) {
                    nodes.add(i);
                    stack.add(i);
                }
            }
        }
        List<Integer> res = new ArrayList<>(nodes);
        Collections.sort(res);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); ++i) {
            System.out.print(res.get(i) + " ");
        }
    }
}
