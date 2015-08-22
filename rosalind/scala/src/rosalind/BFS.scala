package rosalind

import java.util.Scanner

import ui.SolutionStringToTextArea

import scala.io.Source

object BFS extends SolutionStringToTextArea {
  def readEdges(inp:Scanner): Stream[(Int, Int)] = {
    def makeElem(h: Boolean) = if (h) (inp.nextInt, inp.nextInt, inp.hasNextInt, h) else (0, 0, false, h)
    lazy val edges: Stream[(Int, Int, Boolean, Boolean)] = makeElem(true) #:: edges.map { case (_, _, h, _) => makeElem(h) }
    edges.takeWhile(_._4).map(t => (t._1, t._2))
  }

  def adjList(n:Int,edges:Stream[(Int,Int)]) : Array[Array[Int]] = {
    val graph = Array.fill[List[Int]](n+1)(Nil)
    edges.foreach {
      case (s,d) =>
        graph(s) = d :: graph(s)
    }
    graph.map(_.toArray)
  }

  def shortestDist(n:Int,graph:Array[Array[Int]]) : Array[Int] = {
    val dist : Array[Int] = (0 :: 0 :: List.fill[Int](n-1)(Int.MaxValue)).toArray
    val queue = (1 to n).map(i => (dist(i),i)).toSet
    lazy val it : Stream[Set[(Int,Int)]] = queue #:: it.map{
      q =>
        val u = q.min
        graph(u._2).foreach{
          v =>
            val alt = if (u._1 == Int.MaxValue) u._1 else u._1 + 1
            if (alt < dist(v)) dist(v) = alt
        }
        (q - u).map{ case (_,v) => (dist(v),v) }
    }
    it.takeWhile(! _.isEmpty).last
    dist.map(d => if (d == Int.MaxValue) -1 else d)
  }

  def solution(in: String): String = {
    val inp = new Scanner(in)
    val n = inp.nextInt
    inp.nextInt
    val edges = readEdges(inp)
    val graph = adjList(n,edges)
    val shortest = shortestDist(n,graph)
    shortest.tail.mkString(" ")
  }

  def main(args: Array[String]) : Unit = {
    val inp = Source.stdin.getLines().mkString("\n")
    println(solution(inp))
  }
}