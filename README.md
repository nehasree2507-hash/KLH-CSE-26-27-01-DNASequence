DNA Sequence Analysis Tool Using Advanced String Matching Algorithms

Team Members
2520030010 G. Boomika Sre
2520030174 P. Nehasree
2520030282 P. Hasini

Supervisor
Dr CH ANURADHA

Abstract
We developed the DNA Sequence Analysis Tool Using Advanced String Matching Algorithms to efficiently find DNA motifs in genome sequences. The application takes a DNA genome sequence and one or more motifs as input, finds all occurrences by using advanced string matching algorithms like Knuth–Morris–Pratt (KMP), Rabin–Karp, and Aho–Corasick, and shows the matching positions together with execution time and number of matches. This project demonstrates the importance of efficient Data Structures and Algorithms for bioinformatics by evaluating the performance of these algorithms on genome datasets of different sizes. The presented system offers a high performance DNA pattern search solution that is accurate, scalable and suitable for large genomic datasets.


Setup and Execution Instructions
Requirements:
Java Development Kit (JDK) 17 or later.
Command Prompt or Terminal.


Example Input:
Genome:
ACGTACGTGACCTAGCTAGCTAG

Motifs:
ACGT
TAG
GAC

Example Output:
Motif Positions Count
ACGT 1, 5 2
TAG 13, 17 2
GAC 9 1
The positions in the output use 1-based indexing, which means that the first character of the genome is counted as position 1.

Current Phase Status:
Completed:
Project title selected.
Team members finalized.
Problem statement prepared.
Project objectives identified.
Java selected as the programming language.
Input and output formats planned.
KMP and Aho–Corasick algorithms selected.
Basic project folder structure created.
Sample genome and motif data prepared.

Currently in Progress:
Learning and implementing the KMP algorithm in Java.
Learning and implementing the Aho–Corasick algorithm in Java.
Connecting the algorithms to the main program.
Testing the program with different genome sequences and motifs.
Checking whether the reported match positions are correct.
Preparing the project documentation and presentation.

Pending:
Testing the program with a large genome sequence.
Comparing the execution time of KMP and Aho–Corasick.
Preparing performance tables and graphs.
Finalizing the result files.
Completing the project report.
Performing final testing and demonstration.

Overall Status:
Our project is currently in the initial implementation and testing phase. The project topic, objectives, input and output formats, and algorithms have been decided. We are currently working on understanding the algorithms and implementing them in Java. Testing with large genome sequences, performance comparison, documentation, and the final presentation are yet to be completed.

Project Scope:
The current project focuses on exact motif matching. It searches for the given DNA patterns and reports their locations in the genome sequence. The project does not currently identify the biological function of a motif or support approximate matches.

Future Improvements:
Add reverse-complement searching.
Support approximate motif matching.
Add FASTA file support.
Create a graphical user interface.
Export results in CSV or JSON format.
Display graphs comparing algorithm performance.
Add support for parallel processing of very large genome sequences.
