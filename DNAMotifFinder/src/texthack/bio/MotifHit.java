package texthack.bio;

import texthack.util.IntList;

/** All occurrences of one motif on one strand. Positions are 0-based start offsets on the forward strand. */
public final class MotifHit {
    public final String motif;
    public final char strand;      // '+' or '-'
    public final int length;
    public final IntList positions;

    public MotifHit(String motif, char strand, int length, IntList positions) {
        this.motif = motif; this.strand = strand; this.length = length; this.positions = positions;
    }
}
