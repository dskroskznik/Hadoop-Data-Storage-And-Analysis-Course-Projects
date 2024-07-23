package stubs;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

    /* Reducer Process by Dylan Skroskznik     */
	  long wordSum = 0;
	  long wordCount = 0;
	  
	/* word counter for loop to each key		*/  
	  for (IntWritable wordValues : values) {
		  wordSum += wordValues.get();
		  wordCount++;
		  
	  }
	/* if statement calculates word length avg	*/
	  if (wordCount != 0) {
		  double avg = (double)wordSum/(double)wordCount; 
		  
		/* use context object to write
		 * new avg to produce key value 		*/  
		  context.write(key, new DoubleWritable(avg));
		  
	  }
	  
  }
}