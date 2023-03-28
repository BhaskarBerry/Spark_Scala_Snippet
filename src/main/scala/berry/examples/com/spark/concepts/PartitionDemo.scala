package berry.examples.com.spark.concepts

import org.apache.spark.sql._

object PartitionDemo extends App {

  val spark = SparkSession.builder()
    .appName("Scala Partition Demo")
    .master("local[*]")
    .getOrCreate()

  val df = spark.read.option("header",
    "false").csv("C:\\Training\\Spark_Scala_Snippet\\src\\main\\resources\\Data\\Sam.csv")
  df.show()


  val l = List("a", "b", "c", "d", "e", "f", "g", "h")

  val  originalList = spark.sparkContext.parallelize(l)

  println("Number of partition we have :"+ originalList.toString())

}
