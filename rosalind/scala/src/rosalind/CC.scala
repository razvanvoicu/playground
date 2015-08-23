package rosalind

import java.util.Scanner

import ui.SolutionStringToTextField

import scala.io.Source

object CC extends SolutionStringToTextField {
  type EdgeStream = Stream[(Int,Int,Boolean,Boolean)]

  def makeEdgeElem(inp:Scanner, flag: Boolean) = if (flag) (inp.nextInt,inp.nextInt,inp.hasNextInt,flag) else (0,0,false,flag)

  def mult(a:Array[Set[Int]], b:Array[Set[Int]]) : Array[Set[Int]] = {
    a.map { s => s ++ s.foldLeft(Set[Int]()){(s,e) => s ++ b(e) } }
  }

  def main(args:Array[String]): Unit = {
    val input = Source.stdin.mkString
    println(solution(input))
  }

  def solution(input:String):String = {
    val inp = new Scanner(input)
    val n = inp.nextInt
    inp.nextInt
    val edges = {
      lazy val edges: EdgeStream = makeEdgeElem(inp, true) #:: edges.map { t => makeEdgeElem(inp, t._3) }
      edges.takeWhile(_._4).map(t => (t._1, t._2))
    }
    val adj = Array.fill[Set[Int]](n+1)(Set())
    edges.foreach {
      case (s,d) =>
        adj(s) = adj(s) + d
        adj(d) = adj(d) + s
    }
    var emptyId = 0
    val components = (1 to n).foldLeft(adj){case (a,_) => mult(a,adj)}.map { s => if (s.isEmpty) {emptyId -= 1; emptyId} else s.min }.toSet
    (components.size - 1).toString
  }
}