package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /* Mapping Process by Dylan Skroskznik
     * Works to loop through each line and split values by words
     */
	  
	  String textLine = value.toString();
	/* Takes line of text (string line) and maps words out of each line
	 * loops over each words
	 */ 
	  for (String words : textLine.split("\\W+")) {
	  	if(words.length() > 0) { 
	  		String letters = words.substring(0,1); 
	  		
	  		/* use context object to write
			 * new avg to produce key value 		*/  
	  		context.write(new Text(letters), new IntWritable(words.length()));
	  	}
	  }
  }
}
