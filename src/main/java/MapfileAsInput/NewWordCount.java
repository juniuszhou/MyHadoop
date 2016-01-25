package MapfileAsInput;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class NewWordCount {

    public static class TokenizerMapper extends Mapper<Text, LongWritable, Text, LongWritable>{

        private LongWritable one = new LongWritable(1);
        private Text word = new Text();

        public void map(Text key, LongWritable value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(key.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                //one = new LongWritable()
                context.write(word, value);
            }
        }
    }

    // put output in the same directory and several partition files.
    public static class IntSumReducer
            extends Reducer<Text,LongWritable,Text,LongWritable> {
        private LongWritable result = new LongWritable();

        public void reduce(Text key, Iterable<LongWritable> values,
                           Context context
        ) throws IOException, InterruptedException {

            System.out.println("junius into reducer");
            try {
            int sum = 0;
            for (LongWritable val : values) {
                System.out.println("key is " + key + " " + val.get());
                sum += val.get();
            }
            result.set(sum);

            context.write(key, result); }
            catch (Exception e) {
                //context.
                e.printStackTrace();
            }
            System.out.println("junius outside reducer");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("new version 2");
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        // set classed defined in this app.
        job.setJarByClass(NewWordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);


        //set output key value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //job.setMapOutputKeyClass(Text.class);
        //job.setMapOutputValueClass(LongWritable.class);

        // set reduce as 1. no method to set map number now.
        job.setNumReduceTasks(1);


        //set input output path.
        String input = args[0];
        String output = args[1];

        //String input = "file:///home/junius/data/input";
        //String output = "file:///home/junius/data/output";

        SequenceFileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        // set out put file format.
        job.setInputFormatClass(SequenceFileInputFormat.class);
        //job.setOutputFormatClass(MapFileOutputFormat.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}


