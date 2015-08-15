package rosalind

import java.util.Scanner

import scala.io.Source

object SETO {
  def readSet(s: String) : Set[Int] = {
    val scanner = new Scanner(s.substring(1, s.length - 1)).useDelimiter(",[ ]")
    lazy val numbers: Stream[(Int, Boolean, Boolean)] = (scanner.nextInt, scanner.hasNextInt, true) #:: numbers.map { case (_, h, _) => if (h) (scanner.nextInt, scanner.hasNextInt, h) else (0, false, h) }
    numbers.takeWhile(_._3).map(_._1).toSet
  }

  def printSet(s : Set[Int]): Unit = {
    println("{" + s.toArray.sorted.mkString(", ") + "}")
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.stdin.getLines.toArray
    val set1 = readSet(lines(1))
    val set2 = readSet(lines(2))
    printSet(set1 ++ set2)
    printSet(set1.intersect(set2))
    printSet(set1.diff(set2))
    printSet(set2.diff(set1))
    val u = (1 to lines(0).toInt).toSet
    printSet(u.diff(set1))
    printSet(u.diff(set2))
  }
}