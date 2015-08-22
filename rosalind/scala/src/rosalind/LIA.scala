package rosalind

import java.util.Scanner

import scala.io.Source

object LIA {

  lazy val factorial : Stream[BigInt] = BigInt(1) #:: factorial.zipWithIndex.map(p => p._1 * (p._2+1))

  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(Source.stdin.bufferedReader)
    val k = scanner.nextInt
    val n = scanner.nextInt
    def choose(n:Int,k:Int) : Double = {
      (factorial(n) / factorial(k) / factorial(n - k)).toDouble
    }
    val children = 1 << k
    val prob = (n to children).foldLeft(0.0) {
      case (p:Double,i) => p + choose(children,i) * Math.pow(0.25,i) * Math.pow(0.75,children-i)
    }
    println(prob)
  }
}
