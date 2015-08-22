package rosalind

import scala.io.Source

object GRPH {
  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines
    val dna = lines.foldLeft(List[(String,String)]()) {
      case (l,s) if s.startsWith(">") => (s.tail,"") :: l
      case (l,s) => (l.head._1,l.head._2 + s) :: l.tail
    }
    val arcs = for {
      s <- dna
      d <- dna
      if s._1 != d._1
      if s._2.substring(s._2.length-3) == d._2.substring(0,3)
    } yield (s._1,d._1)
    println(arcs.map(p => p._1 + " " + p._2).mkString("\n"))
  }
}
