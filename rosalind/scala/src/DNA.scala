package rosalind

import scala.io.Source

object DNA {
  def main(args: Array[String]): Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0);
    println("ACGT".map(c => inp.filter(_ == c).length).mkString(" "))
  }
}