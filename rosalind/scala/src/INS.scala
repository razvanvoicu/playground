import java.util.Scanner

import scala.io.Source

object INS {

  def swap(a: Array[Int], i: Int, j: Int) : Int = {
    if (a(i) < a(j)) {
      val aux = a(i)
      a(i) = a(j)
      a(j) = aux
      1
    } else 0
  }

  def insertSort(a : Array[Int]) : Int = {
    (1 to a.size-1).foldLeft(0){
      case (n,i) => (i to 1 by -1).foldLeft(n){
        case (n,k) => n + swap(a,k,k-1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(Source.stdin.bufferedReader())
    val n = scanner.nextInt
    val a = (0 to n-1).map(_ => scanner.nextInt).toArray
    println(insertSort(a))
  }
}
