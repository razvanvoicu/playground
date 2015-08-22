package rosalind

import java.util.Scanner

import scala.io.Source

object MS {
  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(Source.stdin.bufferedReader())
    val n = scanner.nextInt
    val a = (1 to n).map(_ => scanner.nextInt).toArray
    println(a.sorted.mkString(" "))
  }
}
