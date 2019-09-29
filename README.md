Algorithms
==========

This repository contains example Java code based on the online course Algorithms, [Part 1](https://www.coursera.org/learn/algorithms-part1) and [Part 2](https://www.coursera.org/learn/algorithms-part2), taught by Kevin Wayne and Robert Sedgewick and offered by Princeton University on Coursera. The online lessons are based on the book Algorithms, 4th Edition, by the same authors. Additional material is also available from the [book's website](https://algs4.cs.princeton.edu/home/). This repository contains also my submissions to the programming assignments. This is a work in progress, see the assessments to know whether the code passes all the tests for correctness, memory, timing and other metrics. Maven is used to compile and execute the code.

## Compile and execute
You can compile and execute the code using Java or Maven.

### Compile and execute with Java
In order to compile the java source files you have first to create a folder that will contain all the compiled classes. You 
can organize the folder as with Maven, with a *target* folder and a *classes* sub-folder. From the project root folder execute

```
$ mkdir -p target/classes 
```
In order to compile the Java code you need the [algs4.jar](https://algs4.cs.princeton.edu/code/) Java library to be added to the classpath. In the example the library is in the *lib* folder. Since the Java compiler cannot find recursively all the Java files by itself, you need to add a bash *find* command

```
$ javac -cp "lib/algs4.jar" -d target/classes $(find src/main/java -name '*.java')
```

In order to execute a Java class, from the project root folder, you need to add the compiled classes and the algs4.jar library to the classpath. As an example the command 

```
$ java -cp "lib/algs4.jar;target/classes" searching.WordCounter < resources/searching/tinyTale.txt
```

executes the *WordCounter* Java class, in the *searching* package, that takes in input a stream from a file (not the file name !) containing text and writes, in the standard output, the number of distinct words and the complete words list. 

It can be useful to execute some of the classes that are provided in the algs4.jar library. As an example

```
$ java -cp "lib/algs4.jar" edu.princeton.cs.algs4.FrequencyCounter 8 < resources/searching/tinyTale.txt
```

executes the Java class FrequencyCounter, in the edu.princeton.cs.algs4 package in the algs4.jar library that takes as an argument the minimun length of a word to be counted and a list of words from the input stream fed by a text file.    
### Compile and execute using Maven
You can compile all the Java classes using Maven

```
$ mvn compile
```

When the command returns, a new *target* folder is created, as Maven's convention, with a sub-folder *classes* containing all the compiled java classes. Examples of how to execute a Java class using Maven are provided in the following sections. 

## Part I
Part I covers elementary data structures, sorting, and searching algorithms. 

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