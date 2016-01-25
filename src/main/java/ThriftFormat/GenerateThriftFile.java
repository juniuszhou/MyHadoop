package ThriftFormat;

import MapReduce2.Calculator;
import ThriftFormat.ThriftReader;
import ThriftFormat.ThriftWriter;
import org.apache.thrift.TBase;

import java.io.File;

/**
 * Created by junius on 1/5/16.
 */
public class GenerateThriftFile {

    public static void write() throws Exception {
        String path = "/home/junius/data/thrift/obj.th";
        File f = new File(path);
        ThriftWriter tw = new ThriftWriter(f);
        tw.open();

        TBase t = new Calculator(1, "1");
        tw.write(t);

        t = new Calculator(2, "2");
        tw.write(t);

        tw.close();
    }

    public static class CreateCalculator implements ThriftReader.TBaseCreator {
        public TBase create() {return new Calculator(0, "0"); }
    }
    public static void read() throws Exception {
        String path = "/home/junius/data/thrift/obj.th";
        File f = new File(path);
        ThriftReader tw = new ThriftReader(f, new CreateCalculator());
        tw.open();

        while (tw.hasNext()){
            Calculator c = (Calculator) tw.read();
            System.out.println("" + c.getWhatOp() + " " + c.getWhy());
        }

        tw.close();
    }

    public static void main(String[] args) throws Exception {
        read();
    }
}
