package berry.examples.com.DataFrame

import org.apache.spark.sql.SparkSession

object JoinsDemo extends App {

  val spark = SparkSession.builder()
    .appName("Joins Demo")
    .master("local[*]")
    .getOrCreate()

  val sc = spark.sparkContext

  import spark.implicits._

  case class Payments(paymentId: Int, customerId: Int, amount: Int)

  case class Customer(customerId: Int, customerName: String)

  val payment = sc.parallelize(Seq(
    (1, 100, 2509090), (2, 200, 456453), (3, 300, 4321), (4, 400, 5678)
  )).toDF("paymentId", "customerId", "amount").as[Payments]
  //payment.show()
  /**
   * +---------+----------+-------+
   * |paymentId|customerId| amount|
   * +---------+----------+-------+
   * |        1|       100|2509090|
   * |        2|       200| 456453|
   * |        3|       300|   4321|
   * |        4|       400|   5678|
   * +---------+----------+-------+
   */

  val customer = sc.parallelize(Seq((100, "Bose"), (200, "Ayub"), (103, "Ram")))
    .toDF("customerId", "customerName").as[Customer]
  // customer.show()

  /**
   * +----------+------------+
   * |customerId|customerName|
   * +----------+------------+
   * |       100|        Bose|
   * |       200|        Ayub|
   * |       103|         Ram|
   * +----------+------------+
   */

  /**
   * Left Anti Join
   * Left Anti Join does the exact opposite of the left semi join. It returns only columns from the left dataset for non-matched records.
   */
  val leftAntiResult = payment.as("p")
    .join(customer.as("c"), $"p.customerId" === $"c.customerId", "leftanti")
  // leftAntiResult.show(false)
  /**
   * +---------+----------+------+
   * |paymentId|customerId|amount|
   * +---------+----------+------+
   * |3        |300       |4321  |
   * |4        |400       |5678  |
   * +---------+----------+------+
   */

  /**
   * Left Semi join is similar to inner join, the difference being left semi join returns all columns from the left
   * dataset and ignores all columns from the right dataset. In other words, this join returns columns from the only
   * left dataset for the records match in the right dataset on join expression, records not matched on join expression
   * are ignored from both left and right datasets
   */
  val leftSemiResult = payment.as("p")
    .join(customer.as("c"), $"p.customerId" === $"c.customerId", "leftsemi")
  leftSemiResult.show(false)

  /**
   * +---------+----------+-------+
   * |paymentId|customerId|amount |
   * +---------+----------+-------+
   * |1        |100       |2509090|
   * |2        |200       |456453 |
   * +---------+----------+-------+
   */
}
