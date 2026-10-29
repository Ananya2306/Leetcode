import java.util.*;
class Solution {
    int n;
    List<Integer>[] adj;
    int[] tin, tout, depth;
    int[][] up;
    int[] bit;
    int timer;
    char[] s;
    int LOG;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.s = s.toCharArray();

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        bit = new int[2 * n + 1];
        timer = 0;

        buildTree();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (this.s[i] - 'a');
            fenwickUpdate(tin[i], mask);
            fenwickUpdate(tout[i], mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                if (this.s[u] != c) {
                    int oldMask = 1 << (this.s[u] - 'a');
                    int newMask = 1 << (c - 'a');

                    fenwickUpdate(tin[u], oldMask);
                    fenwickUpdate(tout[u], oldMask);

                    fenwickUpdate(tin[u], newMask);
                    fenwickUpdate(tout[u], newMask);

                    this.s[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int lca = getLCA(u, v);

                int mask = prefixXor(tin[u] + 1)
                         ^ prefixXor(tin[v] + 1)
                         ^ (1 << (this.s[lca] - 'a'));

                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    void buildTree() {
        int[] stackNode = new int[2 * n + 5];
        int[] stackParent = new int[2 * n + 5];
        int[] stackState = new int[2 * n + 5];

        int top = 0;

        stackNode[top] = 0;
        stackParent[top] = 0;
        stackState[top] = 0;
        top++;

        while (top > 0) {
            top--;

            int u = stackNode[top];
            int parent = stackParent[top];
            int state = stackState[top];

            if (state == 1) {
                tout[u] = timer++;
                continue;
            }

            tin[u] = timer++;
            up[u][0] = parent;

            for (int j = 1; j < LOG; j++) {
                up[u][j] = up[up[u][j - 1]][j - 1];
            }

            stackNode[top] = u;
            stackParent[top] = parent;
            stackState[top] = 1;
            top++;

            for (int v : adj[u]) {
                if (v == parent) {
                    continue;
                }

                depth[v] = depth[u] + 1;

                stackNode[top] = v;
                stackParent[top] = u;
                stackState[top] = 0;
                top++;
            }
        }
    }

    void fenwickUpdate(int index, int value) {
        index++;

        while (index <= 2 * n) {
            bit[index] ^= value;
            index += index & -index;
        }
    }

    int prefixXor(int index) {
        int mask = 0;

        while (index > 0) {
            mask ^= bit[index];
            index -= index & -index;
        }

        return mask;
    }

    int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = LOG - 1; j >= 0; j--) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }
}
