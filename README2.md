# DNA Sequence Analysis Tool

A Java console tool that finds DNA motifs in a genome using six string-matching algorithms, and compares how fast they are.

**Guide:** Dr. Ch. Anuradha

## Team

| Name | Roll No. |
|------|----------|
| G. Boomika Sre | 2520030010 |
| P. Nehasree | 2520030174 |
| P. Hasini | 2520030282 |

## What is this project?

A DNA sequence is a long string of the letters A, C, G and T (N means an unknown base).
A **motif** is a short, continuous piece of DNA that may have a biological meaning, for example:

- `ATG` (start codon)
- `TATAAA` (TATA box)
- `GAATTC` (EcoRI site)

A genome can be millions of bases long, so a slow search is not good enough.
This tool finds every position of one or more motifs quickly and shows the count, the positions and the time taken.

## Features

- Load a genome from a **FASTA** file, or generate a synthetic genome
- Search **one or many motifs** at the same time
- Search the **reverse-complement strand** too (`--rc`)
- Export all positions to a **CSV** file (`--csv`)
- **Benchmark** all algorithms and run **scaling tests** (up to 10 million bases)
- Find the **longest repeated region** in a genome
- Save a genome as a FASTA file
- Simple 7-option menu

```
1. Load DNA Genome from FASTA
2. Generate Synthetic Genome
3. Search DNA Motifs
4. Compare Algorithms (Benchmark)
5. Find Longest Repeated Region
6. Save Genome as FASTA
7. Exit
```

## Algorithms

| Algorithm | Idea | Time |
|-----------|------|------|
| Naive | Compare the motif at every position | O(n·m) worst case |
| KMP | Failure (LPS) table, no re-scanning | O(n+m) |
| Rabin–Karp | Double rolling hash, then check | O(n+m) average |
| Z-function | Z-array of motif + separator + genome | O(n+m) |
| Aho–Corasick | Trie with failure links, all motifs in one pass | O(n + total motif length + matches) |
| Suffix Array | Prefix doubling + binary search; LCP (Kasai) for repeats | O(n log n) build, O(m log n) query |

n = genome length, m = motif length.

## Requirements

- Java **JDK 8 or above** (tested on JDK 21)
- No external libraries
- Windows, Linux or macOS

## How to run

Compile all the Java files and start the menu:

```bash
javac -d out $(find . -name "*.java")
java -cp out texthack.app.Main
```

Other modes:

```bash
java -cp out texthack.app.Main test     # run the 39 self-tests
java -cp out texthack.app.Main demo     # demo on a 1,000,000-base genome
java -cp out texthack.app.Main scale 100000 1000000 5000000 10000000
```

> Check the package name and folder layout against your project. Change the commands if your files are arranged differently.

### Example

Genome: `ATGCGATAGCGATGCG`
Motifs: `ATG, GCG, TAG` with `--rc`

```
Motif    Strand  Count   Positions
---------------------------------------
ATG       +       2       0, 11
ATG       -       0       not found
GCG       +       3       2, 8, 13
GCG       -       0       not found
TAG       +       1       6
TAG       -       0       not found
```

Positions are **0-based** (the first base is position 0). Strand `-` means the reverse complement was found.

## Results

All results are from one run on JDK 21. Your times will be different, but the order should be the same.

**Correctness:** 39 of 39 self-tests passed. This includes 3000 random texts where every algorithm gave the same answer as Naive.

**Benchmark, 1,000,000 bases, 6 motifs:**

| Algorithm | Matches | Time (ms) |
|-----------|--------:|----------:|
| Naive | 19379 | 37.597 |
| KMP | 19379 | 29.541 |
| Rabin–Karp | 19379 | 60.473 |
| Aho–Corasick | 19379 | 10.812 |
| Z algorithm | 19379 | 53.340 |

**Scaling (median time):**

| Genome size | Naive | KMP | Rabin–Karp | Aho–Corasick | Z |
|------------:|------:|----:|-----------:|-------------:|--:|
| 100,000 | 3.95 ms | 3.62 ms | 5.33 ms | 1.12 ms | 4.70 ms |
| 1,000,000 | 44.98 ms | 28.30 ms | 54.15 ms | 10.75 ms | 47.87 ms |
| 5,000,000 | 197.32 ms | 150.73 ms | 274.36 ms | 53.52 ms | 272.13 ms |
| 10,000,000 | 409.42 ms | 317.20 ms | 506.51 ms | 110.66 ms | 544.84 ms |

**Worst case for Naive** (400,000 × `A`, motif = 599 × `A` + `C`): Naive took 223.8 ms and KMP took 1.1 ms. KMP was about 200 times faster.

**Longest repeated region** in a 1,000,000-base genome: 19 bases (`TATTACGACGAAAGTGAAT`).

**What we learned**

- Aho–Corasick is fastest when many motifs are searched, because it needs only one pass.
- On random DNA, Naive is close to the others because mismatches happen early.
- On bad input, Naive is much slower and KMP stays fast.

## Project status

**Done:** all six algorithms, reverse-complement search, CSV export, FASTA load and save, benchmark, self-tests, slides.
**Left:** final report (code listing), final presentation and demo.

## Future scope

- Wildcard (IUPAC) symbols such as N, R and Y inside motifs
- Approximate matching (k mismatches, edit distance)
- Graphical user interface with charts
- GenBank file support and per-record results for multi-record FASTA files
- Position-weight-matrix scoring of motifs
- Parallel or GPU processing for very large genomes
