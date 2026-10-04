import java.util.*;

public class AhoCorasick {

    static class Node {
        int[] next = {-1, -1, -1, -1};
        int fail = 0;
        List<Integer> out = new ArrayList<>();
    }

    static List<Node> trie = new ArrayList<>();
    static List<String> patterns;

    static int id(char c) {
        return c == 'A' ? 0 :
               c == 'C' ? 1 :
               c == 'G' ? 2 : 3;
    }

    public static Map<String, List<Integer>>
    search(String text, List<String> pats) {

        patterns = pats;
        trie.clear();
        trie.add(new Node());

        // Build Trie
        for (int p = 0; p < pats.size(); p++) {
            int cur = 0;

            for (char c : pats.get(p).toCharArray()) {
                int x = id(c);

                if (trie.get(cur).next[x] == -1) {
                    trie.get(cur).next[x] = trie.size();
                    trie.add(new Node());
                }

                cur = trie.get(cur).next[x];
            }

            trie.get(cur).out.add(p);
        }

        // Build failure links
        Queue<Integer> q = new LinkedList<>();

        for (int x = 0; x < 4; x++) {
            int v = trie.get(0).next[x];

            if (v == -1)
                trie.get(0).next[x] = 0;
            else
                q.add(v);
        }

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int x = 0; x < 4; x++) {
                int v = trie.get(u).next[x];

                if (v == -1) {
                    trie.get(u).next[x] =
                            trie.get(trie.get(u).fail).next[x];
                } else {
                    trie.get(v).fail =
                            trie.get(trie.get(u).fail).next[x];

                    trie.get(v).out.addAll(
                            trie.get(trie.get(v).fail).out);

                    q.add(v);
                }
            }
        }

        Map<String, List<Integer>> result =
                new LinkedHashMap<>();

        for (String p : pats)
            result.put(p, new ArrayList<>());

        // Search
        int state = 0;

        for (int i = 0; i < text.length(); i++) {
            state = trie.get(state).next[id(text.charAt(i))];

            for (int p : trie.get(state).out) {
                int pos = i - patterns.get(p).length() + 2;
                result.get(patterns.get(p)).add(pos);
            }
        }

        return result;
    }
}