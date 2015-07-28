import java.util.Scanner

import scala.io.Source

object FIBD {
  def fib(k:Int) = {
    val delay = Stream.fill[BigInt](k-1)(BigInt(0))
    lazy val born : Stream[BigInt] = BigInt(1) #:: fibd
    lazy val died : Stream[BigInt] = delay append born
    lazy val fibd : Stream[BigInt] = BigInt(0) #::
      died.zip(fibd zip born).map {
        case (a, (b, c)) => b + c - a
      }
    (fibd zip born).map(p => p._1 + p._2)
  }
  def main(args:Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader())
    val n = inp.nextInt
    val m = inp.nextInt
    println(fib(m)(n-1))
  }
}
