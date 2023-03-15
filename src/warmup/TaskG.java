package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskG {

    public static void main(String[] args) throws IOException {
        TaskG task = new TaskG();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] requestTime = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
        int[] serverTime = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
        int[] feedbackTime = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
        int timeSpent = calculateTimeSpent(requestTime, feedbackTime);
        adjustReceivedServerTime(serverTime, timeSpent);
        printTime(serverTime);
    }

    private void printTime(int[] serverTime) {
        if (serverTime[0] > 9) {
            System.out.print(serverTime[0] + ":");
        } else {
            System.out.print("0" + serverTime[0] + ":");
        }
        if (serverTime[1] > 9) {
            System.out.print(serverTime[1] + ":");
        } else {
            System.out.print("0" + serverTime[1] + ":");
        }
        if (serverTime[2] > 9) {
            System.out.print(serverTime[2]);
        } else {
            System.out.print("0" + serverTime[2]);
        }
    }

    private int calculateTimeSpent(int[] requestTime, int[] feedbackTime) {
        int timeSpent = 0;
        if (requestTime[0] == feedbackTime[0] && requestTime[1] == feedbackTime[1]) {
            timeSpent = feedbackTime[2] - requestTime[2];
        } else if (requestTime[0] == feedbackTime[0]) {
            timeSpent = feedbackTime[2] + 60 - requestTime[2] + 60 * (feedbackTime[1] - requestTime[1] - 1);
        } else {
            timeSpent = feedbackTime[2] + 60 - requestTime[2] + 60 * (feedbackTime[1] + 60 - requestTime[1] - 1);
            if (feedbackTime[0] > requestTime[0]) {
                timeSpent += 3600 * (feedbackTime[0] - requestTime[0] - 1);
            } else {
                timeSpent += 3600 * (feedbackTime[0] + 24 - requestTime[0] - 1);
            }
        }
        return (timeSpent + 1) / 2;
    }

    private void adjustReceivedServerTime(int[] serverTime, int timeSpent) {
        int hours = timeSpent / 3600;
        timeSpent %= 3600;
        int mins = timeSpent / 60;
        timeSpent %= 60;
        serverTime[2] += timeSpent;
        serverTime[1] += serverTime[2] / 60;
        serverTime[2] %= 60;
        serverTime[1] += mins;
        serverTime[0] += serverTime[1] / 60;
        serverTime[1] %= 60;
        serverTime[0] += hours;
        serverTime[0] %= 24;
    }
}
