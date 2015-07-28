package rosalind

import java.util.Scanner

import scala.io.Source

object FIB {
  val bigIntPlus : ((BigInt,BigInt)) => BigInt = p => p._1 + p._2
  def fib(k: BigInt) = {
    lazy val fibk:Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibk.map(_*k).zip(fibk.tail).map(bigIntPlus)
    fibk
  }
  def main(args: Array[String]) : Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader())
    val n = inp.nextInt
    val k = inp.nextBigInteger
    println(fib(k)(n))
  }
}