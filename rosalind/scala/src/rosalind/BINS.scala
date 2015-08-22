package rosalind

import java.util.Scanner

import scala.io.Source

object BINS {
  def main(args: Array[String]) : Unit = {
    val scanner = new Scanner(Source.stdin.bufferedReader())
    val n = scanner.nextInt
    val m = scanner.nextInt
    val a = (1 to n).map(_ => scanner.nextInt).toArray
    val b = (1 to m).map(_ => scanner.nextInt).toArray
    println(b.map(x => a.indexOf(x) match {case -1 => -1 ; case k => k+1}).mkString(" "))
  }
}
