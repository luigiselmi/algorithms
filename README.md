Algorithms
==========

This repository contains Java code examples based on the online course Algorithms, [Part 1](https://www.coursera.org/learn/algorithms-part1) 
and [Part 2](https://www.coursera.org/learn/algorithms-part2), taught by Kevin Wayne and Robert Sedgewick and offered by Princeton University 
on Coursera. The online lessons are based on the book Algorithms, 4th Edition, by the same authors. Additional material is also available from 
the [book's website](https://algs4.cs.princeton.edu/home/). This repository contains also my submissions to the programming assignments. This 
is a work in progress, see the assessments to know whether the code passes all the tests for correctness, memory, timing and other metrics. 
Maven is used to compile and execute the code. **The Java files that have been developed as solutions to the assignments are encrypted in order 
to comply with the Coursera Honor’s Code** (see [Source Code Encryption](#Source-Code-Encryption) section).

## Compile and execute
You can compile and execute the code using Java or Maven.

### Compile and execute with Java
In order to compile the java source files you have first to create a folder that will contain all the compiled classes. You 
can organize the folder as with Maven, with a *target* folder and a *classes* sub-folder. From the project root folder execute

```
$ mkdir -p target/classes 
```
In order to compile the Java code you need the [algs4.jar](https://algs4.cs.princeton.edu/code/) Java library to be added to the classpath. In 
the example the library is in the *lib* folder. Since the Java compiler cannot find recursively all the Java files by itself, you need to add a 
bash *find* command

```
$ javac -cp "lib/algs4.jar" -d target/classes $(find src/main/java -name '*.java')
```

In order to execute a Java class, from the project root folder, you need to add the compiled classes and the algs4.jar library to the classpath. 
As an example the command 

```
$ java -cp "lib/algs4.jar;target/classes" searching.WordCounter < resources/searching/tinyTale.txt
```

executes the *WordCounter* Java class, in the *searching* package, that takes in input a stream from a file (not the file name !) containing 
text and writes, in the standard output, the number of distinct words and the complete words list. 

It can be useful to execute some of the classes that are provided in the algs4.jar library. As an example

```
$ java -cp "lib/algs4.jar" edu.princeton.cs.algs4.FrequencyCounter 8 < resources/searching/tinyTale.txt
```

executes the Java class FrequencyCounter, in the edu.princeton.cs.algs4 package in the algs4.jar library, that takes as an argument the minimum 
length of a word to be counted, a list of words from the input stream fed by a text file, and prints the most frequent word with at least that 
length.     

### Compile and execute using Maven
You can compile all the Java classes using Maven

```
$ mvn compile
```

When the command returns, a new *target* folder is created, as Maven's convention, with a sub-folder *classes* containing all the compiled java 
classes. Examples of how to execute a Java class using Maven are provided in the following sections. 

## Part I
Part I covers elementary data structures, sorting, and searching algorithms. 

### Week 1
The main topics of the 1st week are analysis of algorithms and union-find. An algorithm should not only be correct, that is, provide the expected 
result, it should do so in a reasonable amount of time. The performance of an algorithm is measured in terms of the size of its input N. For example
we want to know the amount of time that is required by our algorithm to sort N strings. Algorithms are commonly classified by their order-of-growth

|Class|Order-of-growth|
|-----|--------------:| 
|constant|1|
|logarithmic|log(N)| 
|linear|N|
|linearithmic|N*log(N)|
|polynomial|N^2, N^3|
|exponential|2^N|
 
Polynomial times do not scale, let alone exponential times, so our goal is to discover algorithms that solve our problems in less than polynomial 
time. A first example of the relevance of the performances of an algorithm to being able to solve problems in the real world is union-find or finding
whether two nodes in a graph are connected. This abstract problem arises in many applications, one of them is percolation, [this week assignment](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php), 
in which the goal is to determine the number of sites that have to be opened for a square lattice to percolate. The ratio between the number of open sites
and the total number of sites N is called percolation threshold and it is a constant that represents a phase transition of the system. No analytical solution
has been found so far to determine this threshold. The square lattice is made up of N^2 blocked site that are opened randomly one by one till the system 
percolates. A Java class is available to execute many tests so that the result is statistically significant. It can be executed with the command

```
$ java -cp "lib/algs4.jar;target/classes" assignments.percolation.PercolationStats 200 10000
```

where the first argument is the number N that is used to build the N^2 square lattice and the 2nd argument is the number of tests. The results are printed out 

```
mean                    = 0.592671
stddev                  = 0.009708
95% confidence interval = [0.592480, 0.592861]
```

Three algorithms are discussed as possible solution: quick-find, quick-union and weighted quick-union.    

You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.percolation.PercolationVisualizer" -Dexec.args="resources/assignments/percolation/luigi_selmi.txt"
```

![Luigi Selmi](resources/assignments/percolation/luigi_selmi.png)
### Week 2 
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php) is in the queues 
folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.queues.Permutation" -Dexec.args="8" < resources/assignments/queues/distinct.txt
```

### Week 3
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php) is in the 
collinear folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.collinear.FastCollinearPoints" -Dexec.args="resources/assignments/collinear/input8.txt" 
```

![linear patterns](resources/assignments/collinear/collinear.jpg)

### Week 4
The main topics are priority queues and the heap sort algorithm. A Priority queue is a data structure  that provides two
main APIs:

* insert
* remove the maximum

It can be implemented using a stack or a queue but it's more convenient to use a heap-ordered binary tree on top of an array. The heapsort 
algorithm is an optimal sorting algorithm that can be divided in two phases. In the 1st phase the algorithm builds an heap-ordered binary tree 
with the maximum value at its root. In the 2nd phase, the root element is repeatedly exchanged with the last one and removed in order to produce 
a list of ordered elements. A priority queue can be used to implement the A* algorithm and solve the 8-puzzle and the 15-puzzle finding the least 
set of tile moves to reach the goal with all the tiles ordered. The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php) 
and solver of the puzzles is in the puzzle folder. As usual you can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.puzzle.Solver" -Dexec.args="resources/assignments/puzzle/puzzle04.txt"
```

### Week 5
The topics of week 5 are balanced search trees and their applications. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php) 
is about kd-trees and in particular 2d-trees. The Java class KdTreeVisualizer.java computes and draws the 2d-tree that results from the sequence 
of points clicked by the user in the standard drawing window.

```
$ mvn exec:java -Dexec.mainClass="assignments.kdtree.KdTreeVisualizer"
```

The Java class NearestNeighborVisualizer.java reads a sequence of points from a file (specified as a command-line argument) and inserts those 
points into a 2d-tree. Then, it performs nearest-neighbor queries on the point corresponding to the location of the mouse in the standard drawing 
window.

```
$ mvn exec:java -Dexec.mainClass="assignments.kdtree.NearestNeighborVisualizer" -Dexec.args="resources/assignments/kdtree/circle100.txt"
```

The Java class RangeSearchVisualizer.java reads a sequence of points from a file (specified as a command-line argument) and inserts those points 
into a 2d-tree. Then, it performs range searches on the axis-aligned rectangles dragged by the user in the standard drawing window.

```
$ mvn exec:java -Dexec.mainClass="assignments.kdtree.RangeSearchVisualizer" -Dexec.args="resources/assignments/kdtree/circle100.txt"
```

### Week 6
This week is about hash tables. There is no programming assignment. 

## Part II
Part II focuses on graph and string-processing algorithms.

### Week 1
This week is about undirected and directed graph (aka digraphs). The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/wordnet/specification.php) 
is about creating a digraph from the **WordNet semantic lexicon** and finding specific paths between the synsets (vertices) and computing the relatedness 
of two nouns. Three Java classes must be implemented for this assignment. The 1st one to be implemented is SAP.java that is used to compute the 
shortest ancestral path between two vertices. You can use SAP.java, with some test files, executing the Java class and entering pair of integers, 
that represent two vertices, from the command line

```
$ mvn exec:java -Dexec.mainClass="assignments.wordnet.SAP" -Dexec.args="resources/assignments/wordnet/digraph1.txt"
 ```
 
The 2nd Java class to be implemented is WordNet.java that reads two files. The 1st file contains a set of synsets from WordNet, and the 2nd file 
contains the relationships (hypernyms) between the vertices. You can test the correctness of the implementation by executing its main method.

```
$ mvn exec:java -Dexec.mainClass="assignments.wordnet.WordNet" -Dexec.args="resources/assignments/wordnet/synsets.txt resources/assignments/wordnet/hypernyms.txt"
```

Finally the 3rd class, Outcast.java, can be used to measure the semantic distance between words, provided in a file, to return the word that is 
the least related to the other ones, as in the example where outcast5.txt contains the words "horse", "zebra", "cat", "bear", "table", and Outcast 
returns correctly the word "table".

```
$ mvn exec:java -Dexec.mainClass="assignments.wordnet.Outcast" -Dexec.args="resources/assignments/wordnet/synsets.txt resources/assignments/wordnet/hypernyms.txt resources/assignments/wordnet/outcast5.txt"
```

### Week 2
This week is about undirected and directed edge-weighted graphs in which a weight, or cost, is associated to each edge. The addition of a weight 
enables the representation of many practical problems where a weight can represent the distance between two vertices or some other parameter. 
The main algorithms discussed are Prim's algorithm and Kruskal's algorithm to find the minimum spanning tree in an edge-weighted undirected graph, 
and the Dijkstra's algorithm to find the shortest paths from a source vertex to all the other vertices in a directed edge-weighted graph with 
nonnegative weights. One important class of problems, that can be represented by edge-weighted directed graphs and modeled as shortest-paths 
problems, is job scheduling. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/seam/specification.php) is about 
**seam-carving**, a content-aware image resizing technique. The code in SeamCarver.java provides the API to find horizontal and vertical seams. A vertical
seam is a path, from the top border of the image to the bottom one, in which each pixel is chosen to have the minimum energy among the adjacent 
pixels. The same applies for horizontal seams that go from the left border of the image to the right one. The Java class can be used by a client 
application such as ResizeDemo. As an example the following command removes 200 vertical seams and 100 horizontal seams from an image of 600x300 
pixels of a chameleon so that the image size will be reduced to 400x200 preserving its main characteristics.   
   
```
$ mvn exec:java -Dexec.mainClass="assignments.seam.ResizeDemo" -Dexec.args="resources/assignments/seam/chameleon.png 200 100"
```
The original image:

![original image](resources/assignments/seam/chameleon.png)

The image resized using seam-carving

![resized image](resources/assignments/seam/chameleon-400x200.png)
  
### Week 3
The material of the 3rd week is about network-flow algorithms. These algorithms are of great interest because they can be used to solve optimization
problems in many different contexts, for instance to find the optimal way to distribute oil through a pipeline network from the oil field to a refinery. 
A common network-flow algorithm is the Ford-Fulkerson scheme. It is based on a graph whose edges can have two attributes: a capacity and a flow. The
edge's capacity is a number that specifies the maximum value that can be moved between its vertices, and the flow is the value that is actually used.
The algorithm is also called **maxflow algorithm** because it solves the problem of finding the best way to distribute a flow from a source through a network to a
sink so that the flow is maximal given the capacities of the edges. In order to showcase the applicability of the scheme to many different applications, 
it will be used in the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/baseball/specification.php) to solve the [baseball elimination problem](https://en.wikipedia.org/wiki/Maximum_flow_problem#Baseball_elimination).
As an example, the following command finds which team is mathematically eliminated in a baseball division with 5 teams. Given the scenario in resources/assignments/baseball/teams5.txt
```
Team        wins loss left  NY Bal Bos Tor Det
----------------------------------------------
New_York     75   59   28    0   3   8   7   3
Baltimore    71   63   28    3   0   2   7   7
Boston       69   66   27    8   2   0   0   3
Toronto      63   72   27    7   7   0   0   3
Detroit      49   86   27    3   7   3   3   0
```   

Executing baseball elimination

```
$ mvn exec:java -Dexec.mainClass="assignments.baseball.BaseballElimination" -Dexec.args="resources/assignments/baseball/teams5.txt"
```

produces the desired output

```   
Baltimore is not eliminated
Boston is not eliminated
Detroit is eliminated by the subset R = { Toronto Boston Baltimore New_York }
New_York is not eliminated
Toronto is not eliminated
```

### Week 4
The first topic of the 4th week is about specialized algorithms for searching in symbol tables with string keys. The difference with symbol tables discussed
in the first part of the course is that the algorithms don't use comparisons for searching and so they can be faster. The 2nd topic is substring search that has 
application in many fields, from search engines to genetics. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/boggle/specification.php) is about
developing an application to find all the valid words composed of random letters displayed on cubes' faces and kept on a board, as in the word game Boggle. The longer the word found
the higher is the score. The main steps are first building the adjacency list of each cube and then searching for words, using letters on adjacent cubes, that are contained in a 
dictionary. The dictionary of valid words can be conveniently stored in a Trie, a symbol table with string keys, while the search can be performed recursively using depth-first search 
on the graph of cubes with their adjacent neighbors. The Boggle solver can be tested using static board and dictionary files as in the example  

```
$ mvn exec:java -Dexec.mainClass="assignments.boggle.BoggleSolver" -Dexec.args="resources/assignments/boggle/dictionary-algs4.txt resources/assignments/boggle/board4x4.txt"
```

that outputs the valid words that have been found using the letters in the given board with their score

```
AID, score = 1
DIE, score = 1
END, score = 1
ENDS, score = 1
EYE, score = 1
ONE, score = 1
ONES, score = 1
PAID, score = 1
PAINS, score = 2
PAT, score = 1
PATE, score = 1
SEND, score = 1
SIDE, score = 1
SIN, score = 1
SINE, score = 1
SIT, score = 1
SITE, score = 1
TAT, score = 1
TIE, score = 1
TIED, score = 1
TIN, score = 1
TINY, score = 1
TYPE, score = 1
UNIT, score = 1
UNITE, score = 2
UNITED, score = 3
USE, score = 1
YET, score = 1
YOU, score = 1
Number of words found = 29, Score = 33, Timing = 0.039000 (seconds)
```
### Week 5
The topics of the 5th week are **regular expressions** and **data compression**. A regular expressions, by definition, is a pattern that describes a set of strings. They are used in substring search when the pattern to be found is not completely specified. For example 
we might want to search for a substring in a genomic sequence that stretches 5 bases, starting with adenine A and cytosine C and ending with thymine T, without specifying exactly which bases are in between. Regular expressions allows the use of operators to define the set of strings
to be searched, the fundamental ones being concatenation, closure, and the logic or operator. An application that can interpret regular expressions, parses the expression and builds a nondeterministic finite-state automaton that will be able to find the pattern in the text. The
most famous such application is **grep** that is available in any unix-like operating system and can be used, as in our simple use case in which only the concatenation operator is used  

```
$ echo "ACAGCATACTATCGGGAACTATCCTACGAT" | grep -o -e "AC..T"
```

that prints out the list of occurrences of the substrings found in the sequence that follow the pattern

```
ACTAT
ACTAT
ACGAT
```

Data compression is still an important topic since it enables to save space for data storage and reduce the amount of time to transfer data. It is also the topic of this week's assignment. The [assignment](https://coursera.cs.princeton.edu/algs4/assignments/burrows/specification.php) covers only lossless compression for which no information 
is lost and the data after compression and expansion is exactly the same as the original one. The measure of the ability of an algorithm to compress a data set is called compression ratio, that is the ratio between the size of the compressed data and the size of the original one. A lossless
compression algorithm exploits three characteristics of a data set, binary data or text: the alphabet used to represent the data, the presence of long sequences of identical bits/characters, and the frequency in which different characters are used. These three characteristics of the data are 
exploited in the Java classes that must be implemented for the Burrows-Wheeler data compression algorithm in order to prepare the data for compression using the Huffman algorithm. The Java classes must be used in sequence for compression and in the reverse order for expansion.
 
For compression use the Java classes as in the following command:

```
$ java -cp "lib/algs4.jar;target/classes" assignments.burrows.BurrowsWheeler - < resources/assignments/burrows/abra.txt | java -cp "lib/algs4.jar;target/classes" assignments.burrows.MoveToFront - | java -cp "lib/algs4.jar" edu.princeton.cs.algs4.Huffman - | java -cp "lib/algs4.jar" edu.princeton.cs.algs4.HexDump 16
```

For expansion, follow the next example:

```
$ java -cp "lib/algs4.jar" edu.princeton.cs.algs4.Huffman + < resources/assignments/burrows/abra.txt.bwt.mtf.huf | java -cp "lib/algs4.jar;target/classes" assignments.burrows.MoveToFront + | java -cp "lib/algs4.jar;target/classes" assignments.burrows.BurrowsWheeler + 
```

## Source Code Encryption
The Java source code of the solution of the assignments has been encrypted to comply with the [Coursera Honor's Code](https://learner.coursera.help/hc/en-us/articles/209818863-Coursera-Honor-Code) using [OpenSSL](https://www.openssl.org/)
and the Advanced Encryption Standard (AES) symmetric cipher with a 256 bits long key in CBC mode. The command for the encryption is like in the example

```
$ openssl enc -e -aes-256-cbc -in SeamCarver.java -out SeamCarver.java.enc -pass file:secret
```

where "secret" is the name of the file containing the pass-phrase. Use the next command for decryption 
  
```
$ openssl enc -d -aes-256-cbc -in SeamCarver.java.enc -out SeamCarver.java -pass file:secret
```

## Performances
### Sorting
|Algorithm|Running Time|Space|
|---------|------------|-----|

### Searching
|Algorithm|Running Time|Space|
|---------|------------|-----|
### Graphs
|Algorithm|Running Time|Space|
|---------|------------|-----|
### Strings
|Algorithm|Running Time|Space|
|---------|------------|-----|