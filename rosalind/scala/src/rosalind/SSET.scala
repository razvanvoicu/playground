package rosalind

import java.util.Scanner

import ui.SolutionTextFieldToTextField

import scala.io.Source

object SSET extends SolutionTextFieldToTextField {
  def main(args: Array[String]): Unit = {
    val n = new Scanner(Source.stdin.bufferedReader).nextInt
    println((BigInt(1) << n) % 1000000)
  }

  override def solution(inp: String): String = ((BigInt(1) << inp.toInt) % 1000000).toString
}

