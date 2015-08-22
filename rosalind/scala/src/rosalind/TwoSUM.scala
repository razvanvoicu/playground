package rosalind

import java.util.Scanner

import scala.io.Source

object TwoSUM {
  def main(args:Array[String]) : Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader)
    val k = inp.nextInt
    val n = inp.nextInt
    (1 to k).foreach {
      i =>
        val a = (1 to n).map(_ => inp.nextInt)
        val s = a.zipWithIndex.filter(_._1 >= 0)
        val f = s.foldLeft(List[(Int, Int)]()) {
          case (l, p) => a.indexWhere(_ == - (p._1)) match {
            case -1 => l
            case v if p._2 < v => (p._2+1,v+1) :: l
            case v if p._2 > v => (v+1,p._2+1) :: l
            case _ => l
          }
        }
        val result = f match {
          case Nil => "-1"
          case _ => f.last._1 + " " + f.last._2
        }
        println(result)
    }
  }
}
