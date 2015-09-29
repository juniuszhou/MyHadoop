package HTableUsage

import com.gravity.hbase.schema.{DeserializedResult, HRow, HbaseTable}
import org.joda.time.{DateMidnight, DateTime}

/**
 * https://github.com/GravityLabs/HPaste
 */

/*
class WebPageRow(table: WebTable, result: DeserializedResult) extends HRow[WebTable, String](result, table)
class WebTable extends HbaseTable[WebTable, String, WebPageRow](tableName = "pages", rowKeyClass = classOf[String]){
  def rowBuilder(result: DeserializedResult) = new WebPageRow(this, result)

  val meta = family[String, String, Any]("meta")
  val title = column(meta, "title", classOf[String])
  val lastCrawled = column(meta, "lastCrawled", classOf[DateTime])

  val content = family[String, String, Any]("text", compressed = true)
  val article = column(content, "article", classOf[String])
  //val attributes = column(content, "attrs", classOf[Map[String, String]])

  //val searchMetrics = family[String, DateMidnight, Long]("searchesByDay")

}*/
object ScalaAccessHPaste {
  def main (args: Array[String]) {

  }
}
