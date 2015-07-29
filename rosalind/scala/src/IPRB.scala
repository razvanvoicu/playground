package rosalind

import java.util.Scanner

import scala.io.Source

object IPRB {
  def main(args: Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader())
    val m = inp.nextInt
    val n = inp.nextInt
    val k = inp.nextInt
    val p = n*(n-1)/2 * 3.0/4 + m*(m-1)/2 + n*m + n*k/2.0 + m*k
    val q = n*(n-1)/2.0 + k*(k-1)/2 + m*(m-1)/2 + n*m + n*k + m*k
    println(p/q)
  }
}
