package rosalind

import scala.io.Source

object CONS {
  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines
    val dna = lines.foldLeft(List[(String,String)]()) {
      case (l,s) if s.startsWith(">") => (s.tail,"") :: l
      case (l,s) => (l.head._1,l.head._2 + s) :: l.tail
    }
    val cons = dna.map(_._2).transpose.map(l => "ACGT".map(c => (l.count(_ == c),c)).max).map(_._2)
    println(cons.mkString(""))
    val freq = dna.map(_._2).transpose.map(l => "ACGT".map(c => l.count(_ == c))).transpose.map(_.mkString(" "))
    "ACGT" zip freq foreach(p => println(p._1 + ": " + p._2))
  }
}
