package berry.examples.com.SourceData

import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession

/**
When reading data from any file source, Spark might face issues if the file contains any bad or corrupted records.
Let’s say, as per the schema, we were expecting some column values as IntegerType but we received StringType or
DoubleType from the source. As a data engineer, we need to handle these kinds of scenarios, or else Spark will not be
able to parse these records and will give a null for these corrupted records, and we will not be able to find or
identify these bad/corrupted records.

Solution:
1. PERMISSIVE
2. DROPMALFORMED
3. FailFast
4. columnNameOfCorruptRecord Option
5. badRecordsPath
*/

object corruptedRecord extends App {

  val filePath = this.getClass.getResource("/Data/corruptedData.csv").toURI
  val spark: SparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("corruptedRecord")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val dataSchema = new StructType()
    .add(StructField("id", IntegerType, true))
    .add(StructField("Name", StringType, true))
    .add(StructField("Salary", IntegerType, true))
    .add(StructField("City", StringType, true))
    .add(StructField("CorruptRecord", StringType, true))

  val dataCorrectSchema = new StructType()
    .add(StructField("id", IntegerType, true))
    .add(StructField("Name", StringType, true))
    .add(StructField("Salary", IntegerType, true))
    .add(StructField("City", StringType, true))

  //  val FailFast = spark.read
  //    .format("csv")
  //    .schema(dataCorrectSchema)
  //    .option("mode", "FAILFAST")
  //    .load(filePath.toString)
  //  FailFast.show(false)

  /**
   * FailFast: In this mode, Spark throws an exception and halts the data loading process when it finds any bad or corrupted records.
   * Caused by: org.apache.spark.SparkException: Malformed records are detected in record parsing. Parse Mode: FAILFAST. To process malformed records as null result, try setting the option 'mode' as 'PERMISSIVE'.
   */

  //    val columnNameOfCorruptRecord = spark.read
  //    .format("csv")
  //    .schema(dataSchema)
  //    .option("columnNameOfCorruptRecord", "CorruptRecord")
  //    .load(filePath.toString)
  //  columnNameOfCorruptRecord.show(false)

  /** columnNameOfCorruptRecord Option : This will Store all the corrupted records in new column. This extra column must be defined in schema.
   *
   * +----+-----+------+---------+-----------------------+
   * |id  |Name |Salary|City     |CorruptRecord          |
   * +----+-----+------+---------+-----------------------+
   * |1   |berry|10000 |bangalore|null                   |
   * |2   |ayub |20000 |indore   |null                   |
   * |3   |eram |30000 |bhopal   |null                   |
   * |4   |123  |40000 |pune     |null                   |
   * |null|rohan|50000 |delhi    |rohit,rohan,50000,delhi|
   * |5   |makam|null  |kolkatta |5,makam,noida,kolkatta |
   * |6   |ajay |null  |chennai  |6,ajay,mumbai,chennai  |
   * +----+-----+------+---------+-----------------------+
   *
   */

  //  val Permissive = spark.read
  //    .format("csv")
  //    .schema(dataCorrectSchema)
  //    .option("mode","PERMISSIVE")
  //    .load(filePath.toString)
  //  Permissive.show(false)

  /**
   * PERMISSIVE : This is the default mode. Spark will load and process both complete and corrupted data, but for corrupted data it will store null.
   * +----+-----+------+---------+
   * |id  |Name |Salary|City     |
   * +----+-----+------+---------+
   * |1   |berry|10000 |bangalore|
   * |2   |ayub |20000 |indore   |
   * |3   |eram |30000 |bhopal   |
   * |4   |123  |40000 |pune     |
   * |null|rohan|50000 |delhi    |
   * |5   |makam|null  |kolkatta |
   * |6   |ajay |null  |chennai  |
   * +----+-----+------+---------+
   */

  //  val DropMalformed = spark.read
  //    .format("csv")
  //    .schema(dataCorrectSchema)
  //    .option("mode", "DROPMALFORMED")
  //    .load(filePath.toString)
  //  DropMalformed.show(false)

  /**
   * DROPMALFORMED : This mode will drop the corrupted records and will only show the correct records.
   * +---+-----+------+---------+
   * |id |Name |Salary|City     |
   * +---+-----+------+---------+
   * |1  |berry|10000 |bangalore|
   * |2  |ayub |20000 |indore   |
   * |3  |eram |30000 |bhopal   |
   * |4  |123  |40000 |pune     |
   * +---+-----+------+---------+
   */

  val badRecordsPath = spark.read
    .format("csv")
    .schema(dataCorrectSchema)
    .option("badRecordsPath", "/tmp/")
    .load(filePath.toString)
  badRecordsPath.show(false)

  /**
   * badRecordsPath: Spark processes only the correct records and corrupted or bad records are excluded. Corrupted or bad records will be stored in a file at the badRecordsPath location.
   *
   * +----+-----+------+---------+
   * |id  |Name |Salary|City     |
   * +----+-----+------+---------+
   * |1   |berry|10000 |bangalore|
   * |2   |ayub |20000 |indore   |
   * |3   |eram |30000 |bhopal   |
   * |4   |123  |40000 |pune     |
   * |null|rohan|50000 |delhi    |
   * |5   |makam|null  |kolkatta |
   * |6   |ajay |null  |chennai  |
   * +----+-----+------+---------+
   */

}