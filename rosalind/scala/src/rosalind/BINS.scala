package rosalind

import java.util.Scanner

import ui.SolutionStringToTextArea

import scala.io.Source

object BINS extends SolutionStringToTextArea {
  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines().mkString("\n")
    println(solution(inp))
  }

  def solution(input:String) : String = {
    val scanner = new Scanner(input)
    val n = scanner.nextInt
    val m = scanner.nextInt
    val a = (1 to n).map(_ => scanner.nextInt).toArray
    val b = (1 to m).map(_ => scanner.nextInt).toArray
    b.map(x => a.indexOf(x) match {case -1 => -1 ; case k => k+1}).mkString(" ")
  }
}
