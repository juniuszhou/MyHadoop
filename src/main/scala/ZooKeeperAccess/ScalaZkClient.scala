package ZooKeeperAccess

import com.twitter.util.{Future, Await, Timer, Duration}
import com.twitter.zk._

import org.apache.zookeeper.data._

/**
 * libraryDependencies += "com.twitter" % "util-zk_2.10" % "6.27.0"

 * the correct for it is just designed for async usage.
 */
object ScalaZkClient {
  def main (args: Array[String]) {
    implicit val timer = Timer.Nil
    //val zkClient = ZkClient("zk1.us-east-1.test.expedia.com:2181",
    //val zkClient = ZkClient("zk1.us-east-1.prod.expedia.com:2181",
    val zkClient = ZkClient("127.0.0.1:2181",
      Some(Duration.fromSeconds(30)), Duration.fromSeconds(30)).withRetries(3)
    //val znode = zkClient("/zookeeper")
    val znode = zkClient("/")
    val data = znode.getData()
    data.onSuccess(_ => println("with data."))

    //val zk = Await.result(zkClient, Duration.fromSeconds(20))


    //println(zk.getSessionId)

    //val zookeeper = zkClient.apply()
    //zookeeper.onSuccess( zk => println(zk.getSessionId ))
    // getChildren("\", true))
    //val result = Await.result(zookeeper, Duration.fromSeconds(25))

    //val children = result.getChildren("/", false)

    /*
    Await.ready(zkClient.withRetries(3).retrying{ _ =>
      i = i + 1
      Future.exception(new Exception)
    }.onSuccess { _ =>
    }
    )
    */

    //val znode = zkClient("/kestrel").asInstanceOf[ZNode]
    //val data = znode.getData

    //zkClient.
    Thread.sleep(10000)
  }
}
