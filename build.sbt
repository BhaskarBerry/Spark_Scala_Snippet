name := "Spark_Scala_Snippet"

version := "0.1"

scalaVersion := "2.13.10"

organization := "berry.examples.com"

val sparkVersion= "3.3.1"

//autoScalaLibrary := false

val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

libraryDependencies ++= sparkDependencies ++ testDependencies