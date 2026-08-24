# DNA Sequence Analysis Tool Using Advanced String Matching Algorithms

## Team Members
- **2520030010** – G. Boomika Sre
- **2520030174** – P. Nehasree
- **2520030282** – P. Hasini

## Supervisor
**Dr. Ch. Anuradha**

---

# Abstract

The **DNA Sequence Analysis Tool Using Advanced String Matching Algorithms** is designed to efficiently identify DNA motifs within genome sequences using advanced string matching techniques. The application accepts a DNA sequence and one or more motifs as input and identifies all occurrences of the motifs using algorithms such as **Knuth–Morris–Pratt (KMP), Rabin–Karp, and Aho–Corasick**. The system also compares the execution time and performance of these algorithms on genome datasets of varying sizes. This project demonstrates the practical application of Data Structures and Algorithms in bioinformatics by providing an accurate, scalable, and efficient solution for DNA pattern searching.

---

# Objectives

- Develop a DNA sequence analysis tool for efficient motif searching.
- Implement advanced string matching algorithms such as KMP, Rabin–Karp, and Aho–Corasick.
- Support searching for multiple DNA motifs simultaneously.
- Compare the execution time and efficiency of different algorithms.
- Process genome datasets of varying sizes efficiently.
- Demonstrate the application of Data Structures and Algorithms in computational biology.

---

# Literature Grounding

## 1. Knuth, Morris & Pratt (1977)
**Title:** *Fast Pattern Matching in Strings*

This paper introduced the Knuth–Morris–Pratt (KMP) algorithm, which preprocesses the search pattern using the Longest Prefix Suffix (LPS) array to eliminate unnecessary comparisons during pattern matching.

**Conclusion:**  
The KMP algorithm performs exact pattern matching in linear time **O(n + m)**, making it suitable for searching DNA motifs in long genome sequences.

---

## 2. Rabin & Karp (1987)
**Title:** *Efficient Randomized Pattern Matching Algorithms*

The Rabin–Karp algorithm uses hashing techniques to efficiently compare patterns with substrings of the text and is particularly useful for searching multiple patterns.

**Conclusion:**  
Hash-based searching significantly improves average-case performance and provides an efficient approach for DNA motif searching.

---

## 3. Aho & Corasick (1975)
**Title:** *Efficient String Matching: An Aid to Bibliographic Search*

This research introduced the Aho–Corasick algorithm, which constructs a trie with failure links to search multiple patterns simultaneously in a single scan.

**Conclusion:**  
Aho–Corasick is one of the most efficient algorithms for multi-pattern matching and is highly applicable to large-scale DNA sequence analysis.

---

## 4. Dan Gusfield (1997)
**Book:** *Algorithms on Strings, Trees, and Sequences*

The book discusses advanced string processing algorithms and their applications in computational biology, genome sequencing, and pattern matching.

**Conclusion:**  
Efficient string matching algorithms play a crucial role in modern bioinformatics and biological sequence analysis.

---

## 5. National Center for Biotechnology Information (NCBI)

NCBI provides publicly available genomic datasets used worldwide for biological research and algorithm evaluation.

**Conclusion:**  
Large standardized genome datasets are essential for validating the accuracy and efficiency of DNA sequence analysis algorithms.

---

# Design Methodology

The proposed system follows a modular approach for efficient DNA motif searching.

1. Read the DNA genome sequence from the user or a dataset.
2. Accept one or more DNA motifs as input.
3. Select the desired string matching algorithm (KMP, Rabin–Karp, or Aho–Corasick).
4. Preprocess the pattern(s) according to the selected algorithm.
5. Search the genome sequence to identify all matching positions.
6. Display the matching indices, number of occurrences, and execution time.
7. Compare the performance of all implemented algorithms based on execution time and search efficiency.

This methodology ensures efficient searching while minimizing computational complexity, making the system suitable for large genomic datasets.

---

# Technical Soundness

### Programming Language
- Java

### Data Structures
- Strings
- Arrays
- Trie
- Prefix (LPS) Array
- Hash Tables

### Algorithms
- Knuth–Morris–Pratt (KMP)
- Rabin–Karp
- Aho–Corasick

### Time Complexity

| Algorithm | Time Complexity |
|-----------|-----------------|
| Naïve String Matching | O(n × m) |
| KMP | O(n + m) |
| Rabin–Karp | Average O(n + m) |
| Aho–Corasick | O(n + m + z) |

where:
- **n** = Length of genome sequence
- **m** = Length of motif
- **z** = Number of matches found

---

# Setup and Execution Instructions

## Requirements

- Java Development Kit (JDK) 17 or later
- Visual Studio Code / IntelliJ IDEA / Eclipse
- Command Prompt or Terminal

## Steps

1. Clone or download the project repository.
2. Open the project in your preferred Java IDE.
3. Compile the Java source files.
4. Run the main program.
5. Enter the genome sequence and DNA motif(s).
6. View the matching positions, total matches, and execution time.

### Example Input

**Genome**
```
ACGTACGTGACCTAGCTAGCTAG
```

**Motifs**
```
ACGT
TAG
GAC
```

### Example Output

| Motif | Positions | Count |
|-------|-----------|------:|
| ACGT | 1, 5 | 2 |
| TAG | 13, 17 | 2 |
| GAC | 9 | 1 |

*Positions use 1-based indexing.*

---

# Current Phase Status

## Completed

- Project title finalized.
- Team members and supervisor finalized.
- Problem statement and objectives prepared.
- Literature survey completed.
- Functional and system requirements documented.
- Dataset requirements and sample test cases prepared.
- Algorithms selected (KMP, Rabin–Karp, and Aho–Corasick).
- Initial project structure created.

## Currently in Progress

- Implementing the KMP algorithm in Java.
- Implementing the Rabin–Karp algorithm.
- Implementing the Aho–Corasick algorithm.
- Integrating algorithms into the main application.
- Preparing datasets for performance testing.

## Pending

- Performance comparison of all algorithms.
- Testing with large genome datasets.
- Preparing execution time analysis.
- Final documentation.
- Final presentation and project demonstration.

---

# Project Scope

The project focuses on exact DNA motif matching using advanced string matching algorithms. It identifies the locations of one or more motifs within a genome sequence and compares algorithm performance. Approximate pattern matching and biological interpretation of motifs are outside the current scope.

---

# Future Improvements

- FASTA file support
- Reverse-complement motif searching
- Approximate pattern matching
- Graphical User Interface (GUI)
- CSV/JSON result export
- Performance visualization using graphs
- Parallel processing for very large genome datasets
```
