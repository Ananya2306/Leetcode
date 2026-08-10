import java.util.*;

class TaskManager {

    HashMap<Integer, int[]> map;

    PriorityQueue<int[]> pq;

    public TaskManager(List<List<Integer>> tasks) {

        map = new HashMap<>();

        pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]); 
            }

            return Integer.compare(b[1], a[1]);    
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);

            map.put(taskId, new int[]{userId, priority});
            pq.offer(new int[]{priority, taskId});
        }
    }

    public void add(int userId, int taskId, int priority) {

        map.put(taskId, new int[]{userId, priority});

        pq.offer(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {

        int[] task = map.get(taskId);

        int userId = task[0];

        map.put(taskId, new int[]{userId, newPriority});

        pq.offer(new int[]{newPriority, taskId});
    }

    public void rmv(int taskId) {

        map.remove(taskId);
    }

    public int execTop() {

        while (!pq.isEmpty()) {

            int[] top = pq.peek();

            int priority = top[0];
            int taskId = top[1];

            if (!map.containsKey(taskId)) {
                pq.poll();
                continue;
            }

            int[] current = map.get(taskId);

            if (current[1] != priority) {
                pq.poll();
                continue;
            }

            pq.poll();

            int userId = current[0];

            map.remove(taskId);

            return userId;
        }

        return -1;
    }
}