# String Pair Writables - Hadoop MapReduce Job
---
### Overview
This WritableComparable Job for String Pairs reads a text document of names, pairing first and last names, and counts occurrences of each pair. The Mapper splits each line into first and last names, creating a custom StringPairWritable object for each pair. The custom writable object supports serialization, deserialization, and comparison. The Reducer uses these objects to count how many times each name pair appears in the dataset. For instance, it might find that the name pair "Smith, John" appears 20 times in the document. The Custom WritableComparable job is useful for any application that needs to handle complex data types in a MapReduce framework, such as combining and comparing multiple fields. This can be applied in fields like data warehousing, e-commerce, and social media analysis. 

### Driver (StringPairTestDriver.java):
The driver, `StringPairTestDriver`, sets up the job by linking the Mapper and a built-in `LongSumReducer`, configuring input/output paths, and verifying dataset arguments. It sets output key and value classes based on Mapper and Reducer outputs. This team, we are utilizing the Reducer through a Driver and allowing string writable objects and the operations performance separately.
 
### Mapper (StringPairMapper.java):
The Mapper reads the dataset, splits each line into first and last names, and creates a `StringPairWritable` object for each pair. It pairs the names and assigns a LongWritable value of 1 to each pair for counting.

### Generated Writeable (StringPairWritable.java):
This custom type handles pairs of strings, supporting methods for serialization, deserialization, comparison, and formatting. It includes constructors, write/read methods, compareTo, toString, equals, and hashcode methods for managing string pairs.

