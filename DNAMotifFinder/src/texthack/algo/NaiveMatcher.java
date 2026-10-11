package texthack.algo;

import texthack.util.IntList;

/** Brute force - baseline, O(n*m) worst case. */
public final class NaiveMatcher implements PatternMatcher {
    public String name() { return "Naive"; }
    public String complexity() { return "O(n*m) worst"; }

    public IntList search(char[] t, char[] p) {
        IntList r = new IntList();
        int n = t.length, m = p.length;
        if (m == 0 || m > n) return r;
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && t[i + j] == p[j]) j++;
            if (j == m) r.add(i);
        }
        return r;
    }
}
