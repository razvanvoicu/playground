package rosalind

import ui.SolutionTextAreaToTextArea

import scala.io.Source

object CONS extends SolutionTextAreaToTextArea{
  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.mkString
    println(solution(lines))
  }

  def solution(input:String) : String = {
    val out = new StringBuilder
    val lines = input.split('\n')
    val dna = lines.foldLeft(List[(String,String)]()) {
      case (l,s) if s.startsWith(">") => (s.tail,"") :: l
      case (l,s) => (l.head._1,l.head._2 + s) :: l.tail
    }
    val cons = dna.map(_._2).transpose.map(l => "ACGT".map(c => (l.count(_ == c),c)).max).map(_._2)
    out.append(cons.mkString("") + "\n")
    val freq = dna.map(_._2).transpose.map(l => "ACGT".map(c => l.count(_ == c))).transpose.map(_.mkString(" "))
    out.append("ACGT" zip freq map(p => p._1 + ": " + p._2) mkString "\n")
    out.toString
  }
}
