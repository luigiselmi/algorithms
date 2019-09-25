Algorithms
==========

This repository contains example Java code based on the online course Algorithms, [Part 1](https://www.coursera.org/learn/algorithms-part1) and [Part 2](https://www.coursera.org/learn/algorithms-part2), taught by Kevin Wayne and Robert Sedgewick and offered by Princeton University on Coursera. The online lessons are based on the book Algorithms, 4th Edition, by the same authors. Additional material is also available from the [book's website](https://algs4.cs.princeton.edu/home/). This repository contains also my submissions to the programming assignments. This is a work in progress, see the assessments to know whether the code passes all the tests for correctness, memory, timing and other metrics. Maven is used to compile and execute the code.

## Part I
Part I covers elementary data structures, sorting, and searching algorithms. You can compile all the Java classes using Maven

```
$ mvn compile
```

### Week 1
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php) is in the percolation folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.percolation.PercolationVisualizer" -Dexec.args="resources/percolation/input20.txt"
```

### Week 2 
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php) is in the queues folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.queues.Permutation" -Dexec.args="8" < resources/queues/distinct.txt
```

### Week 3
The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php) is in the collinear folder. You can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.collinear.FastCollinearPoints" -Dexec.args="resources/collinear/input8.txt" 
```

### Week 4
The main topics are priority queues and the heap sort algorithm. A Priority queue is a data structure  that provides two
main APIs:

* insert
* remove the maximum

It can be implemented using a stack or a queue but it's more convenient to use a heap- ordered binary tree on top of an array. The heapsort algorithm is an optimal sorting algorithm that can be divided in two phases. In the 1st phase the algorithm builds an heap-ordered binary tree with the maximum value at its root. In the 2nd phase, the root element is repeatedly exchanged with the last one and removed in order to produce a list of ordered elements. A priority queue can be 
used to implement the A* algorithm and solve the 8-puzzle and the 15-puzzle finding the least set of tile moves to reach 
the goal with all the tiles ordered. The Java code for the [programming assignment](https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php) and solver of the puzzles is in the puzzle folder. As usual you can execute the code using Maven

```
$ mvn exec:java -Dexec.mainClass="assignments.puzzle.Solver" -Dexec.args="resources/puzzle/puzzle04.txt"
```      

## Part II
Part II focuses on graph- and string-processing algorithms.