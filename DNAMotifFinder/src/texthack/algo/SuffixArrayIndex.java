package texthack.algo;

import texthack.util.IntList;
import texthack.util.Lcp;

/**
 * Suffix array (prefix doubling + counting sort, O(n log n)) with Kasai LCP (O(n)).
 * Once built, ANY pattern is found by binary search in O(m log n + occ) - ideal for many queries.
 */
public final class SuffixArrayIndex {
    private final char[] text;
    private final int[] sa;
    private int[] lcp;

    public SuffixArrayIndex(char[] text) {
        this.text = text;
        this.sa = build(text);
    }

    public int[] suffixArray() { return sa; }

    private static int[] build(char[] s) {
        int n = s.length;
        int[] sa = new int[n];
        if (n == 0) return sa;
        int[] rk = new int[n], old = new int[n], sa2 = new int[n];
        int m = 256;
        int[] c = new int[Math.max(m, n) + 1];
        for (int i = 0; i < n; i++) c[rk[i] = s[i] & 0xFF]++;
        for (int i = 1; i < m; i++) c[i] += c[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--c[rk[i]]] = i;
        for (int k = 1; ; k <<= 1) {
            int p = 0;                                   // order by second key
            for (int i = n - k; i < n; i++) sa2[p++] = i;
            for (int i = 0; i < n; i++) if (sa[i] >= k) sa2[p++] = sa[i] - k;
            for (int i = 0; i < m; i++) c[i] = 0;        // stable counting sort by first key
            for (int i = 0; i < n; i++) c[rk[i]]++;
            for (int i = 1; i < m; i++) c[i] += c[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--c[rk[sa2[i]]]] = sa2[i];
            for (int i = 0; i < n; i++) old[i] = rk[i];  // re-rank
            rk[sa[0]] = 0;
            p = 1;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1], b = sa[i];
                int a2 = a + k < n ? old[a + k] : -1, b2 = b + k < n ? old[b + k] : -1;
                rk[b] = (old[a] == old[b] && a2 == b2) ? p - 1 : p++;
            }
            if (p >= n) break;
            m = p;
        }
        return sa;
    }

    /** LCP array via Kasai's algorithm (see util.Lcp). Computed once, then cached. */
    public int[] lcp() {
        if (lcp == null) lcp = Lcp.kasai(text, sa);
        return lcp;
    }

    /** Longest repeated substring (ignoring N runs): returns {firstStart, secondStart, length}. */
    public int[] longestRepeat() {
        return Lcp.longestRepeat(text, sa, lcp());
    }

    private int cmp(int suffix, char[] p) {
        int n = text.length;
        for (int j = 0; j < p.length; j++) {
            if (suffix + j >= n) return -1;
            if (text[suffix + j] != p[j]) return text[suffix + j] < p[j] ? -1 : 1;
        }
        return 0;
    }

    private int bound(char[] p, boolean strict) {
        int l = 0, r = sa.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            int c = cmp(sa[mid], p);
            if (c < 0 || (strict && c == 0)) l = mid + 1; else r = mid;
        }
        return l;
    }

    /** All start positions of p (sorted ascending). */
    public IntList find(char[] p) {
        IntList r = new IntList();
        if (p.length == 0) return r;
        int lo = bound(p, false), hi = bound(p, true);
        for (int i = lo; i < hi; i++) r.add(sa[i]);
        r.sort();
        return r;
    }
}
