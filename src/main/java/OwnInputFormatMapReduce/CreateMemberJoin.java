package OwnInputFormatMapReduce;

import MapReduce2.Calculator;
import org.apache.thrift.TBase;

public class CreateMemberJoin implements
        OwnInputFormatMapReduce.ThriftStreamReader.TBaseCreator {
    public TBase create() {return new Calculator(0, "0"); }
}
