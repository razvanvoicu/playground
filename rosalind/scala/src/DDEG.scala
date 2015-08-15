package rosalind

import java.util.Scanner

import scala.io.Source

object DDEG {

  type EdgeStream = Stream[(Int,Int,Boolean,Boolean)]

  def makeEdgeElem(inp:Scanner, flag: Boolean) = if (flag) (inp.nextInt,inp.nextInt,inp.hasNextInt,flag) else (0,0,false,flag)

  def add(m:Map[Int,Int],k:Int,s:Int) : Map[Int,Int] = m.get(k) match {
    case None => m + (k -> s)
    case Some(j) => m + (k -> (j+s))
  }

  def main(args:Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader)
    val n = inp.nextInt
    inp.nextInt
    val edges = {
      lazy val edges: EdgeStream = makeEdgeElem(inp, true) #:: edges.map { t => makeEdgeElem(inp, t._3) }
      edges.takeWhile(_._4).map(t => (t._1,t._2))
    }
    val zeroMap = (1 to n).foldLeft(Map[Int, Int]()) { case (m,k) => m + (k->0) }
    val deg = edges.foldLeft(zeroMap) {
      case (m, (s, d)) => add(add(m, s,1), d, 1)
    }
    val ddeg = edges.foldLeft(zeroMap) {
      case (m,(s,d)) => add(add(m,s,deg(d)),d,deg(s))
    }
    println((1 to n).map(ddeg(_)).mkString(" "))
  }
}