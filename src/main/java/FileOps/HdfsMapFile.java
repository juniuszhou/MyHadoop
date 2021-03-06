package FileOps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;


public class HdfsMapFile {
    public static void write() throws Exception {
        Configuration conf=new Configuration();
        FileSystem fs= FileSystem.get(conf);

        Path mapFile=new Path("hdfs://127.0.0.1:9000/jun/mapFile");

        MapFile.Writer.Option keyClass =
                (MapFile.Writer.Option)MapFile.Writer.keyClass(IntWritable.class);
        SequenceFile.Writer.Option valueClass = MapFile.Writer.valueClass(Text.class);

        MapFile.Writer writer=new MapFile.Writer(conf,mapFile,keyClass,valueClass);


        writer.append(new IntWritable(1),new Text("value1"));
        writer.append(new IntWritable(2),new Text("value2"));
        writer.append(new IntWritable(3),new Text("value3"));
        writer.append(new IntWritable(4),new Text("value4"));
        writer.append(new IntWritable(5),new Text("value5"));
        writer.append(new IntWritable(6),new Text("value6"));

        IOUtils.closeStream(writer);//关闭write流
    }

    public static void read() throws Exception {
        Configuration conf=new Configuration();
        Path mapFile=new Path("hdfs://127.0.0.1:9000/jun/mapFile");

        IntWritable key=new IntWritable();
        Text value=new Text();
        MapFile.Reader reader=new MapFile.Reader(mapFile, conf);
        while(reader.next(key,value)){
            System.out.println(key + "  " + value);
        }
        IOUtils.closeStream(reader);
    }

    public static void seek() throws Exception {
        Configuration conf=new Configuration();
        FileSystem fs= FileSystem.get(conf);
        Path mapFile=new Path("hdfs://127.0.0.1:9000/jun/mapFile");
        IntWritable key=new IntWritable(2);
        Text value=new Text();
        MapFile.Reader reader=new MapFile.Reader(mapFile, conf);
        // seek to key.
        reader.seek(key);
        reader.get(key, value);
        //reader.next(key,value);
        System.out.println(key + "  " + value);

        IOUtils.closeStream(reader);
    }

    public static void main(String[] args) throws Exception {
        //write();
        read();
    }
}
