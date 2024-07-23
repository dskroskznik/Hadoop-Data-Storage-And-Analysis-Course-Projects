package stubs;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class AvgWordLength {

  public static void main(String[] args) throws Exception {

    /*
     * Validate that two arguments were passed from the command line.
     */
    if (args.length != 2) {
      System.out.printf("Usage: AvgWordLength <input dir> <output dir>\n");
      System.exit(-1);
    }

    /*
     * Instantiate a Job object for your job's configuration. 
     */
    Job job = new Job();
    
    /*
     * Specify the jar file that contains your driver, mapper, and reducer.
     * Hadoop will transfer this jar file to nodes in your cluster running 
     * mapper and reducer tasks.
     */
    job.setJarByClass(AvgWordLength.class);
    
    /*
     * Specify an easily-decipherable name for the job.
     * This job name will appear in reports and logs.
     */
    job.setJobName("Average Word Length");
    
    /* Driver Process by Dylan Skroskznik 				*/
    /* Create and route input, read and output, written paths
     * from using a command line argument path.
     */
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    /* Job object extensions to set Reducer and Mapper classes.
     */
    job.setMapperClass(LetterMapper.class);
    job.setReducerClass(AverageReducer.class);
    
    /* Use the 2 set map output formats to key & value
     * for mapper and reducer to match outputs.
     */
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    
    /* Use the 2 same output methods but different for
     * setting an output for key & value class files.
     */
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(DoubleWritable.class);
    
    /* Start the MapReduce job and wait for it to finish.
     * If it finishes successfully, return 0. If not, return 1.
     */
    boolean success = job.waitForCompletion(true);
    System.exit(success ? 0 : 1);
  }
}

