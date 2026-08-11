class EventManager {

    PriorityQueue<int[]> pq;
    HashMap<Integer, Integer> map;

    public EventManager(int[][] events) {

        map = new HashMap<>();

        pq = new PriorityQueue<>((a, b) -> {

            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        for (int[] event : events) {

            int id = event[0];
            int priority = event[1];

            map.put(id, priority);
            pq.offer(new int[]{id, priority});
        }
    }

    public void updatePriority(int eventId, int newPriority) {

        map.put(eventId, newPriority);

        pq.offer(new int[]{eventId, newPriority});
    }

    public int pollHighest() {

        while (!pq.isEmpty()) {

            int[] event = pq.poll();

            int id = event[0];
            int priority = event[1];

            Integer currentPriority = map.get(id);

            if (currentPriority != null && currentPriority == priority) {

                map.remove(id);

                return id;
            }
        }

        return -1;
    }
}