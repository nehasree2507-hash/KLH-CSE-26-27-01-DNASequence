package texthack.algo;

import texthack.util.IntList;

/** Adapter: builds the suffix array once per text and reuses it for every motif. */
public final class SuffixArrayMatcher implements PatternMatcher {
    private char[] cachedText;
    private SuffixArrayIndex index;
    private long buildNanos;

    public String name() { return "Suffix Array"; }
    public String complexity() { return "O(n log n) build, O(m log n) query"; }
    public long buildNanos() { return buildNanos; }

    public IntList search(char[] t, char[] p) {
        if (t != cachedText) {
            long t0 = System.nanoTime();
            index = new SuffixArrayIndex(t);
            buildNanos = System.nanoTime() - t0;
            cachedText = t;
        }
        return index.find(p);
    }
}
