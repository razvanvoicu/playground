package rosalind

import scala.io.Source

object REVC {
  def translate : Char => Char = {
    case 'A' => 'T'
    case 'C' => 'G'
    case 'G' => 'C'
    case 'T' => 'A'
    case _ => throw new IllegalArgumentException
  }
  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0)
    println(inp.map(translate).reverse)
  }
}