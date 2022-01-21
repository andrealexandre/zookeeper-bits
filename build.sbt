import Dependencies.{testContainers, _}

ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "org.aalexandre"
ThisBuild / organizationName := "aalexandre"

name             := "zookeeper-bits"
libraryDependencies ++= Seq(
  slf4j,
  logback,
  scalaLogging,

  zookeeper,

  akkaSlf4j,
  akkaStream,
  jacksonDatabind,

  scalaTest % Test,
  testContainers % Test
)