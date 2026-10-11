package texthack.algo;

import texthack.util.IntList;

/** Z-function on S = P + '\0' + T. A position i with Z[i] == m is a match. O(n+m). */
public final class ZMatcher implements PatternMatcher {
    public String name() { return "Z-function"; }
    public String complexity() { return "O(n+m)"; }

    public static int[] zArray(char[] s) {
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i < r) z[i] = Math.min(r - i, z[i - l]);
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) z[i]++;
            if (i + z[i] > r) { l = i; r = i + z[i]; }
        }
        return z;
    }

    public IntList search(char[] t, char[] p) {
        IntList r = new IntList();
        int n = t.length, m = p.length;
        if (m == 0 || m > n) return r;
        char[] s = new char[m + 1 + n];
        for (int i = 0; i < m; i++) s[i] = p[i];
        s[m] = '\u0000';
        for (int i = 0; i < n; i++) s[m + 1 + i] = t[i];
        int[] z = zArray(s);
        for (int i = m + 1; i < s.length; i++) if (z[i] == m) r.add(i - m - 1);
        return r;
    }
}
