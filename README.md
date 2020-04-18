# Buffons-Needle
An Operating Systems project.

# Authors
Kevin Filanowski
Jacob Ginn

# Version
04/317/20 - 1.0.0

# Table of Contents
* [Description](#description)
* [Contents](#contents)
* [Compiling](#compiling)
* [Usage](#usage)

# Description
A multithreaded application with message passing to perform Buffon's Needle Experiment.
This is a simple program that estimates the value of PI by doing Buffon's Needle experiment.
This is an experiment in which there are two 'lines' spaced some provided distance apart, and 
'needles' are dropped at some random angle and position on the 'paper'. If the needles touch the lines,
we consider this a hit, and if the needles do not touch the lines, it is considered a miss.
These results are then collected and passed into a formula to estimate the value of PI.

This project asks the user for:
1. The distance between the lines,
2. The length of each needle,
3. The amount of needles to drop overall,
4. and the number of threads to create to performs these experiments.

The specified number of threads will be created and the threads will perform the needle drop experiments,
with relatively equal work split amongst the threads. When the thread finishes, a message is sent back to a class to collect
the results of a thread immediately to calculate a result using a technique called **Message Passing**.

The program must ensure that there is no busy waiting and no concurrency errors (such as reading and writing to the same data).


# Contents
- instructions.pdf : The PDF instructions for the project.

- src : The folder containing the source files of the program.

   1. Buffon.java : The *consumer* class that starts the threads for the experiments, collects the results, and estimates PI.
   
   2. Experiment.java : The runnable class that performs the experiment. Each thread created is running this class.
   
   3. Channel.java : A generic interface defining the message passer.
   
   4. MessageQueue.java : The message passer. This class allows for communication between the Buffon and Experiment classes.
   
   
# Compiling
To compile the program, ensure that the files described in 'CONTENTS',
specifically in the src folder, are all in the same directory.
Then run the following command to compile all java files in your current
directory:

`javac *.java`

There should be no errors or warnings.

# Usage
`java Buffon`

or

`./java Buffon`
