package DebugMR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.StringTokenizer;

/*
    https://dzone.com/articles/running-elastic-mapreduce-job
*/


public class OrderAggregation extends Configured implements Tool {

    public static class WordCountMap extends
            Mapper<LongWritable, Text, Text, LongWritable> {
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String str = value.toString();
            String subStr = str.substring(0, 20);
            Text output = new Text(subStr);
            System.out.println(" input :"+ subStr + " key " + key);
            context.write(output, key);
        }
    }

    public static class WordCountReduce extends
            Reducer<Text, LongWritable, Text, LongWritable> {

        public void reduce(Text key, Iterable<LongWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (LongWritable val : values) {
                sum += val.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }

    @Override
    public int run(String[] var1) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(OrderAggregation.class);
        job.setJobName("OrderAggregation");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(var1[0]));
        FileOutputFormat.setOutputPath(job, new Path(var1[1]));

        return(job.waitForCompletion(true)?0:-1);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new OrderAggregation(), args);
        System.exit(exitCode);
    }
}
