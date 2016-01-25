package OwnInputFormatMapReduce;

import MapReduce2.Calculator;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


import java.io.IOException;
import java.io.InputStream;


public class MemberJoinThriftRecordReader extends RecordReader<LongWritable, Text> {
    private ThriftStreamReader reader = null;
    private LongWritable key;
    private Text value;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        //FileSplit fileSplit = (FileSplit) split;
        Path filePath = ((FileSplit) split).getPath();
        System.out.println("junius open member join file "+ filePath.toString());
        FileSystem fs = filePath.getFileSystem(context.getConfiguration());
        InputStream fileIn = fs.open(filePath);

        reader = new ThriftStreamReader(fileIn, new CreateMemberJoin());
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (reader.hasNext()) {
            try {
                Calculator memberJoin = (Calculator) reader.read();
                key = new LongWritable(memberJoin.whatOp);
                value = new Text(DataTransform.MemberJoinToBytes(memberJoin));
                System.out.println("read member join key is " + key);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0.0F;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

