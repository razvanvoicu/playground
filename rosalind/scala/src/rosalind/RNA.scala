package rosalind

import ui.SolutionTextAreaToTextArea

import scala.io.Source

object RNA extends SolutionTextAreaToTextArea {
  def solution(inp:String) : String = inp.replace('T','U')

  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0)
    println(solution(inp))
  }
}
