package example

import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

trait ZooKeeperBaseSpec extends AnyFlatSpec with BeforeAndAfter {

  val zookeeperContainer: ZooKeeperContainer = new ZooKeeperContainer()

}
