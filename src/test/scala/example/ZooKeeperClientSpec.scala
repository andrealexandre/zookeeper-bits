package example

import com.typesafe.scalalogging.StrictLogging
import example.Implicits.FutureWait
import org.apache.zookeeper.Watcher.Event.KeeperState
import org.apache.zookeeper.{CreateMode, WatchedEvent, ZooDefs, ZooKeeper}
import org.scalatest.matchers.should

import java.nio.charset.Charset
import java.util.concurrent.CountDownLatch
import scala.concurrent.{ExecutionContext, Future}

class ZooKeeperClientSpec extends ZooKeeperBaseSpec with should.Matchers with StrictLogging {

  implicit val ec: ExecutionContext = ExecutionContext.global

  def zooKeeperClientFactory(sessionTimeout: Int = 2000): Future[ZooKeeper] = {
    val countDownLatch = new CountDownLatch(1)

    val zooKeeper = new ZooKeeper(zookeeperContainer.getConnectString(), sessionTimeout, (event: WatchedEvent) => {
      logger.info(s"Zookeeper event=$event")
      if (event.getState == KeeperState.SyncConnected) {
        countDownLatch.countDown()
      }
    })

    Future {
      countDownLatch.await()
      zooKeeper
    }
  }

  "Zookeeper client" should "connect to container" in {

    val zookeeper = zooKeeperClientFactory().await()

    zookeeperContainer.getContainerId should not be(empty)
    zookeeper.getState should be(ZooKeeper.States.CONNECTED)
  }

  it should "create a new ephemeral znode" in {

    val client1 = zooKeeperClientFactory().await()
    client1.create("/test", "test data".toArray.map(_.toByte), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
    val data = client1.getData("/test", false, null)
    new String(data, Charset.defaultCharset()) should be("test data")
    client1.close()

    val client2 = zooKeeperClientFactory().await()
    val stat = client2.exists("/test", false)
    stat should be(null)
  }

  it should "create a new persistent znode" in {

    val client1 = zooKeeperClientFactory().await()
    client1.create("/test", "test data".toArray.map(_.toByte), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    val data = client1.getData("/test", false, null)
    new String(data, Charset.defaultCharset()) should be("test data")
    client1.close()

    val client2 = zooKeeperClientFactory().await()
    val readData = client2.getData("/test", false, null)
    new String(readData, Charset.defaultCharset()) should be ("test data")
  }

}
