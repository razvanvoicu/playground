package rosalind

import ui.SolutionTextAreaToTextField

import scala.io.Source

object DNA extends SolutionTextAreaToTextField {

  def solution(inp:String) : String = "ACGT".map(c => inp.filter(_ == c).length).mkString(" ")

  def main(args: Array[String]): Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0);
    println(solution(inp))
  }
}