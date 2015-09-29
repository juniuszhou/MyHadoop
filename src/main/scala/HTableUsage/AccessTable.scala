package HTableUsage

import java.util

import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HColumnDescriptor, TableName, HBaseConfiguration}
import org.apache.hadoop.hbase.client._

object AccessTable {

  def main (args: Array[String]) {
    val con = HBaseConfiguration.create()
    con.set("hbase.master", "127.0.0.1:60000")
    val connection = ConnectionFactory.createConnection(con)

    def addColumnFamily(tableName: String, familyName: String) = {
      val table = connection.getTable(TableName.valueOf(tableName))
      val desc = table.getTableDescriptor
      desc.addFamily(new HColumnDescriptor(familyName))
    }
    def addRecord(tableName: String, row: String, familyName: String, col: String, value: String ) = {
      val table = connection.getTable(TableName.valueOf(tableName))
      val put = new Put(Bytes.toBytes(row))
      put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(col), Bytes.toBytes(value))
      table.put(put)
    }

    def deleteRecord(tableName: String, row: String): Unit ={
      val table = connection.getTable(TableName.valueOf(tableName))
      val list = new util.ArrayList[Delete]()
      val del = new Delete(Bytes.toBytes(row))
      list.add(del)
      table.delete(list)
    }


  }
}
