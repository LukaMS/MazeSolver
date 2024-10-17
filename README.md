# Assignment A3 - Maze Runner Take Two

  * **Student**: [Luka Mahrt-Smith](mahrtsml@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
    - For this assignment, the path does not have to be the shortest one.
- The program can take a path as input and verify if it's a legit one.
- The program can take different maze solcing methods as input, including graphing methods.
- The program has a benchmarking feature to compare exploration time of varying methods 

## How to run this software?

To build the program, simply package it with Maven:

```
mosser@azrael A1-Template % mvn -q clean package 
```

### Provided version

When called on a non-existing file. it prints an error message

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txtd
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txtd
/!\ An error has occured /!\
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

### Delivered version

#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-m {BFS, righthand}`: specifies which path computing algorithm to use, deafult to righthand.
- `-baseline BASELINE`: activates the programs benchmarking capabilities to compare the generated solutions from an input method and baseline method.

#### Examples

If a given path is correct, the program prints the message `Correct path` on the standard output.

```
$ java -jar target/mazerunner.jar -i ./examples/small.maz.txt -p "F L F R 2F L 6F R 4F R 2F L 2F R 2F L F"
[INFO ] Main ** Starting Maze Runner
[INFO ] Main **** Reading the maze from file ./examples/small.maz.txt
[INFO ] Main **** Checking path
*** Correct Path
[INFO ] Main ** End of MazeRunner

```

If a given path is incorrect, the program prints the message `Incorrect path` on the standard output.

```
$ java -jar target/mazerunner.jar -i ./examples/small.maz.txt -p "F L F R 2F L 6F R 4F"
[INFO ] Main ** Starting Maze Runner
[INFO ] Main **** Reading the maze from file ./examples/small.maz.txt
[INFO ] Main **** Checking path
*** Incorrect Path
[INFO ] Main ** End of MazeRunner
```

If no input path provided the program prints out the computed path using righthand method.

```
$ java -jar target/mazerunner.jar -i ./examples/small.maz.txt
[INFO ] Main ** Starting Maze Runner
[INFO ] Main **** Reading the maze from file ./examples/small.maz.txt
[INFO ] Main *** Right Hand Method Selected
[INFO ] Main *** Computing Path
*** Computed Path F R F 2R 2F R 2F R 2F 2R 4F R 2F R 4F 2R 2F R 4F R 2F R 2F 2R 2F L 2F L 4F R 2F R 2F 2R 4F R 2F R 2F 2R 2F R 2F R 4F R 2F L 2F R 2F L F
[INFO ] Main ** End of MazeRunner
```

If a solving method is inputed, the solution generated using that method is printed.

```
$ java -jar target/mazerunner.jar -i ./examples/small.maz.txt -m BFS
[INFO ] Main ** Starting Maze Runner
[INFO ] Main **** Reading the maze from file ./examples/small.maz.txt
[INFO ] Main *** BFS Method Selected
[INFO ] Main *** Computing Path
*** Computed Path F L F R 2F L 6F R 4F R 2F L 2F R 2F L F
[INFO ] Main ** End of MazeRunner
```

If a baseline method is inputed, the program compares the speeds of the baseline method with the input method.
If no input method provided than righthand will be used, the comparison is printed out.

```
$ java -jar target/mazerunner.jar -i ./examples/small.maz.txt -m BFS -baseline righthand
[INFO ] Main ** Starting Maze Runner
[INFO ] Main **** Reading the maze from file ./examples/small.maz.txt
[INFO ] Main *** Bench Marking
Time to load maze: 1.18 milliseconds
Time to solve maze using BFS: 18.75 milliseconds
Time to solve maze using baseline righthand: 1.54 milliseconds
Path length improvement: BFS (29 steps) is 3.28 times faster than righthand (95 steps).
[INFO ] Main ** End of MazeRunner
```

