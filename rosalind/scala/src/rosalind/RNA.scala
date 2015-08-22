package rosalind

import ui.SolutionStringToTextArea

import scala.io.Source

object RNA extends SolutionStringToTextArea {
  def solution(inp:String) : String = inp.replace('T','U')

  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0)
    println(solution(inp))
  }
}
