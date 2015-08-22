package rosalind

import scala.io.Source

object FIBO {
  def main(args: Array[String]): Unit = {
    lazy val fibo : Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibo.zip(fibo.tail).map{case (x,y) => x+y}
    val n = Source.stdin.getLines.toArray.apply(0).toInt
    println(fibo(n))
  }
}
