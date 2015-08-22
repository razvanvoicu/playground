package rosalind

import java.util.Scanner

import scala.io.Source

object PERM {
  def main(args: Array[String]): Unit = {
    val n = new Scanner(Source.stdin.bufferedReader).nextInt
    def perm(n:Int) : List[List[Int]]= {
      if (n <= 0) List(List())
      else {
        for {
          p <- perm(n-1)
          k <- n-1 to 0 by -1
        } yield { p.take(k) ++ (n :: p.drop(k))}
      }
    }
    val p = perm(n)
    val out = p.map{_.mkString(" ")}.mkString("\n")
    println(p.length)
    println(out)
  }
}