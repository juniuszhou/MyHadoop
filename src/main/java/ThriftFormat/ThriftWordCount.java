package ThriftFormat;

import MapReduce2.Calculator;
import MapReduce2.NewWordCount;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;


public class ThriftWordCount {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Text> {


        private Text word = new Text();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);

        TTransport transport = new TIOStreamTransport(outStream);

        TBinaryProtocol binaryOut = new TBinaryProtocol(transport);

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            try {
                Calculator c = new Calculator();
                c.whatOp = 1;
                c.why = value.toString();
                System.out.println("Junius " + value.toString());

                c.write(binaryOut);

                word = new Text(c.why);
                //value.getBytes();

                context.write(word, new Text(outStream.toByteArray()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // put output in the same directory and several partition files.
    public static class IntSumReducer
            extends Reducer<Text,Text,Text,Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {
            try {
                String sum = "";
                for (Text val : values) {
                    sum += val.toString();
                }
                result.set(sum);
                context.write(key, result); }
            catch (Exception e) {
                //context.
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("new version 2");
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);


        job.setJarByClass(ThriftWordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(1);

        String input = args[0];
        String output = args[1];


        FileInputFormat.addInputPath(job, new Path(input));

        FileOutputFormat.setOutputPath(job, new Path(output));

        job.setOutputFormatClass(FileOutputFormat.class);

        System.out.println(job.getOutputFormatClass().toString());


        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}


