package texthack.algo;

import texthack.util.IntList;

/** Common contract for single-pattern exact matchers. Returns 0-based start positions in increasing order. */
public interface PatternMatcher {
    String name();
    String complexity();
    IntList search(char[] text, char[] pattern);
}
