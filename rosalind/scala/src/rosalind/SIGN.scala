package rosalind

import java.util.Scanner

import ui.SolutionTextFieldToTextArea

import scala.io.Source

object SIGN extends SolutionTextFieldToTextArea {
  def addSign(p:List[Int],k:Int) : List[Int] = {
    p.foldLeft((k,List[Int]())) {
      case ((k,l),e) => if (k % 2 == 0) (k/2,e :: l) else (k/2, (-e) :: l)
    }._2.reverse
  }

  def perm(n:Int) : List[List[Int]]= {
    if (n <= 0) List(List())
    else {
      for {
        p <- perm(n-1)
        k <- n-1 to 0 by -1
      } yield { p.take(k) ++ (n :: p.drop(k))}
    }
  }

  def solution(inp: String): String = {
    val n = new Scanner(inp).nextInt

    val out = for {
      p <- perm(n)
      q <- 0 until (1 << n)
    } yield { addSign(p,q) }

    out.length + "\n" +
      out.map(_.mkString(" ")).mkString("\n")
  }

  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines.toArray.apply(0)
    println(solution(inp))
  }
}