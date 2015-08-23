package rosalind

import java.util.Scanner

import ui.SolutionTextAreaToTextArea

import scala.io.Source

object DDEG extends SolutionTextAreaToTextArea {

  type EdgeStream = Stream[(Int,Int,Boolean,Boolean)]

  def add(m:Map[Int,Int],k:Int,s:Int) : Map[Int,Int] = m.get(k) match {
    case None => m + (k -> s)
    case Some(j) => m + (k -> (j+s))
  }

  def main(args:Array[String]): Unit = {
    val inp = Source.stdin.mkString
    println(solution(inp))
  }

  def computeDDeg(edges: Stream[(Int,Int)], n: Int) : Seq[Int] = {
    val zeroMap = (1 to n).foldLeft(Map[Int, Int]()) { case (m,k) => m + (k->0) }
    val deg = edges.foldLeft(zeroMap) {
      case (m, (s, d)) => add(add(m, s,1), d, 1)
    }
    val ddeg = edges.foldLeft(zeroMap) {
      case (m, (s, d)) => add(add(m, s, deg(d)), d, deg(s))
    }
    (1 to n).map(ddeg(_))
  }

  def solution(input: String) : String = {
    val inp = new Scanner(input)
    val n = inp.nextInt
    inp.nextInt
    val edges = {
      def makeEdgeElem(flag: Boolean) = if (flag) (inp.nextInt,inp.nextInt,inp.hasNextInt,flag) else (0,0,false,flag)
      lazy val edges: EdgeStream = makeEdgeElem(true) #:: edges.map { t => makeEdgeElem(t._3) }
      edges.takeWhile(_._4).map(t => (t._1,t._2))
    }
    computeDDeg(edges,n).mkString(" ")
  }
}