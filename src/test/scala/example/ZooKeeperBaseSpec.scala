package example

import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec

trait ZooKeeperBaseSpec extends AnyFlatSpec with BeforeAndAfterAll {

  val zookeeperContainer: ZooKeeperContainer = new ZooKeeperContainer()

  override protected def beforeAll(): Unit = zookeeperContainer.start()

  override protected def afterAll(): Unit = zookeeperContainer.stop()
}
