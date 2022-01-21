import sbt._

object Dependencies {
  lazy val slf4j = "org.slf4j" % "slf4j-api" % "1.7.30"
  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"

  lazy val zookeeper = "org.apache.zookeeper" % "zookeeper" % "3.3.4"

  lazy val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % "2.6.14"
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.6.14"
  lazy val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.4"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.8"
  lazy val testContainers = "org.testcontainers" % "testcontainers" % "1.15.3"
}
