package texthack.algo;

import texthack.util.Dna;
import texthack.util.IntList;

/**
 * Aho-Corasick: trie + failure links + dictionary (output) links, compiled into a full
 * goto automaton over the 5-letter DNA alphabet. Finds ALL motifs in ONE pass: O(n + total|P| + matches).
 */
public final class AhoCorasick {
    private static final int SIGMA = 5;
    private final int[][] go;
    private final int[] fail, dict, termHead, chainPrev, lens;

    public AhoCorasick(char[][] pats) {
        int k = pats.length;
        int total = 1;
        for (char[] p : pats) total += p.length;
        go = new int[total][SIGMA];
        for (int i = 0; i < total; i++) for (int c = 0; c < SIGMA; c++) go[i][c] = -1;
        fail = new int[total];
        dict = new int[total];
        termHead = new int[total];
        for (int i = 0; i < total; i++) termHead[i] = -1;
        chainPrev = new int[k];
        lens = new int[k];

        int nodes = 1;
        for (int id = 0; id < k; id++) {                // 1) build the trie
            int u = 0;
            lens[id] = pats[id].length;
            for (char ch : pats[id]) {
                int c = Dna.code(ch);
                if (go[u][c] == -1) go[u][c] = nodes++;
                u = go[u][c];
            }
            chainPrev[id] = termHead[u];                // duplicate patterns share a node
            termHead[u] = id;
        }

        int[] q = new int[nodes];                       // 2) BFS: failure + dictionary links
        int qh = 0, qt = 0;
        for (int c = 0; c < SIGMA; c++) {
            int v = go[0][c];
            if (v == -1) go[0][c] = 0;
            else { fail[v] = 0; q[qt++] = v; }
        }
        while (qh < qt) {
            int u = q[qh++];
            for (int c = 0; c < SIGMA; c++) {
                int v = go[u][c];
                if (v == -1) go[u][c] = go[fail[u]][c];
                else {
                    fail[v] = go[fail[u]][c];
                    dict[v] = termHead[fail[v]] != -1 ? fail[v] : dict[fail[v]];
                    q[qt++] = v;
                }
            }
        }
    }

    /** Returns, for every pattern id, the sorted list of 0-based start positions. */
    public IntList[] search(char[] t) {
        IntList[] res = new IntList[lens.length];
        for (int i = 0; i < res.length; i++) res[i] = new IntList();
        int s = 0;
        for (int i = 0; i < t.length; i++) {
            s = go[s][Dna.code(t[i])];
            int v = termHead[s] != -1 ? s : dict[s];
            while (v != 0) {
                for (int p = termHead[v]; p != -1; p = chainPrev[p]) res[p].add(i - lens[p] + 1);
                v = dict[v];
            }
        }
        return res;
    }
}
