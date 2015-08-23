package rosalind

import java.util.Scanner

import ui.SolutionTextAreaToTextArea

import scala.io.Source

object DIJ extends SolutionTextAreaToTextArea {
  def readEdges(inp:Scanner): Stream[(Int, Int, Int)] = {
    def makeElem(h: Boolean) = if (h) (inp.nextInt, inp.nextInt, inp.nextInt, inp.hasNextInt, h) else (0, 0, 0, false, h)
    lazy val edges: Stream[(Int, Int, Int, Boolean, Boolean)] = makeElem(true) #:: edges.map { case (_, _, _, h, _) => makeElem(h) }
    edges.takeWhile(_._5).map(t => (t._1, t._2, t._3))
  }

  def adjMat(n:Int,edges:Stream[(Int,Int,Int)]) : Array[Array[Int]] = {
    val graph = Array.fill[Array[Int]](n+1)(Array.fill(n+1)(Int.MaxValue))
    edges.foreach { case (s,d,l) => graph(s)(d) = Math.min(graph(s)(d),l) }
    graph
  }

  def shortestDist(n:Int,graph:Array[Array[Int]]) : Array[Int] = {
    val dist : Array[Int] = (0 :: 0 :: List.fill[Int](n-1)(Int.MaxValue)).toArray
    val queue = (1 to n).map(i => (dist(i),i)).toSet
    lazy val it : Stream[Set[(Int,Int)]] = queue #:: it.map{
      q =>
        val u = q.min
        (1 to n).foreach{
          v =>
            val alt = if (graph(u._2)(v) == Int.MaxValue || u._1 == Int.MaxValue) Int.MaxValue else graph(u._2)(v) + u._1
            if (alt < dist(v)) dist(v) = alt
        }
        (q - u).map{ case (_,v) => (dist(v),v) }
    }
    it.takeWhile(! _.isEmpty).last.last
    dist.map(d => if (d == Int.MaxValue) -1 else d)
  }

  def solution(in:String): String = {
    val inp = new Scanner(in)
    val n = inp.nextInt
    inp.nextInt
    val edges = readEdges(inp)
    val graph = adjMat(n,edges)
    val shortest = shortestDist(n,graph)

    shortest.tail.mkString(" ")
  }

  def main(args: Array[String]): Unit = {
    val input = Source.stdin.getLines().mkString("\n")
    println(solution(input))
  }
}