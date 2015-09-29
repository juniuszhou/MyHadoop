package ZooKeeperAccess

import com.twitter.util.Future
import com.twitter.zk.{ZkClient, Connector}
import org.apache.zookeeper.ZooKeeper

/**
 * Created by junius on 9/28/15.
 */

class TestZkClient(zkInstance: String) extends ZkClient {
  val connector = new Connector {
    def apply(): Future[ZooKeeper] = Future {
      new ZooKeeper(zkInstance, 45000, null)
    }
    def release() = Future.Done
  }
}

object ScalaOwnClient {
  def main (args: Array[String]) {

    val zClient = new TestZkClient("zk1.us-east-1.test.expedia.com:2181/kafka/ewep")
    val zooKeeper = zClient.connector()

    zooKeeper.onSuccess( zk => {
      val zNode = zk.getChildren("/", false)
      zNode.toArray.foreach(println)
    })

  }
}
