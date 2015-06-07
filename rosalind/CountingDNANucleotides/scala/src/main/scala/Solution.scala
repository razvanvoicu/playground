import java.util.Scanner

object Solution {
  def main(args: Array[String]) = {
    val in = new Scanner(System.in)
    val input = in.nextLine()
    val counts = "ACGT".map(c => input.filter(_.==(c)).length)
    val out : String = counts.tail.foldLeft(counts.head.toString)((x,y) => x ++ (" " + y))
    println(out)
  }
}
