
Algorithms
==========

This repository contains Java code examples based on the online courses Algorithms, [Part 1](https://www.coursera.org/learn/algorithms-part1)
and [Part 2](https://www.coursera.org/learn/algorithms-part2), taught by Kevin Wayne and Robert Sedgewick and offered by Princeton University
on Coursera. The online lessons are based on the book Algorithms, 4th Edition, by the same authors. Additional material is also available on
the [book's website](https://algs4.cs.princeton.edu/home/). This repository contains also my submissions to the programming assignments. All the assignments for both Part 1 and Part 2 have been successfully completed, passing the tests for correctness, memory, timing and other metrics. 
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

executes the *WordCounter* Java class, in the *searching* package, that reads a file from standard input containing some text and writes, to
standard output, the number of distinct words and the complete words list.

It can be useful to execute some of the classes that are provided in the algs4.jar library. As an example

```
$ java -cp "lib/algs4.jar" edu.princeton.cs.algs4.FrequencyCounter 8 < resources/searching/tinyTale.txt
```

executes the Java class FrequencyCounter, in the edu.princeton.cs.algs4 package of the algs4.jar library. The Java class reads from input
stream the minimum length of a word to be counted, then reads from standard input a file containing a list of words and writes to standard
output the most frequent word of at least that length.     

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

Algorithms that needs polynomial time to process do not scale, let alone exponential times, so our goal is to discover algorithms that solve our problems in less than polynomial
time. A first example of the relevance of the performances of an algorithm to being able to solve problems in the real world is union-find or finding
whether two nodes in a graph are connected. This abstract problem arises in many applications, one of them is percolation, [this week assignment](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php),
in which the goal is to determine the number of sites that have to be opened for a square lattice to percolate. A 2 dimensional lattice percolates if any open site
in the top row can be connected through a path of open sites to an open site in the bottom row. The ratio between the number of open sites
and the total number of sites is called [percolation threshold](https://en.wikipedia.org/wiki/Percolation_threshold) and it is a constant that represents a phase transition of the system. No analytical solution
has been found so far to determine this threshold. The square lattice is made up of N^2 blocked site that are opened randomly one by one till the system
percolates. A Java class is available to execute many tests so that the result is statistically significant. It can be executed from the root folder with
the command

```
$ java -cp "lib/algs4.jar;target/classes" assignments.percolation.PercolationStats 200 10000
```

where the first argument is the number N that is used to build the N^2 square lattice and the 2nd argument is the number of tests. The results are printed out

```
mean                    = 0.592671
stddev                  = 0.009708
95% confidence interval = [0.592480, 0.592861]
```

Three algorithms are discussed as possible solution: quick-find, quick-union and weighted quick-union. The first two algorithms are quadratic-time while weighted
quick-union is logarithmic and so can be used for real world applications.   

A lattice can be visualized using a Java application provided in the assignment and executing the command as in the example below

```
$ mvn exec:java -Dexec.mainClass="assignments.percolation.PercolationVisualizer" -Dexec.args="resources/assignments/percolation/luigi_selmi.txt"
```

![Luigi Selmi](resources/assignments/percolation/luigi_selmi.png)
### Week 2
The topics of this week are collections, queues and stacks, and elementary sorting methods. Items in a queue follow the first-in-first-out (FIFO) rule while items in stacks follow
the last-in-first-out (LIFO) rule. They can be implemented using linked list or arrays. Since arrays have to be declared with a size they may need to
be resized. The assignment is about building a collection that supports adding and removing items from either the front or the back of the data structure.
A second task is to create a randomized queue in which the item removed is chosen uniformly at random among items in the data structure.
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php) is in the queues
folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.queues.Permutation" -Dexec.args="8" < resources/assignments/queues/distinct.txt
```

The sorting methods introduced in this week lecture are selection sort, insertion sort and shellsort. They can be used to sort arrays of objects but since
their time complexity is N^2 for the first two and N^(3/2) for shellsort they are best used with small arrays. A useful property of some sorting methods is stability.
Insertion sort is stable so that it can be used to sort an array of objects using different keys preserving the order of each sorting. Selection sort and shellsort
are not stable.

### Week 3
This week's lecture is about two sorting algorithms: mergesort and quicksort. Mergesort is an example of the divide and conquer paradigm. It first divides an array into two
halves, then recursively sorts each half, and finally merge the two halves. Mergesort is used in Java to sort objects. A nice property of mergesort is that it takes a time
proportional to Nlog(N) to sort an array of size N. Mergesort is also stable. The disadvantage is that it needs an extra space proportional to N.
Quicksort is also a recursive algorithm. The algorithm begins with a partition phase in which for each entry in an array it moves larger entries to the right and smaller entries
to the left and finally it recursively sorts the parts. Quicksort takes a time proportional to Nlog(N) to sort an array of N entries and it does not require extra space. Quicksort
is faster than mergesort but the disadvantage is that quicksort is not stable. Quicksort can take quadratic time if the input array is already sorted or partially sorted so usually
the input array is randomly shuffled to improve the performances. Sorting algorithms are used in many obvious and non obvious applications from sorting a list of strings or a search result
to enabling binary search, finding duplicates and also in computational biology and physics. One task of this week's assignment is to write an algorithm based on sorting that can
find line patterns in a given set of points in a plane. The algorithm based on sorting can be compared with one that follows a brute force approach to solve the same problem and it can be seen
that is faster than the brute force algorithm when the number of points is more than one thousand. The plan of the fast algorithm is as follows: given one point p the algorithm computes the
slopes of the segments that join p to all the other points and sorts the points according to the slopes they make with p. When there are four or more adjacent points that have the same slope
with p they belong to the same (maximal) segment. The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php) is in the
collinear folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.collinear.FastCollinearPoints" -Dexec.args="resources/assignments/collinear/mystery10089.txt"
```

As an example of the difference in performance between the two algorithms we use a data set that contains more than 10000 points. The fast algorithm based on sorting completes the task in
30 seconds on average, while the brute force algorithm needs more than one hour and a half. Clearly the message that emerges from the segments is a suggestion for those who use the brute force
approach.

```
Elapsed time (seconds): 29.558000
Number of segments: 34
(1500, 30001) -> (3499, 30001)
(1500, 26001) -> (1500, 30001)
(1500, 26001) -> (3500, 26001)
(3500, 26001) -> (3500, 28001)
(2500, 28001) -> (3500, 28001)
(4501, 29999) -> (6501, 29999)
(4501, 25999) -> (4501, 29999)
(4501, 25999) -> (6501, 25999)
(6501, 25999) -> (6501, 29999)
(8000, 22000) -> (10000, 22000)
(9000, 18003) -> (9000, 22000)
(11000, 21995) -> (13000, 21995)
(11000, 17995) -> (11000, 21995)
(11000, 17995) -> (13000, 17995)
(13000, 17995) -> (13000, 21995)
(14500, 14000) -> (16499, 14000)
(14500, 12001) -> (14500, 14000)
(14500, 12001) -> (16500, 12001)
(16500, 10000) -> (16500, 12001)
(14499, 10000) -> (16500, 10000)
(17501, 9998) -> (17501, 13998)
(17501, 9998) -> (19501, 9998)
(20500, 13999) -> (22500, 13999)
(20500, 9999) -> (20500, 13999)
(20500, 11994) -> (22000, 11994)
(20500, 9999) -> (22500, 9999)
(23500, 13997) -> (25500, 13997)
(23500, 9997) -> (23500, 13997)
(23500, 11997) -> (25000, 11997)
(23500, 9997) -> (25500, 9997)
(26500, 13996) -> (28500, 13996)
(26500, 9996) -> (26500, 13996)
(26500, 11996) -> (28500, 11996)
(28500, 11996) -> (28500, 13996)
```

![linear patterns](resources/assignments/collinear/mystery10089.png)

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
$ mvn exec:java -Dexec.mainClass="assignments.puzzle.Solver" -Dexec.args="resources/assignments/puzzle/puzzle05.txt"
```
As an example we use the Java code to solve a 8-tiles puzzle in 5 moves.

```
Minimum number of moves = 5
3
4 1 3
0 2 6
7 5 8

0 1 3
4 2 6
7 5 8

1 0 3
4 2 6
7 5 8

1 2 3
4 0 6
7 5 8

1 2 3
4 5 6
7 0 8

1 2 3
4 5 6
7 8 0
```

### Week 5
This is the culminating section of the 1st part of the course, where all what we learned before about linked list, arrays, sorting algorithms
and recursion are put in use to address the problem of building and using symbol tables to store and search key-value pairs. Symbol
tables are known under different names such as dictionaries, indices and associative arrays. Unordered symbol tables can be implemented using
linked lists or arrays in which strings can be used as keys instead of integers. Accessing an unordered symbol table requires a time proportional
to its size and the insertion of N key-value pairs requires a time proportional to N^2. Symbol tables in which the keys are ordered have
better performances and can support numerous useful methods. Binary search trees (BST) are an implementation of symbol tables in which the data
is structured in nodes that contain a value, a key and two links, the left link and the right link. The left link connects the given node to a
subtree whose nodes have smaller keys than the given node, while the right link connects to subtree whose nodes have larger keys. Search and
insert operations in BST require a time proportional to log(N) on average. Since with BST the shape of the tree depends on the order in which the
nodes are added to the BST, in the worst case the tree that results from an insertion of N key-value pairs in a BST can be unbalanced and look
like a chain of left links so that accessing a node can require a time proportional to its size. In order avoid such situations and keep the tree
balanced other BST have been developed that allow more than one key and more than two links that guarantee a height proportional to log(N) even
in the worst case scenario. A 2-node is node with one key and two links as in BST, a 3-node is a node with two keys and three links. The data
structure that is built with these two types of nodes is called 2-3 tree. A red-black BST is a 2-3 tree in which a 3-node is represented as two
2-nodes connected by a left "red" link. The color of the link is represented by a flag in the code. Symbol tables are behind many important
applications such as databases and search engines. One other remarkable application of BST is computational geometry in which geometric properties
of objects, such as point coordinates, are used as keys in a BST, enabling the implementation of useful methods such as range search and nearest-neighbor
search. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php) requires the development of two Java
classes to implement such two methods in two different ways, one based on the use of a balanced BST implementation that is available in the java.util package,
and one that requires the implementation of a 2d-tree, that is a binary tree that corresponds to a recursive partition of the plane so that at each level
of the tree the plane is divided alternatively by a horizontal or vertical line that passes through each node in that level of the tree. The 2d-tree
adapts gracefully to the distribution of the points in the plane and it is often a better solution than the grid approach in which the plane is divided
into equally sized squares, particularly when the points are not evenly distributed such as in geographic applications. As an example of how the 2d-tree
recursively splits the space into two rectangles every time a point is created in the plane, and its corresponding node is added to the 2d-tree,
can be seen with the Java class KdTreeVisualizer.java that draws the points clicked by the user in the standard drawing window and the splitting lines.

```
$ mvn exec:java -Dexec.mainClass="assignments.kdtree.KdTreeVisualizer"
```

An example of the partition of the plane to address the nearest neighbor search is shown in the figure below where 100 points are distributed in
a circle with a query point connected to the nearest point in the circle

![nearest neighbor search](resources/assignments/kdtree/circle100.png)

The figure can be created by executing the command

```
$ mvn exec:java -Dexec.mainClass="assignments.kdtree.KdTree" -Dexec.args="resources/assignments/kdtree/circle100.txt"
```

We use the same data set to show the 2d-tree solution of the range search problem in which our two solutions are used to search the points that lie within
a rectangle drawn at run time. The red color represents the points that have been found by the algorithm based on the Java TreeSet and the blue color
represents the points that have been found using our 2d-tree implementation. The fact that both algorithms have found the same points guarantees that
our 2d-tree implementation is correct.

![range search](resources/assignments/kdtree/range_search_circle100.png)

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

```
Is "yellowlegs" a noun ? true
synset id for noun "zone" is 82150
synset id for noun "zone" is 82151
synset id for noun "zone" is 82152
synset id for noun "zone" is 82153
The SAP for "worm" and "bird" is animal animate_being beast brute creature fauna .
The distance between the nouns "worm" and "bird" is 5.
The distance between the nouns "white_marlin" and "mileage" is 23.
The distance between the nouns "Black_Plague" and "black_marlin" is 33.
The distance between the nouns "American_water_spaniel" and "histology" is 27.
The distance between the nouns "Brown_Swiss" and "barrel_roll" is 29.
```

Finally the 3rd class, Outcast.java, can be used to measure the semantic distance between words, provided in a file, to return the word that is
the least related to the other ones, as in the example where outcast5.txt contains the words "horse", "zebra", "cat", "bear", "table", and Outcast
returns correctly the word "table".

```
$ mvn exec:java -Dexec.mainClass="assignments.wordnet.Outcast" -Dexec.args="resources/assignments/wordnet/synsets.txt resources/assignments/wordnet/hypernyms.txt resources/assignments/wordnet/outcast5.txt"
```

### Week 2
This week is about undirected and directed edge-weighted graphs in which a weight, or cost, is associated to each edge. The addition of a weight
to edges enables the representation of many practical problems where a weight can represent the distance between two locations, a job completion
time from start to finish or some other parameter. The main algorithms discussed are Prim's algorithm and Kruskal's algorithm to find the minimum
spanning tree in an edge-weighted undirected graph, and the Dijkstra's algorithm to find the shortest paths from a source vertex to all the other
vertices in a directed edge-weighted graph with nonnegative weights. One important class of problems, that can be represented by edge-weighted
directed acyclic graphs and modeled as shortest-paths problems, is job scheduling. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/seam/specification.php)
is about **seam-carving**, a content-aware image resizing technique that is an application of the shortest paths algorithm on a direct acyclic graph.
The code in SeamCarver.java provides the API to find horizontal and vertical seams. A vertical seam is a path, from the top border of the image to
the bottom one, in which each pixel is chosen to have the minimum energy among the adjacent pixels. The energy of a pixel represents the change in
color in one direction (e.g. top-bottom or left-right). The same applies for horizontal seams that go from the left border of the image to the right
one. The Java class can be used by a client application such as ResizeDemo. As an example the following command removes 200 vertical seams and 100
horizontal seams from an image of 600x300 pixels of a chameleon so that the image size will be reduced to 400x200 preserving its main characteristics.   

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
The first topic of the 4th week is about specialized algorithms for searching in symbol tables with string keys. The difference with symbol tables
discussed in the first part of the course is that the algorithms don't use comparisons for searching and so they can be faster. The 2nd topic is
substring search that has application in many fields, from search engines to genetics. The [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/boggle/specification.php)
is about developing an application to find all the valid words composed of random letters displayed on cubes' faces and kept on a board, as in the
word game Boggle. The longer the word found the higher is the score. The main steps are first building the adjacency list of each cube and then
searching for words, using letters on adjacent cubes, that are contained in a dictionary. The dictionary of valid words can be conveniently stored
in a Trie, a symbol table with string keys, while the search can be performed recursively using depth-first search on the graph of cubes with their
adjacent neighbors. The Boggle solver can be tested using static board and dictionary files as in the example  

```
$ mvn exec:java -Dexec.mainClass="assignments.boggle.BoggleSolver" -Dexec.args="resources/assignments/boggle/dictionary-algs4.txt resources/assignments/boggle/board4x4.txt"
```

that outputs the valid words that have been found in the dictionary using the letters in the given board with their score

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
The Boggle solver is used by the Boggle game to play against the computer. You can start the Boggle game by executing the command

```
$ java -cp "lib/algs4.jar;target/classes" assignments.boggle.BoggleGame 4 4
```
The player can compare the number of valid words found with those found by the solver (opponent). Only the words that can be found
in the selected dictionary are valid. At the end of a game the interface shows the valid words found by the user. It's not that easy
to beat the solver !

![Boggle game](resources/assignments/boggle/boggle_game.png)  

### Week 5
The topics of the 5th week are **regular expressions** and **data compression**. A regular expressions, by definition, is a pattern that describes a
set of strings. They are used in substring search when the pattern to be found is not completely specified. For example we might want to search for
a substring in a genomic sequence that stretches 5 bases, starting with adenine A and cytosine C and ending with thymine T, without specifying exactly
which bases are in between. Regular expressions allows the use of operators to define the set of strings to be searched, the fundamental ones being
concatenation, closure, and the logic or operator. An application that can interpret regular expressions, parses the expression and builds a
nondeterministic finite-state automaton that will be able to find the pattern in the text. The most famous such application is **grep** that is available
in any unix-like operating system and can be used, as in our simple use case in which only the concatenation operator is used  

```
$ echo "ACAGCATACTATCGGGAACTATCCTACGAT" | grep -o -e "AC..T"
```

that prints out the list of occurrences of the substrings found in the sequence that follow the pattern

```
ACTAT
ACTAT
ACGAT
```

Data compression is still an important topic since it enables to save space for data storage and reduce the amount of time to transfer data. It is also
the topic of this week's assignment. The [assignment](https://coursera.cs.princeton.edu/algs4/assignments/burrows/specification.php) covers only lossless
compression for which no information is lost and the data after compression and expansion is exactly the same as the original one. The measure of the
ability of an algorithm to compress a data set is called compression ratio, that is the ratio between the size of the compressed data and the size of the
original one. A lossless compression algorithm exploits three characteristics of a data set: the alphabet used to represent the data,
the presence of long sequences of identical bits/characters, and the frequency in which different characters are used in the data set. For instance, instead
of using 7 bits to represent each character in the ASCII code we can use two bits to represent the 4 most common characters and additional bits for the less
common characters. The code of each character must not be a prefix of any other character's code (prefix-free code). This idea is implemented in the
**Huffman encoding**. For encoding, the Huffman algorithms first reads the input data, computes the frequencies of each character and builds a binary tree
(trie) by merging pairs of nodes with the smallest frequency values. Each character appears as a leaf in the tree at the end of a path of nodes
connected to their parents through a '0' or a '1' link. The output of the Huffman algorithm contains the binary tree and the encoding of the input string.
Both are required for the decoding phase.
The **Burrows-Wheeler** data compression algorithm, the assignment of this week, consists of three components:

1. Burrows-Wheeler transform
2. Move-To-Front encoding
3. Huffman compression

These components are used to process the input data in that same sequence. The Barrow-Wheeler transform moves equal characters close together.
The Move-to-Front encoding changes the order of the characters in the alphabet depending on the frequency and the order in which they appear in the data
set so that the output will contain many equal integers. Finally the Huffman compression will be able to achieve a better compression ratio using the data
from the two previous steps.

For compression use the Java classes as in the following example:

```
$ java -cp "lib/algs4.jar;target/classes" assignments.burrows.BurrowsWheeler - < resources/assignments/burrows/aesop.txt | java -cp "lib/algs4.jar;target/classes" assignments.burrows.MoveToFront - | java -cp "lib/algs4.jar" edu.princeton.cs.algs4.Huffman - > aesop.bw
```

The size of the input data used in the example, aesop.txt is 188k, the size of the compressed file, aesop.bw is 65k and the compression ratio is 0.345.

To expand the compressed file and get the same input file, execute the command:

```
$ java -cp "lib/algs4.jar" edu.princeton.cs.algs4.Huffman + < resources/assignments/burrows/aesop.bw | java -cp "lib/algs4.jar;target/classes" assignments.burrows.MoveToFront + | java -cp "lib/algs4.jar;target/classes" assignments.burrows.BurrowsWheeler +
```

The size of the expanded file is exactly the same of the input file. The expansion is much faster than the compression. Finally we compute the compression
ratio of the Huffman coding alone for the same input file, aesop.txt

```
java -cp "lib/algs4.jar" edu.princeton.cs.algs4.Huffman - < resources/assignments/burrows/aesop.txt > aesop.huf
```

The size of the compressed file, aesop.huf, is 106k and the compression ratio is 0.563. We can say that the Burrows-Wheeler algorithm achieves a significantly
better result, more than 1.5 times better for that specific file.

## Source Code Encryption
The Java source code of the solution of the assignments has been encrypted to comply with the [Coursera Honor's Code](https://learner.coursera.help/hc/en-us/articles/209818863-Coursera-Honor-Code)
using [OpenSSL](https://www.openssl.org/) and the Advanced Encryption Standard (AES) symmetric cipher with a 256 bits long key in CBC mode. The command
for the encryption is like in the example

```
$ openssl enc -e -aes-256-cbc -in SeamCarver.java -out SeamCarver.java.enc -pass file:secret
```

where "secret" is the name of the file containing the pass-phrase. Use the next command for decryption

```
$ openssl enc -d -aes-256-cbc -in SeamCarver.java.enc -out SeamCarver.java -pass file:secret
```

## Computational Complexity of Algorithms
### Abstract Data Types
Here we present the most used data types that are used in many applications. Each data type provides a set of APIs to add,
look up or remove items. The APIs can be implemented with algorithms that may have different performances.

#### Stack
A stack is a data type that implements a Last-In-First-Out data access policy. It can be based on a linked list and must
implement two APIs: push() to insert a new element on the top of the stack, and pop() to remove one element from the top.

|API|Running time|
|---|------------|
|push()|constant|
|pop()|constant|

#### Queue
A queue is a data type that implements a First-In-First-Out data access policy. It can be based on a linked list and must
implement two APIs: enqueue() to add an element to the tail of the queue and dequeue() to remove the first element from the head of the queue.

|API|Running time|
|---|------------|
|enqueue()|constant|
|dequeue()|constant|

#### Priority Queue and heap-ordered binary tree
A priority queue is a data type that provides an API to insert an element, similarly to a queue or a stack, but instead of implementing a
method to remove the oldest element like in a queue, or the newest one like in a stack, a priority queue implements a method to remove the
element with the maximum value. The priority queue is based on a heap-ordered complete binary tree that can be implemented on top of an array.
A binary tree is heap-ordered when the key in each node is equal or larger than its children’s keys.

|API|Running time|
|---|------------|
|insert()|log(N)|
|delMax()|log(N)|

#### Symbol Table
Symbol table is an abstract data type that is used to store key-value pairs. Three types of symbol tables are presented in the course:
two that support ordered keys, the binary search tree and the red-black binary search tree, and one that uses a hash function to map
the key to an integer value that is used as an index. Hash tables do not support ordered operations.

#### Binary Search Tree
A binary search tree is an abstract data type whith an explicit tree data strucure in which each node has a natural ordered key
and an associated value. The key in a node is smaller than any key in its right subtree and larger than any key in its left subtree.
A Red-Black binary search tree is a nearly perfectly balanced symbol table with guaranteed log(N) time for insertion and search.
Guaranteed means the cost will not exceed the logarithmic time even in the worst case (e.g. when the input keys are ordered).  

|API|Running time|
|---|------------|
|put()|log(N)|
|get()|log(N)|

#### Hash Table
A hash table is a symbol table in which the keys are mapped to an integer value to be used as index in an array. The mapping from a key
(e.g. a string) and an integer is done with a function that must be provided with the data type. Since the size of the array that stores
the hash values is finite there might be collisions. One collision resolution strategy is called separate chaining. It consists of putting
the keys that have the same hash value into a list. This way, if N is the number of keys and M is the size of the array, the number of
compares for insert and search is N/M.

|API|Running time|
|---|------------|
|put()|constant|
|get()|constant|

### Sorting algorithms
The table presents the running time of the main sorting algorithms in the worst, average and best case. An algorithm that does not need any
additional space is said to be in-place. A sorting algorithm is said stable if it preserves the relative order of equal keys.  

|Algorithm|In-place|Stable| Worst case|Average case|Best case|Notes|
|---------|--------|------|-----------|------------|---------|-----|
|Selection sort|Yes|No|N^2 / 2|N^2 / 2|N^2 / 2|N exchanges|
|Insertion sort|Yes|Yes|N^2 / 2|N^2 / 4|N|Use for small N or partially ordered|
|Shellsort|Yes|No|N^(3/2)|Subquadratic|Subquadratic||
|Mergesort|No|Yes|N*log(N)|N*log(N)|N*log(N)|NlogN guarantee|
|Quicksort|Yes|No|N^2 / 2|2N*log(N)|N*log(N)|NlogN probabilistic guarantee, fastest in practice|
|Heapsort|Yes|No|N*log(N)|N*log(N)|N*log(N)|NlogN guarantee|

### Searching algorithms
A symbol table is an abstract data type that provides mechanisms to store information as a key-value pair so that once it is saved it can be
searched by its key. A symbol table with keys that have a natural order is called ordered symbol table and can support ordered operation such
as min(), max() and rank(). Symbol tables are used to build dictionaries, associative arrays, indeces, inverted indices, sparse vectors and
matrices.

|Algorithm|Search (worst case)|Insert (worst case) |Search (average case)|Insert (average case)|Notes|
|---------|-------------------|--------------------|---------------------|---------------------|-----|
|Sequential search|N|N|N / 2|N|Unordered list|
|Binary search|log(N)|N|log(N)|N / 2|Supports ordered operations|
|Binary search tree|N|N|log(N)|log(N)|Supports ordered operations|
|Red-Black BST|2*log(N)|2*log(N)|log(N)|log(N)|logN performance guaranteed for search insert and ordered operations|
|Hash table|< log(N)|< log(N)|N / (2M)|N / M|Separate chaining, N keys, M index size, no ordered operations|

### Graphs
A graph is an abstract data type consisting of a set of vertices and a collection of edges that are sets of pairs of vertices. This definition
allows self-loops, i.e. edges that connect a vertex to itself, and parallel edges, i.e. edges that connect the same pair of vertices. There are
two categories of graphs: undirected and directed. Graphs whose edges are associated to a value are called edge-weighted graphs and are used in
many applications such as finding the shortest path between two geographical locations.

#### Undirected Graphs
In undirected graphs two edges with the same vertices in different order are the same. An undirected graph can be efficiently built as an
array of adjacency lists, i.e. an array of linked lists where each index represents a vertex and the associated linked list contains its
adjacent vertices. When an edge v-w is added to an undirected graph to connect two vertices v and w, the vertex w must be added to the list
of adjacent vertices of v and the vertex v must be added to the list of adjacent vertices of w. A vertex is characterized by its degree that
represents the number of edges incident to it. Two main algorithms are used to process undirected graphs, depth-first search and breadth-first
search. These algorithms provide a base to easily implement higher level graph processing algorithms.

|Algorithm|Running time|Use|
|---------|------------|---|
|Depth-first search (DFS)|Proportional to the sum of the degrees of each vertex connected to the source vertex|recursive algorithm, cycle detection, determines if graph is bipartite|
|Breadth-first search (BFS)||Shortest path between two vertices|
|Single-source paths||Based on DFS, finds paths to all vertices connected to a source vertex|
|Connected components||Based on DFS|
|Degrees of separation||Based on BFS|

#### Edge-weighted Graphs
An edge-weighted graph can be represented as an array of adjacency lists of edges where each edge object contains two vertices and a weight.
Many important applications are based on finding the minimum spanning tree (MST) of an edge-weighted graph. A spanning tree is any subgraph
of a graph that is connected and has no cycles (i.e. it's a tree). A MST is a spanning tree that has minimum weight.  

|Algorithm|Running time|Extra space|Use|
|---------|------------|-----------|---|
|Prim|E*log(E) lazy implementation, E*log(V) eager implementation|E (lazy implementation), V (eager implementation)|Greedy algorithm, computes the minimum spanning tree (MST) of an edge-weighted graph|
|Kruskal|E*log(E)|E|greedy algorithm, computes the minimum spanning tree (MST) of an edge-weighted graph|

E represents the number of edges and V represents the number of vertices in a graph.

#### Directed Graphs
In directed graphs, two edges with the same vertices in different order are different. The order of vertices implies a one way adjacency.
Directed graphs, or digraphs, are used to model systems such as social networks, the WWW, chemical reactions, road networks and many others.
The first vertex in the pair that represents and edge is called its head, the second vertex is called its tail. A directed graph can be built as
an array of adjacency lists like an undirected graph. Adding an edge to connect two vertices in a directed graph is easier than in an undirected
one. When an edge v->w is added to a directed graph, only the tail vertex w must be added to the adjacency list of v.

|Algorithm|Running time|Extra space|Use|
|---------|------------|-----------|---|
|Depth-first search|Proportional to the sum of the degrees of the outgoing edges of each vertex reachable from the source vertex||Recursive algorithm, same implementation as for the undirected graphs, direct cycles detection|
|Breadth-first search|||Same implementation as for the undirected graphs, shortest path between two vertices in a directed path|
|Topological sort|E + V| |Returns the vertices of a directed acyclic graph in topological order, used in scheduling problems|
|Strong connectivity||||
|Transitive closure|||Computes all the directed edges between directed connected vertices |

A direct acyclic graph (DAG) is a digraph with no directed cycles. A DAG has a topological order.

#### Edge-weighted Directed Graphs
An edge-weighted directed graph can be represented as a vertex-indexed array of adjacency lists of directed edges where each directed edge object
contains an ordered pair of vertices, a source vertex and a destination vertex, and a weight. The main problem that we want to solve is finding the
shortest paths from one source vertex to any other vertex. It can be shown that the shortest paths from a source vertex to any other vertex is a
(spanning) tree and we can represent that tree with a vertex-indexed array of directed edges. Each index represents a vertex and the associated edge
contains the vertex itself and the edge's source vertex so that we can go back from any vertex in the tree to the source. An additional vertex-indexed
array contains the distance from a vertex in tree to the source. Different algorithms use these data structures to compute the single-source shortest
paths.   

|Algorithm|Running time|Extra space|Use|
|---------|------------|-----------|---|
|Dijkstra's single-source shortest paths|E*log(V)|V|Finds the shortest directed paths (shortest paths tree) from a source vertex to any other vertex (nonnegative weights)|
|Edge-weighted DAG single-source shortest paths| E + V||Similar to Dijkstra's algorithm but based on the DAG's topological order, also with negative weights|
|Bellman-Ford single-source shortest paths|EV|V|Shortest paths for directed edge-weighted graphs with no negative cycles reachable from the source vertex, negative cycles detection|
|Ford-Fulkerson (max-flow/min-cut)|V*E^2||Finds the maximum flow that can be established between a source vertex and a sink vertex, given the capacities (weights) of the edges|

Many problems can be represented as edge-weighted DAG and addressed like a shortest-paths problem, one of the most relevant being parallel job
scheduling where each job has a completion time (its weight) and precedence constraints. The Bellman-Ford algorithm allows us to address the
shortest-paths problem even on egdge-weighted directed graphs that have negative weights and cycles if there are no negative cycles, i.e. cycles
whose total weight is negative.

### Strings
Sequences of characters from an alphabet are used not only in all types of communications but also in scientific fields such as Genomics where the
structure of the genes and proteins can be represented by sequences of characters from an alphabet of 4 or 20 characters respectively. When dealing
with strings we are interested in algorithms to sort collections of strings, or to search for a particular string in a text, or to search for a set of
strings that follow a pattern in a text. Finally we may be interested in reducing the size of a text for storage or before sending it through a network.    

|Algorithm|Running time|Extra space|Use|
|---------|------------|-----------|---|
|Key-indexed counting|8*N + R + 1|N + R|Sorts stably records using small integers as keys. If the records are sorted, e.g. by name, the sort by key-indexed counting keeps the names in relative order|
|Least-significant-digit-first (LSD string sort)|WN (W string length)|N + R (R, or radix, is the size of the characters set)|Sorts stably fixed-length strings. Based on key-indexed counting to sort the strings by character from right to left|
|Most-significant-digit-first (MSD string sort)|8N + 3R (R radix, size of ther characters set)|N + DR (radix R, size of the characters set, D length of the longest string)|Sorts strings of different length. It is a recursive algorithm based on key-indexed counting to sort the strings by character from left to right |
|3-way string quicksort|WNlog(N) (W string length |log(N) + W|It is a recursive, not stable, string sort algorithm. It can be used to sort strings of different length|
|Suffix array|Nlog(N)|N|It is a data structure that can be used to index a string to be able to quickly search for a substring in it, and to find the longest repeated substring|
|R-way Tries|search hit: W (key length), search miss: sublinear (less than the length of the key)|RNw (R size of the characters set, W average key length)|It is a symbol table with a tree data structure built from the characters of the string keys. Each node may have as many children as the size R of the characters set used. It supports ordered symbol table operations and character-based operations such as prefix and wildcard match.|
|Ternary search tries (TST)|log(N)|log(N)|It is a symbol table with a binary tree data structure built from the characters in the string keys. Each node has a character, a value and links to three children: left for character's key less than the parent's, right for character's key greater than the parent's and center for a match. It supports ordered symbol table operations and character-based operations such as prefix and wildcard match.|
|Knuth-Morris-Pratt (KMP) substring search|N + M (N length of text, M length of pattern)||It is used to search for a pattern string in a text. The core of the algorithm is a deterministic finite-state automaton (DFA) that is built from the pattern string and prescribes the transition from one state to another for any character that is read from the text. The KMP algorithm does not need to back up, that is to go back in the text after a mismatch, for this reason it is mostly used to search for a pattern in a stream.|
|Boyer-Moore substring substring search|N / M (sublinear, M is the length of the pattern)||This algorithm compares the pattern with the text from right to left. The algorithm builds an array of the rightmost occurrence of each character in the pattern to drive the search in the text. Contrary to the KMP algorithm, the full must be available at once for the search.|
|Rabin-Karp (fingerprint) substring search|7N||The algorithm compares the hash value of a pattern of length M with the hash values of all the substrings of the same length in the text. The hash function is built using the Horner's method. Being based on a hash function the algorithm belongs to the randomized, Monte Carlo algorithms class. |
|Regular Expression (RE) for pattern matching|NM (M length of the RE)||REs are used in many applications such as checking the validity of input strings or whether a certain pattern occurs in a genome sequence. A RE is generalization of the finite-state machine used in the KMP algorithm to drive the search for a substring in a text. The non-deterministic finite-state automaton (NFA) that implements a RE defines the set of strings that are searched for in the text. The difference between a DFA and a NFA is that a state in a NFA can have more than one possible transitions to other states for the same character read from the text, so the choice is non-deteministic and all the possible transitions must be evaluated. The NFA uses a digraph to represent the transitions allowed by the operators used in the RE.|

## Data Structures, Data Types, Java interfaces and implementations
Units of data hat belong to a collection are physically stored in the computer memory in two ways: contiguous memory locations or 
non-contiguous memory locations. 

### Data Structure 
It is way to organize the data units in the computer's memory. There are two main kind of data structures: contiguous memory locations and linked memory locations where each data element has a reference to the next element in the collection. An array is a data structure of the 1st type. A linked list, a tree or a graph are data structures of the 2nd type. The main difference between the two types that is a consequence of the way in which the elements of the structure are stored in memory is that an array must be declared with its number of elements to allow the computer to allocate a contiguous amount of space in memory. This problem can be addressed by resizing 
the array when the number of its elements are close to the size of the array.

### Data Type 
We consider data types that represent collections of objects and provide a set of operations on those objects to add or remove items,
to traverse the collection, and algorithms for sorting or searching. A data type is based on a data structure and implements some 
operations that allows easy access to the elements of the data according to a pattern. Java, as other programming languages, provides basic data types for collections. Since the same data type can be implemented in Java using different data structures and operations implemented using different algorithms, for example for sorting, Java offers an interface for each collection data type and one or more concrete implementations. The most common Java collection data types are: stack, queue, priority queue, set, symbol tables.

### Java interfaces and implementations
 - array: int [] students = new int [N]
 - list: List&lt;String> cities = new ArrayList&lt;String>(); // or = new LinkedList&lt;String&gt;();
 - stack: Deque&lt;Integer&gt; stack = new ArrayDeque&lt;Integer&gt;();
 - queue, priority queue: PriorityQueue&lt;Student&gt; students = new PriorityQueue&lt;Student&gt;();
 - symbol table: TreeMap&lt;String, Integer&gt; populations = new TreeMap&lt;String, Integer&gt;();  
   or HashMap&lt;String, Integer&gt; populations = new HashMap&lt;String, Integer&gt;();                      

