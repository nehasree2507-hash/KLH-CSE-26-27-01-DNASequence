package texthack.bio;

import texthack.util.Dna;
import texthack.util.Lcg;

/** Builds a synthetic genome (AT-rich background) and plants known motifs at random positions. */
public final class GenomeGenerator {
    private GenomeGenerator() {}

    public static char[] generate(int n, long seed, String[] motifs, int plantEach) {
        Lcg rng = new Lcg(seed);
        char[] g = new char[n];
        for (int i = 0; i < n; i++) {
            int r = rng.nextInt(100);
            g[i] = r < 29 ? 'A' : r < 58 ? 'T' : r < 79 ? 'C' : 'G';
        }
        for (String s : motifs) {
            char[] m = Dna.toMotif(s);
            if (m.length >= n) continue;
            for (int j = 0; j < plantEach; j++) {
                int pos = rng.nextInt(n - m.length);
                char[] src = (j % 2 == 0) ? m : Dna.reverseComplement(m);   // half planted on the minus strand
                for (int k = 0; k < m.length; k++) g[pos + k] = src[k];
            }
        }
        return g;
    }
}
