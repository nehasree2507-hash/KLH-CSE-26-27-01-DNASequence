package texthack.util;

/** DNA alphabet helpers. Alphabet = A C G T N (N = unknown / any non-ACGT symbol). */
public final class Dna {
    private Dna() {}

    public static boolean isBase(char c) {
        return c == 'A' || c == 'C' || c == 'G' || c == 'T' || c == 'N';
    }

    /** Maps a base to 0..4 (used as automaton column index). */
    public static int code(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default:  return 4;
        }
    }

    /** Validates & upper-cases a motif. Throws IllegalArgumentException when invalid. */
    public static char[] toMotif(String s) {
        String t = s.trim().toUpperCase();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty motif");
        char[] r = new char[t.length()];
        for (int i = 0; i < r.length; i++) {
            char c = t.charAt(i);
            if (!isBase(c)) throw new IllegalArgumentException("Invalid base '" + c + "' in motif " + t + " (allowed: A C G T N)");
            r[i] = c;
        }
        return r;
    }

    public static char complement(char c) {
        switch (c) {
            case 'A': return 'T';
            case 'T': return 'A';
            case 'C': return 'G';
            case 'G': return 'C';
            default:  return 'N';
        }
    }

    /** Reverse complement = the same motif read on the opposite DNA strand. */
    public static char[] reverseComplement(char[] p) {
        int m = p.length;
        char[] r = new char[m];
        for (int i = 0; i < m; i++) r[i] = complement(p[m - 1 - i]);
        return r;
    }

    public static boolean equal(char[] a, char[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }
}
