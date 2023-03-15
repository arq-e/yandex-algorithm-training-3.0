package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

public class TaskB {

    public static void main(String[] args) throws IOException {
        TaskB task = new TaskB();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] playerDeque = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Queue<Integer> firstPlayerCards = new ArrayDeque<>();
        for (int i : playerDeque) {
            firstPlayerCards.add(i);
        }
        playerDeque = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Queue<Integer> secondPlayerCards = new ArrayDeque<>();
        for (int i : playerDeque) {
            secondPlayerCards.add(i);
        }
        int i = 0;
        while (firstPlayerCards.size() > 0 && secondPlayerCards.size() > 0 && i < 1000000) {
            int first = firstPlayerCards.poll();
            int second = secondPlayerCards.poll();
            if (first > second) {
                if (first != 9 || second != 0) {
                    firstPlayerCards.add(first);
                    firstPlayerCards.add(second);
                } else {
                    secondPlayerCards.add(first);
                    secondPlayerCards.add(second);
                }
            } else if (second > first) {
                if (second != 9 || first != 0) {
                    secondPlayerCards.add(first);
                    secondPlayerCards.add(second);
                } else {
                    firstPlayerCards.add(first);
                    firstPlayerCards.add(second);
                }
            }
            ++i;
        }
        if (i == 1000000) {
            System.out.println("botva");
        } else {
            if (firstPlayerCards.size() != 0) {
                System.out.println("first " + i);
            } else {
                System.out.println("second " + i);
            }
        }
    }
}
