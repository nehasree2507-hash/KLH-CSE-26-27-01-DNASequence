# DNA Sequence Analysis Tool Using Advanced String Matching Algorithms 

## Team Members

- 2520030010 G Boomika Sre
- 2520030174 P.Nehasree
- 2520030282 P.Hasini

## Supervisor

- Supervisor Name

## Abstract

This project develops a DNA sequence analysis tool for searching biological
motifs in large genome sequences. The tool accepts a genome sequence and one
or more DNA motifs as input and reports the locations where each motif occurs.

The project implements and compares three string-matching algorithms:
Brute Force, Knuth–Morris–Pratt (KMP), and Aho–Corasick. Brute Force is used
as a basic reference algorithm, KMP provides efficient single-pattern matching,
and Aho–Corasick supports simultaneous searching of multiple motifs.

The system is designed to process genome sequences of up to 100 MB. It
demonstrates the application of data structures and string algorithms to
bioinformatics problems.

## Project Objective

- Search for DNA motifs in a genome sequence.
- Support multiple motifs simultaneously.
- Report accurate match positions.
- Compare the performance of different algorithms.
- Process large DNA sequences efficiently.

## Input

The program accepts:

1. A genome sequence containing the symbols A, C, G, and T.
2. One or more DNA motifs to be searched.

Example:

```text
Genome:
ACGTACGTGACCTAGCTAGCTAG

Motifs:
ACGT
TAG
GAC
```

## Output

The program displays:

- Motif sequence.
- Position of every match.
- Number of occurrences.
- Execution time of each algorithm.

Example:

```text
Motif       Positions       Count
ACGT        1, 5             2
TAG         13, 17           2
GAC         9               1
```

Positions are reported using 1-based indexing.

## Algorithms Used

### Brute Force

Checks the motif at every possible position in the genome.

Time complexity:

```text
O(nm)
```

### Knuth–Morris–Pratt

Uses a Longest Prefix Suffix table to avoid unnecessary comparisons.

Time complexity:

```text
O(n + m)
```

### Aho–Corasick

Builds a trie containing all motifs and uses failure links to search for
multiple motifs in a single scan.

Time complexity:

```text
O(n + L + z)
```

where:

- n is the genome length.
- L is the total length of all motifs.
- z is the number of matches.

## Requirements

- Python 3.8 or later
- No external libraries are required for the basic version.

## Setup Instructions

Clone or download the project:

```bash
git clone <repository-link>
cd DNA-Motif-Searching
```

Optional: create a virtual environment:

```bash
python -m venv venv
```

Activate it on Windows:

```bash
venv\Scripts\activate
```

Activate it on Linux or macOS:

```bash
source venv/bin/activate
```

## Execution Instructions

Run the main program:

```bash
python SRC/main.py
```

If the program accepts input files:

```bash
python SRC/main.py \
    --genome DATA/sample_genome.txt \
    --motifs DATA/sample_motifs.txt
```

The results will be saved in:

```text
OUTPUT/motif_matches.csv
```

## Current Phase Status

### Completed

- Project topic selected.
- Problem statement prepared.
- Input and output format defined.
- Brute Force algorithm designed.
- KMP algorithm designed.
- Aho–Corasick algorithm designed.
- Basic DNA validation implemented.
- Folder structure created.

### In Progress

- Integrating all algorithms into the main program.
- Testing with multiple motifs.
- Testing with large genome sequences.
- Comparing execution times.
- Preparing graphs and final documentation.

### Pending

- Final user interface.
- Complete performance analysis.
- Final report.
- Presentation slides.
- Final testing and demonstration.

## Limitations

- The current version searches for exact motif matches.
- Approximate matches and mutations are not yet supported.
- Biological interpretation of detected motifs is outside the current scope.

## Future Enhancements

- Reverse-complement searching.
- Approximate motif matching.
- FASTA file support.
- Graphical user interface.
- CSV and JSON report generation.
- Parallel processing for very large genome datasets.

## License

This project is developed for academic purposes.
