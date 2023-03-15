package dfs;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class TaskB {
    public static void main(String[] args) throws IOException {
        TaskB task = new TaskB();
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
            System.out.println(param[0]);
            for (int i = 1; i <= param[0]; ++i) {
                System.out.println(1);
                System.out.println(i);
            }
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
            Set<Integer> allNodes = new HashSet<>();
            for (int i = 1; i <= param[0]; ++i) {
                allNodes.add(i);
            }

            int[] colors = new int[param[0]+1];
            Map<Integer, Set<Integer>> components = new HashMap<>();
            while (allNodes.size() > 0) {
                dfs(colors,map, allNodes, components);
            }
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
            pw.println(components.size());
            for (Set<Integer> i : components.values()) {
                pw.println(i.size());
                for (int j : i) {
                    pw.print(j + " ");
                }
                pw.println();
            }
            pw.close();
        }
    }

    private void dfs(int[] colors, Map<Integer, Set<Integer>> map, Set<Integer> allNodes,
                     Map<Integer, Set<Integer>> components) {
        Set<Integer> nodes = new HashSet<>();
        Stack<Integer> stack = new Stack();
        for (int i = 1; i < colors.length; ++i) {
            if (colors[i] == 0) {
                allNodes.remove(i);
                colors[i] = 1;
                if (map.get(i) != null) {
                    stack.add(i);
                    nodes.add(i);
                    break;
                } else {
                    nodes.add(i);
                    components.put(components.size(), new HashSet<>(nodes));
                    nodes.clear();
                }
            }
        }
        while(stack.size() > 0) {
            int curr = stack.pop();
            for (int i: map.get(curr)) {
                if (!nodes.contains(i) && colors[i] == 0) {
                    nodes.add(i);
                    stack.add(i);
                    colors[i] = 1;
                    allNodes.remove(i);
                }
            }
        }
        if (nodes.size() > 0) components.put(components.size(), nodes);
    }
}
