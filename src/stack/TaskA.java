package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskA {
    List<Integer> stack;
    public static void main(String[] args) {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        stack = new ArrayList<>();
        while (!command.equals("exit")) {
            switch (command) {
                case "pop":
                    pop(stack);
                    break;
                case "back":
                    back(stack);
                    break;
                case "size":
                    size(stack);
                    break;
                case "clear":
                    clear(stack);
                    break;
                default:
                    String[] commandSplit = command.split(" ");
                    if (commandSplit[0].equals("push")) {
                        push(stack, commandSplit[1]);
                    }
            }
            command = sc.nextLine();
        }
        System.out.println("bye");
    }

    private void pop(List<Integer> stack) {
        if (stack.size() != 0) {
            int val = stack.get(0);
            stack.remove(0);
            System.out.println(val);
        } else System.out.println("error");
    }

    private void back(List<Integer> stack) {
        if (stack.size() != 0) {
            int val = stack.get(0);
            System.out.println(val);
        } else System.out.println("error");
    }

    private void size(List<Integer> stack) {
        System.out.println(stack.size());
    }

    private void clear(List<Integer> stack) {
        stack.clear();
        System.out.println("ok");
    }

    private void push(List<Integer> stack, String num) {
        stack.add(0, Integer.parseInt(num));
        System.out.println("ok");
    }

}
