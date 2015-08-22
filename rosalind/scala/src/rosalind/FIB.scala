package rosalind

import java.util.Scanner

import ui.SolutionTextFieldToTextField

import scala.io.Source

object FIB extends SolutionTextFieldToTextField {
  val bigIntPlus : ((BigInt,BigInt)) => BigInt = p => p._1 + p._2
  lazy val fib : BigInt => Stream[BigInt] = k => 0 #:: 1 #:: fib(k).map(_*k).zip(fib(k).tail).map(bigIntPlus)

  def solution(inp:String) : String = {
    val scanner = new Scanner(inp)
    val n = scanner.nextInt
    val k = scanner.nextBigInteger
    fib(k)(n).toString
  }

  def main(args: Array[String]) : Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader())
    val n = inp.nextInt
    val k = inp.nextBigInteger
    println(fib(k)(n))
  }
}