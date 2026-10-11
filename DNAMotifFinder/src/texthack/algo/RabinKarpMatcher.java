package texthack.algo;

import texthack.util.IntList;

/**
 * Rabin-Karp with a polynomial rolling hash. Two independent moduli (double hashing)
 * make collisions extremely unlikely; every hash hit is still verified (Las Vegas style).
 */
public final class RabinKarpMatcher implements PatternMatcher {
    private static final long M1 = 1_000_000_007L, M2 = 998_244_353L;
    private static final long B1 = 131L, B2 = 137L;
    private long falsePositives;

    public String name() { return "Rabin-Karp"; }
    public String complexity() { return "O(n+m) avg"; }
    public long falsePositives() { return falsePositives; }

    public IntList search(char[] t, char[] p) {
        IntList r = new IntList();
        int n = t.length, m = p.length;
        if (m == 0 || m > n) return r;
        long pow1 = 1, pow2 = 1;                       // B^(m-1)
        for (int i = 0; i < m - 1; i++) { pow1 = pow1 * B1 % M1; pow2 = pow2 * B2 % M2; }
        long hp1 = 0, hp2 = 0, ht1 = 0, ht2 = 0;
        for (int i = 0; i < m; i++) {
            hp1 = (hp1 * B1 + p[i]) % M1;  hp2 = (hp2 * B2 + p[i]) % M2;
            ht1 = (ht1 * B1 + t[i]) % M1;  ht2 = (ht2 * B2 + t[i]) % M2;
        }
        for (int i = 0; ; i++) {
            if (hp1 == ht1 && hp2 == ht2) {
                int j = 0;
                while (j < m && t[i + j] == p[j]) j++;
                if (j == m) r.add(i); else falsePositives++;
            }
            if (i == n - m) break;
            ht1 = ((ht1 - t[i] * pow1 % M1 + M1) * B1 + t[i + m]) % M1;
            ht2 = ((ht2 - t[i] * pow2 % M2 + M2) * B2 + t[i + m]) % M2;
        }
        return r;
    }
}
