package warmup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class TaskA {

    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
        task.solve();
    }

    public void solve() throws IOException {
        int[] frequencies = new int[128];
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String str = br.lines().collect(Collectors.joining("\n"));
        br.close();
        int maxFrequency = 0;
        for (int i = 0; i < str.length(); i++) {
            char sym = str.charAt(i);
            if (sym != ' ' && sym != '\n') {
                ++frequencies[sym];
                if (frequencies[sym] > maxFrequency) {
                    maxFrequency = frequencies[sym];
                }
            }
        }
        while (maxFrequency > 0) {
            for (int frequency : frequencies) {
                if (frequency != 0) {
                    if (frequency >= maxFrequency) {
                        System.out.print('#');
                    } else {
                        System.out.print(' ');
                    }
                }
            }
            System.out.println();
            --maxFrequency;
        }
        for (int i = 0; i < frequencies.length; ++i) {
            if (frequencies[i] != 0) {
                System.out.print((char)i);
            }
        }
    }
}
