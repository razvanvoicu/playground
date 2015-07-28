package rosalind

import scala.io.Source

object RNA {
  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0)
    println(inp.replace('T','U'))
  }
}