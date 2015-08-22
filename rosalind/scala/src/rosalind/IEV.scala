package rosalind

import java.util.Scanner

import scala.io.Source

object IEV {
  def main(args: Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader())
    val n1 = inp.nextInt
    val n2 = inp.nextInt
    val n3 = inp.nextInt
    val n4 = inp.nextInt
    val n5 = inp.nextInt
    val n6 = inp.nextInt
    println(n1*2.0+n2*2.0+n3*2.0+n4*1.5+n5)
  }
}
