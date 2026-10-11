package texthack.bio;

import texthack.util.Dna;
import java.io.*;

/** FASTA reader/writer. Multi-record files are joined with a single 'N' so motifs cannot span records. */
public final class Fasta {
    private Fasta() {}

    private static final class CharBuf {
        char[] a = new char[1 << 16];
        int n;
        void add(char c) {
            if (n == a.length) {
                char[] b = new char[a.length * 2];
                for (int i = 0; i < n; i++) b[i] = a[i];
                a = b;
            }
            a[n++] = c;
        }
        char last() { return n == 0 ? 0 : a[n - 1]; }
        char[] toArray() {
            char[] r = new char[n];
            for (int i = 0; i < n; i++) r[i] = a[i];
            return r;
        }
    }

    /** Name from the first '>' header line (text up to the first space), or null if there is none. */
    public static String firstHeader(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && line.charAt(0) == '>') {
                    String h = line.substring(1).trim();
                    int sp = h.indexOf(' ');
                    return (sp > 0 ? h.substring(0, sp) : h).isEmpty() ? null : (sp > 0 ? h.substring(0, sp) : h);
                }
            }
        }
        return null;
    }

    public static char[] read(String path) throws IOException {
        File f = new File(path);
        if (!f.isFile()) throw new FileNotFoundException("File not found: " + path);
        CharBuf b = new CharBuf();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty() || line.charAt(0) == ';') continue;
                if (line.charAt(0) == '>') {
                    if (b.n > 0 && b.last() != 'N') b.add('N');
                    continue;
                }
                for (int i = 0; i < line.length(); i++) {
                    char c = Character.toUpperCase(line.charAt(i));
                    if (c >= 'A' && c <= 'Z') b.add(Dna.isBase(c) ? c : 'N');   // IUPAC codes -> N
                }
            }
        }
        if (b.n == 0) throw new IOException("No DNA sequence found in " + path);
        return b.toArray();
    }

    public static void write(String path, String header, char[] seq) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
            pw.println(">" + header);
            for (int i = 0; i < seq.length; i += 70) {
                pw.println(new String(seq, i, Math.min(70, seq.length - i)));
            }
        }
    }
}
