package rosalind

import scala.io.Source

object GC {
  def percent(s:String) = {
    val cg = "CG".map(c => s.filter(_ == c).length).sum
    cg.toDouble / s.length
  }
  def main(args: Array[String]) : Unit = {
    val lines = Source.stdin.getLines.toArray
    val idDna = lines.foldLeft(List[(String,String)]()){
      case (l,s) if s.startsWith(">") => ((s.substring(1),"")) :: l
      case (l,s) => l.head match {
        case (title,dna) => (title,dna ++ s) :: l.tail
      }
    }.toMap
    val maxDna = idDna.map(p => (percent(p._2),p._1)).max
    println(maxDna._2)
    println(maxDna._1*100)
  }
}