package example

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

import scala.jdk.CollectionConverters.SeqHasAsJava

class ZooKeeperContainer(dockerImageName: DockerImageName) extends GenericContainer[ZooKeeperContainer](dockerImageName) {

  val ZookeeperPort = 2181
  setExposedPorts(List(ZookeeperPort).map(Integer.valueOf).asJava)

  def this(tag: String = "latest") = this(DockerImageName.parse("zookeeper").withTag(tag))

  def getConnectString(): String = String.format("%s:%s", getHost, getMappedPort(ZookeeperPort))

}