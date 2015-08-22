package rosalind

import scala.io.Source

object PROT {
  def main(args:Array[String]): Unit = {
    val fname = "res/rna_codon.txt"
    val translator = Source.fromFile(fname).getLines.toArray.map(p => { val a = p.split("[ ]+"); (a(0),a(1))}).toMap
    val rna = Source.stdin.getLines.toArray.apply(0)
    val prot = (0 until rna.length by 3).foldLeft(new StringBuilder) {
      case (b,i) if translator(rna.substring(i,i+3)) == "Stop" => b
      case (b,i) => b append translator(rna.substring(i,i+3))
    }
    println(prot)
  }
}