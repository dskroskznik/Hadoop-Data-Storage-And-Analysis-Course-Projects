# Average Word Lengths - Hadoop MapReduce Job
---
## Overview
Average Word Length MapReduce Job is important for text analysis applications, such as natural language processing, document summarization, and readability assessment. It helps identify patterns in word usage across large datasets, which can be useful for search engines, academic research, and content creation tools. This MapReduce Job processes large text files to compute the average length of words that start with each letter. It works by mapping each word to its first letter and length, then reducing these mappings to calculate the average length for each letter. For example, it might determine that words starting with 'A' have an average length of 5 characters.

### Driver (AvgWordLength.java) :
The driver sets up the MapReduce job, linking the Mapper and Reducer, and defines input and output paths via command line arguments. It specifies `Text.class` for the key and `IntWritable.class` for the value in the Mapper, and `DoubleWritable.class` for the value in the Reducer to calculate average word lengths. 

### Mapper (LetterMapper.java) :
The Mapper processes input text, ignoring the key, and outputs the first letter of each word as the key with the word's length as the value. It filters alphabetic characters and maps words longer than one character.

### Reducer (AverageReducer.java) :
The Reducer takes keys from the Mapper and their associated word lengths. It calculates the average word length for each key and outputs the key with the average as a `DoubleWritable`.

### Dataflow:
The job maps words by their initial character, sums their lengths, and counts occurrences in the Mapper. The Reducer calculates and outputs the average length for each initial character. This process efficiently handles large text files, organizing word lengths by their starting letters.
