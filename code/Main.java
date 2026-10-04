import java.util.*;

public class Main {

    static boolean validDNA(String s) {
        return s.matches("[ACGT]+");
    }

    static Map<String, List<Integer>> runKMP(
            String genome, List<String> motifs) {

        Map<String, List<Integer>> result = new LinkedHashMap<>();

        for (String motif : motifs) {
            result.put(motif, KMP.search(genome, motif));
        }

        return result;
    }

    static Map<String, List<Integer>> runRabinKarp(
            String genome, List<String> motifs) {

        Map<String, List<Integer>> result = new LinkedHashMap<>();

        for (String motif : motifs) {
            result.put(motif, RabinKarp.search(genome, motif));
        }

        return result;
    }

    static void showResults(Map<String, List<Integer>> results) {

        System.out.println();
        System.out.println("RESULTS");
        System.out.println("--------------------------------");

        for (String motif : results.keySet()) {

            List<Integer> positions = results.get(motif);

            System.out.println(
                    motif + "  ->  " +
                    positions.size() +
                    " matches"
            );

            System.out.println(
                    "     " + positions
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("DNA MOTIF ANALYZER");
        System.out.println("------------------");

        System.out.print("Genome: ");
        String genome = sc.nextLine().trim().toUpperCase();

        if (!validDNA(genome)) {
            System.out.println("Invalid DNA sequence.");
            return;
        }

        System.out.print("Number of motifs: ");
        int n;

        try {
            n = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid number.");
            return;
        }

        List<String> motifs = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            System.out.print("Motif " + (i + 1) + ": ");

            String motif =
                    sc.nextLine().trim().toUpperCase();

            if (!validDNA(motif)) {
                System.out.println("Invalid motif.");
                return;
            }

            motifs.add(motif);
        }

        // KMP
        long start = System.nanoTime();

        Map<String, List<Integer>> kmp =
                runKMP(genome, motifs);

        long kmpTime = System.nanoTime() - start;

        // Rabin-Karp
        start = System.nanoTime();

        Map<String, List<Integer>> rabin =
                runRabinKarp(genome, motifs);

        long rabinTime = System.nanoTime() - start;

        // Aho-Corasick
        start = System.nanoTime();

        Map<String, List<Integer>> aho =
                AhoCorasick.search(genome, motifs);

        long ahoTime = System.nanoTime() - start;

        System.out.println();
        System.out.println(
                "Genome size: " + genome.length() + " bases"
        );

        showResults(kmp);
        showGenomeWithMatches(genome, kmp);

        if (kmp.equals(rabin) && kmp.equals(aho)) {
            System.out.println();
            System.out.println(
                    "Check: KMP, Rabin-Karp and Aho-Corasick agree."
            );
        }

        System.out.println();
        System.out.println("TIME");
        System.out.println("--------------------------------");

        System.out.printf(
                "KMP            %.4f ms%n",
                kmpTime / 1_000_000.0
        );

        System.out.printf(
                "Rabin-Karp     %.4f ms%n",
                rabinTime / 1_000_000.0
        );

        System.out.printf(
                "Aho-Corasick   %.4f ms%n",
                ahoTime / 1_000_000.0
        );

        System.out.println();

        sc.close();
    }
    static void showGenomeWithMatches(
        String genome,
        Map<String, List<Integer>> results) {

    Set<Integer> matchedPositions = new HashSet<>();

    for (String motif : results.keySet()) {

        List<Integer> positions = results.get(motif);

        for (int start : positions) {

            for (int i = 0; i < motif.length(); i++) {
                matchedPositions.add(start + i);
            }
        }
    }

    System.out.println("\nGenome with matches");
    System.out.println("---------------------------------------------");

    for (int start = 0; start < genome.length(); start += 50) {

        int end = Math.min(start + 50, genome.length());

        for (int i = start; i < end; i++) {

            int position = i + 1;
            char base = genome.charAt(i);

            if (matchedPositions.contains(position)) {

                // Highlight matched bases
                System.out.print(
                        "\u001B[43m\u001B[30m "
                        + base
                        + " \u001B[0m"
                );

            } else {

                System.out.print(" " + base + " ");
            }
        }

        System.out.println();
    }

    System.out.println("---------------------------------------------");
    System.out.println("Highlighted bases = detected motifs");
}
}