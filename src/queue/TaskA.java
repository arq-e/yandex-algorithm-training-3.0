package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskA {

    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        Queue<Integer> queue = new ArrayDeque<>();
        while (!command.equals("exit")) {
            switch (command) {
                case "pop":
                    if (queue.size() == 0) {
                        System.out.println("error");
                    } else {
                        System.out.println(queue.poll());
                    }
                    break;
                case "front":
                    if (queue.size() == 0) {
                        System.out.println("error");
                    } else {
                        System.out.println(queue.peek());
                    }
                    break;
                case "clear":
                    queue.clear();
                    System.out.println("ok");
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                default:
                    String[] commandSplit = command.split(" ");
                    if (commandSplit.length == 2 && commandSplit[0].equals("push")) {
                        int num = Integer.parseInt(commandSplit[1]);
                        queue.add(num);
                        System.out.println("ok");
                    }
            }
            command = br.readLine();
        }
        System.out.println("bye");
    }
}
