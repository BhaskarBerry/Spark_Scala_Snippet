package berry.examples.com.DataFrame

import  org.apache.spark.sql.SparkSession

object Sort_OrderBy extends App{

  val spark = SparkSession.builder()
    .appName("Bucketing")
    .master("local")
    .getOrCreate()

  import spark.implicits._

//    val df = spark.createDataFrame(
//      [ ("Andrew", "Johnson", "Engineering", "UK", 34),
//        ("Maria", "Brown", "Finance", "US", 41),
//        ("Michael", "Stevenson", "Sales", "US", 31),
//        ("Mark", "Anderson", "Engineering", "Ireland", 28),
//        ("Jen", "White", "Engineering", "UK", 29)  ] )
//  , [("first_name", "last_name", "department", "country", "age")
//  df = spark.createDataFrame(
//
//  df.show(truncate=False)

}
