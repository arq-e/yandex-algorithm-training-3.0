package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TaskC {

    public TaskC() {
    }

    public static void main(String[] args) throws IOException {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] command = br.readLine().split(" ");
        DequeImpl deque = new DequeImpl();
        while (!command[0].equals("exit")) {
            switch(command[0]) {
                case "push_back":
                    deque.pushBack(Integer.parseInt(command[1]));
                    break;
                case "push_front":
                    deque.pushFront(Integer.parseInt(command[1]));
                    break;
                case "pop_back":
                    deque.pollBack();
                    break;
                case "pop_front":
                    deque.pollFront();
                    break;
                case "clear":
                    deque.clear();
                    break;
                case "back":
                    deque.peekBack();
                    break;
                case "front":
                    deque.peekFront();
                    break;
                case "size":
                    deque.getSize();
                    break;
                default:
                    break;
            }
            command = br.readLine().split(" ");
        }
        System.out.println("bye");
    }
}

class DequeImpl {
    List<DequeBlock> list;

    public DequeImpl() {
        this.list = new ArrayList<>();
        list.add(new DequeBlock(false));
    }

    public void pushBack(int num) {
        DequeBlock block;
        if (list.size() == 0) {
            block = new DequeBlock(false);
        } else {
            block = list.get(list.size()-1);
        }
        if (block.getBack() == block.getArray().length - 1 && block.getSize() > 0) {
            block = new DequeBlock(false);
            list.add(block);
        }
        block.pushBack(num);
        System.out.println("ok");
    }

    public void pushFront(int num) {
        DequeBlock block;
        if (list.size() == 0) {
            block = new DequeBlock(false);
        } else {
            block = list.get(0);
        }
        if (block.getFront() == 0 && block.getSize() > 0) {
            block.setFront(0);
            block = new DequeBlock(true);
            list.add(0,block);
        }
        block.pushFront(num);
        System.out.println("ok");
    }

    public void pollBack() {
        DequeBlock block = list.get(list.size()-1);
        if (block.getSize() == 0) {
            System.out.println("error");
        } else {
            System.out.println(block.getBackElement(true));
            if (block.getSize() == 0 && list.size() > 1) list.remove(list.size()-1);
        }
    }

    public void pollFront() {
        DequeBlock block = list.get(0);
        if (block.getSize() == 0) {
            System.out.println("error");
        } else {
            System.out.println(block.getFrontElement(true));
            if (block.getSize() == 0 && list.size() > 1) {
                list.remove(0);
            }
        }
    }

    public void peekBack() {
        DequeBlock block = list.get(list.size()-1);
        if (block.getSize() == 0) {
            System.out.println("error");
        } else {
            System.out.println(block.getBackElement(false));
        }
    }

    public void peekFront() {
        DequeBlock block = list.get(0);
        if (block.getSize() == 0) {
            System.out.println("error");
        } else {
            System.out.println(block.getFrontElement(false));
        }
    }

    public void clear() {
        list.clear();
        list.add(new DequeBlock(false));
        System.out.println("ok");
    }

    public void getSize() {
        int size = 0;
        for (DequeBlock block : list) {
            size += block.getSize();
        }
        System.out.println(size);
    }
}

class DequeBlock {
    private int front;
    private int back;
    private int[] array;
    private int size;

    public DequeBlock(boolean addBefore) {
        this.array = new int[10];
        if (addBefore) {
            this.front = array.length-1;
            this.back = array.length-1;
        } else {
            this.front = 0;
            this.back = 0;
        }
        size = 0;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getSize() {
        return size;
    }

    public int[] getArray() {
        return array;
    }

    public void pushBack(int num) {
        if (size > 0) {
            ++back;
        }
        ++size;
        array[back] = num;
    }

    public void pushFront(int num) {
        if (size > 0) {
            --front;
        }
        array[front] = num;
        ++size;
    }

    public int getBackElement(boolean remove){
        int element = array[back];
        if (remove) {
            --back;
            --size;
        }
        if (size == 0) back = front;
        return element;
    }

    public int getFrontElement(boolean remove){
        int element = array[front];
        if (remove) {
            ++front;
            --size;
        }
        if (size == 0) front = back;
        return element;
    }

    public int maxElement() {
        return array.length-1;
    }
}


