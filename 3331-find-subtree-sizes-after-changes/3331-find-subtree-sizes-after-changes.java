import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] oldLast = new int[n];
        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;
        while (top > 0) {
            int curr = stack[--top];
            if (curr < 0) {
                curr = -curr - 1;
                last[s.charAt(curr) - 'a'] = oldLast[curr];
                continue;
            }
            int ch = s.charAt(curr) - 'a';
            oldLast[curr] = last[ch];
            if (curr != 0 && last[ch] != -1) {
                newParent[curr] = last[ch];
            } else {
                newParent[curr] = parent[curr];
            }
            last[ch] = curr;
            stack[top++] = -curr - 1;
            for (int child : tree[curr]) {
                stack[top++] = child;
            }
        }
        ArrayList<Integer>[] modified = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }
        int[] order = new int[n];
        top = 0;
        int count = 0;
        stack[top++] = 0;
        while (top > 0) {
            int curr = stack[--top];
            order[count++] = curr;
            for (int child : modified[curr]) {
                stack[top++] = child;
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = n - 1; i > 0; i--) {
            int curr = order[i];
            ans[newParent[curr]] += ans[curr];
        }
        return ans;
    }
}