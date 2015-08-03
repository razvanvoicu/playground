import java.util.Scanner

import scala.io.Source

object MER {
  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(Source.stdin.bufferedReader())
    val n = scanner.nextInt
    val a = (1 to n).map(_ => scanner.nextInt).toArray
    val m = scanner.nextInt
    val b = (1 to m).map(_ => scanner.nextInt).toArray
    val ab = Array.fill(n+m)(0)
    (0 until n+m).foldLeft((0,0)){
      case ((i,j),k) =>  () match {
        case _ if j >= m || i < n && j < m && a(i) < b(j) => { ab(k) = a(i); (i+1,j) }
        case _ if j < m => { ab(k) = b(j); (i,j+1) }
        case _ => throw new Error("Should never happen")
      }

    }
    println(ab.mkString(" "))
  }
}
