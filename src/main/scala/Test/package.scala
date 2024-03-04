
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.rand

object Test {
  def main(args: Array[String]) {
    val spark =
      SparkSession.builder()
        .appName("DataFrame-Basic")
        .master("local[4]")
        .getOrCreate()


    val df1 = spark.range(4000)
      .select(
        rand(seed = 42) as "col1",
        rand(seed = 43) as "col2",
        rand(seed = 44) as "col3"
      )
    val df2 = spark.range(1000)
      .select(
        rand(seed = 42) as "col1",
        rand(seed = 43) as "col2"
      )

    val df3 = spark.range(10)
      .select(
        rand(seed = 42) as "col1",
        rand(seed = 43) as "col4"
      )



    // Realizar el left join en la columna 2
    val df4 = df1.join(df2, Seq("col2"), "left")
    // Realizar el join en la columna 1
    val df5 = df4.join(df3,Seq("col1"))
    // Mostrar el resultado
    df4.show()
    df5.show()
  }
}
