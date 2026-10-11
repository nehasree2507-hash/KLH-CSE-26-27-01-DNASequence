package texthack.algo;

import texthack.util.IntList;

/** Knuth-Morris-Pratt: failure function (longest proper border) gives O(n+m). */
public final class KMPMatcher implements PatternMatcher {
    public String name() { return "KMP"; }
    public String complexity() { return "O(n+m)"; }

    /** f[i] = length of longest proper prefix of p[0..i] that is also a suffix. */
    public static int[] failure(char[] p) {
        int m = p.length;
        int[] f = new int[m];
        int k = 0;
        for (int i = 1; i < m; i++) {
            while (k > 0 && p[i] != p[k]) k = f[k - 1];
            if (p[i] == p[k]) k++;
            f[i] = k;
        }
        return f;
    }

    public IntList search(char[] t, char[] p) {
        IntList r = new IntList();
        int n = t.length, m = p.length;
        if (m == 0 || m > n) return r;
        int[] f = failure(p);
        int k = 0;
        for (int i = 0; i < n; i++) {
            while (k > 0 && (k == m || t[i] != p[k])) k = f[k - 1];
            if (t[i] == p[k]) k++;
            if (k == m) r.add(i - m + 1);
        }
        return r;
    }
}
