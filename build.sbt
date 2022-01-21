import Dependencies.{testContainers, _}

scalaVersion      := "2.13.7"
version           := "0.1.0-SNAPSHOT"
organization      := "org.aalexandre"
organizationName  := "aalexandre"

name := "zookeeper-bits"
libraryDependencies ++= Seq(
  slf4j,
  logback,
  scalaLogging,
  zookeeper,
  scalaTest      % Test,
  testContainers % Test
)
