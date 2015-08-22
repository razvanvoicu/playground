package rosalind

import scala.io.Source

object SUBS {
  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines.toArray
    val text = lines(0)
    val pat = lines(1)
    val pos = (0 to text.length-1).filter(i => text.indexOf(pat,i) == i).map(_ + 1)
    println(pos.mkString(" "))
  }
}