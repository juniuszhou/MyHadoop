package ZooKeeperAccess

import org.apache.zookeeper.ZooKeeper

/**
 * Created by junius on 9/27/15.
 */
object JavaZkClient {
  def main (args: Array[String]) {
    //val zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null)
    val zooKeeper = new ZooKeeper("zk1.us-east-1.prod.expedia.com:2181/kafka/ewep", 45000, null)
    val znode = zooKeeper.getChildren("/kafka/ewep", null)

    val data = znode.get(0)
    println(data.toString)

  }
}
