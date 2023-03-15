package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskJ {

    public static void main(String[] args) throws IOException {
        TaskJ task = new TaskJ();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        br.close();
        Map<Character, Long> map = new HashMap<>();
        long currCount = 0;
        int extra = word.length();
        long res = 0;
        for (int i = 0; i < word.length(); i++) {
            currCount += extra;
            extra -= 2;
            map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0L) + currCount);
            res += currCount;
        }
        List<Character> symbols = new ArrayList<>(map.keySet());
        Collections.sort(symbols);
        for (Character sym : symbols) {
                System.out.println(sym + ": " + map.get(sym));
        }
        System.out.println(res);
    }
}
