import scala.io.Source

object TREE {
  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines.toArray
    val n = lines(0).toInt
    val a = lines.size - 1
    println(n-a-1)
  }
}
