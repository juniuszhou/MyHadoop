package HTableUsage

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.TableName

import org.apache.hadoop.conf.Configuration


object CreateTable {
  def main (args: Array[String]) {
    // Instantiating configuration class
    val con = HBaseConfiguration.create()
    con.set("hbase.master", "127.0.0.1:60000")

    // Instantiating HbaseAdmin class
    val connection = ConnectionFactory.createConnection(con)
    val admin = connection.getAdmin
    println("get admin already.")

    // Instantiating table descriptor class
    val tableDescriptor = new HTableDescriptor(TableName.valueOf("emp"))

    // Adding column families to table descriptor
    tableDescriptor.addFamily(new HColumnDescriptor("columnFamilyOne"))
    tableDescriptor.addFamily(new HColumnDescriptor("columnFamilyTwo"))

    // Execute the table through admin
    admin.createTable(tableDescriptor)
    System.out.println(" Table created ")
  }
}
