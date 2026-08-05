import java.util.*;

class AuthenticationManager {

    class Pair {
        String token;
        int expiry;

        Pair(String token, int expiry) {
            this.token = token;
            this.expiry = expiry;
        }
    }

    int ttl;

    HashMap<String, Integer> map;
    Queue<Pair> queue;

    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
        map = new HashMap<>();
        queue = new LinkedList<>();
    }

    private void removeExpired(int currentTime) {

        while (!queue.isEmpty()) {

            Pair front = queue.peek();

            if (front.expiry > currentTime) {
                break;
            }

            queue.poll();

            if (map.containsKey(front.token) &&
                map.get(front.token) == front.expiry) {

                map.remove(front.token);
            }
        }
    }

    public void generate(String tokenId, int currentTime) {

        removeExpired(currentTime);

        int expiry = currentTime + ttl;

        map.put(tokenId, expiry);
        queue.offer(new Pair(tokenId, expiry));
    }

    public void renew(String tokenId, int currentTime) {

        removeExpired(currentTime);

        if (!map.containsKey(tokenId)) {
            return;
        }

        int expiry = currentTime + ttl;

        map.put(tokenId, expiry);
        queue.offer(new Pair(tokenId, expiry));
    }

    public int countUnexpiredTokens(int currentTime) {

        removeExpired(currentTime);

        return map.size();
    }
}