import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }
            int preferred = i % k;
            Integer server = available.ceiling(preferred);

            if (server == null) {
                server = available.first();
            }

            available.remove(server);
            count[server]++;
            int finishTime = arrival[i] + load[i];

            busy.offer(new int[]{finishTime, server});
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }
        return result;
    }
}
