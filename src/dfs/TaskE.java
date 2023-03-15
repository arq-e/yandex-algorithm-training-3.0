package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskE {
    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; ++i) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] colors = new int[N];
        Arrays.fill(colors, -1);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] == 1) {
                    if (!map.containsKey(i)) {
                        map.put(i, new HashSet<>());
                    }
                    map.get(i).add(j);
                }
            }
        }
        Set<Integer> coloured = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        while (coloured.size() < N) {
            res = dfs(N, map, colors, coloured);
            if (res.size() != 0) break;
        }
        if (res.size() == 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(res.size());
            for (int i : res) {
                System.out.print((i + 1) + " ");
            }
        }
    }

    private List<Integer> dfs(int N, Map<Integer, Set<Integer>> map, int[] colors,
                              Set<Integer> coloured) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; ++i) {
            if (colors[i] == -1) {
                if (map.get(i) != null && map.get(i).size() > 1) {
                    stack.push(i);
                    break;
                } else {
                    colors[i] = 2;
                    coloured.add(i);
                }

            }
        }
        if (stack.size() < 1) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        colors[stack.peek()] = 0;
        coloured.add(stack.peek());
        Set<Integer> currentUse = new HashSet<>();
        currentUse.add(stack.peek());
        int prev = -1;
        boolean cycleFound = false;
        int cycleEnd = 0;
        while(stack.size() > 0) {
            int curr = stack.pop();
            if (stack.size() > 0) {
                prev = stack.peek();
            } else prev = -1;
            stack.push(curr);
            if (colors[curr] == 0) {
                if (map.get(curr) != null && map.get(curr).size() < 2) {
                    if (prev != -1) map.get(prev).remove(curr);
                    colors[curr] = 2;
                    currentUse.remove(stack.pop());
                } else {
                    int count2 = 0;
                    for (int i : map.get(curr)) {
                        if (colors[i] == -1) {
                            stack.push(i);
                            colors[i] = 0;
                            coloured.add(i);
                            currentUse.add(i);
                            break;
                        } else if (i != prev && currentUse.contains(i)) {
                            cycleFound = true;
                            cycleEnd = i;
                        } else if (colors[i] == 2) {
                            ++count2;
                        }
                    }
                    if (count2 >= map.get(curr).size()-1) {
                        colors[curr] = 2;
                        currentUse.remove(stack.pop());
                    }
                }
            } else if (colors[curr] == 2) {
                currentUse.remove(stack.pop());
            }
            if (cycleFound) break;
        }
        if (cycleFound) {
            int curr = stack.pop();
            while (curr != cycleEnd) {
                res.add(curr);
                curr = stack.pop();
            }
            res.add(curr);
        }
        return res;
    }
}