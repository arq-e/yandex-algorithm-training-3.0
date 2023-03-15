package dfs;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class TaskD {

    public static void main(String[] args) throws IOException {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] edges = new int[param[1]][];
        for (int i = 0; i < param[1]; ++i) {
            edges[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        Map<Integer, Set<Integer>> incomingMap = new HashMap<>();
        Map<Integer, Set<Integer>> outgoingMap = new HashMap<>();
        for (int i = 0; i <= param[0]; ++i) {
            incomingMap.put(i, new HashSet<>());
            outgoingMap.put(i, new HashSet<>());
        }
        for (int i = 0; i < param[1]; ++i) {
            incomingMap.get(edges[i][1]).add(edges[i][0]);
            outgoingMap.get(edges[i][0]).add(edges[i][1]);
        }

        int[] colors = new int[param[0]+1];
        List<Integer> res = new ArrayList<>();
        boolean cycleFound = false;
        for (int i = 1; i <= param[0]; ++i) {
            if (colors[i] == 0) {
                cycleFound = dfs(param, colors, incomingMap, outgoingMap, res, i);
                if (cycleFound) break;
            }
        }
        if (cycleFound) {
            System.out.println(-1);
        } else {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
            for (int i = res.size()-1; i >= 0; --i) {
                pw.print(res.get(i) + " ");
            }
            pw.close();
        }
    }

    private boolean dfs(int[] param, int[] colors, Map<Integer, Set<Integer>> incomingMap,
                        Map<Integer,Set<Integer>> outgoingMap, List<Integer> res,
                        int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (stack.size() > 0) {
            int curr = stack.peek();
            if (outgoingMap.get(curr).size() == 0) {
                colors[curr] = 2;
                res.add(curr);
                stack.pop();
            } else {
                int count = 0;
                for (int i : outgoingMap.get(curr)) {
                    if (colors[i] == 1) return true;
                    else if (colors[i] == 0){
                        colors[i] = 1;
                        stack.push(i);
                        break;
                    } else ++count;
                }
                if (count == outgoingMap.get(curr).size()) {
                    res.add(curr);
                    colors[curr] = 2;
                    stack.pop();
                }
            }
        }
        return false;
    }
}
