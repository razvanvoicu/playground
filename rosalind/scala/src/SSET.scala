package rosalind

import java.util.Scanner

import scala.io.Source

object SSET {
  def main(args: Array[String]): Unit = {
    val n = new Scanner(Source.stdin.bufferedReader).nextInt
    println((BigInt(1) << n) % 1000000)
  }
}