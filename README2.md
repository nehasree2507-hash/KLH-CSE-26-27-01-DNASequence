# DNA Sequence Analysis Tool

A Java program that finds a short DNA pattern (called a motif) inside a long DNA sequence.

Team: G. Boomika Sre, P. Nehasree, P. Hasini

```
Guide: Dr. Ch. Anuradha
```

## What it does

- Reads a DNA sequence from a FASTA file, or makes one for you
- Finds every place where your motif appears
- Shows how many matches, where they are, and the time taken
- Can also search the reverse side of the DNA
- Can save the results in a CSV file
- Can compare all the algorithms to see which is fastest

## Algorithms used

1. Naive
2. KMP
3. Rabin-Karp
4. Z-function
5. Aho-Corasick
6. Suffix Array

## What you need

- Java JDK 8 or above

## How to run

```
javac -d out $(find . -name "*.java")
java -cp out texthack.app.Main
```

Then choose an option from the menu.

## Example

DNA: ATGCGATAGCGATGCG

| Motif | Count | Positions |
|-------|-------|-----------|
| ATG | 2 | 0, 11 |
| GCG | 3 | 2, 8, 13 |
| TAG | 1 | 6 |

Positions start from 0.

## Results

- All 39 tests passed
- Aho-Corasick was the fastest when searching many motifs
- It worked on DNA with 10 million letters
- In the worst case, KMP was about 200 times faster than Naive

## Future work

- Search with wildcard letters
- Search with small mistakes allowed
- A screen with buttons and charts
- Use more cores to go faster
