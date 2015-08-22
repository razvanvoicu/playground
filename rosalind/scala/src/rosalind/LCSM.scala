package rosalind

import scala.io.Source

object LCSM {

  case class Node(var children:Array[Node] = Array.fill(4)(null)) {
    var occursCount = 0
  }

  def idx : Char => Int = {
    case 'A' => 0
    case 'C' => 1
    case 'G' => 2
    case 'T' => 3
  }

  def charOf : Int => Char = "ACGT".charAt(_)

  def longestCommonSubstring(n:Node,size:Int) : (Int,String) = {
    val x = n.children.zipWithIndex.filter(p => p._1 != null && p._1.occursCount == size)
    if (x.isEmpty) {
      (0,"")
    } else {
      x.map{
        case (child,k) => longestCommonSubstring(child,size) match {
          case (lgth,s) => (lgth+1,charOf(k) + s)
        }
      }.max
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines
    val dna = lines.foldLeft(List[(String,String)]()) {
      case (l,s) if s.startsWith(">") => (s.tail,"") :: l
      case (l,s) => (l.head._1,l.head._2 + s) :: l.tail
    }
    var root = Node()
    var nodeReused = 0
    dna.zipWithIndex.foreach {
      case ((name, s),i) => {
        s.foldLeft(List(root)) {
          case (l, c) => root :: l.map {
            n =>
              if (n.children(idx(c)) == null)
                n.children(idx(c)) = Node()
              if (n.children(idx(c)).occursCount == i) n.children(idx(c)).occursCount += 1
              n.children(idx(c))
          }
        }
      }
    }
    println(longestCommonSubstring(root,dna.size)._2)
  }
}