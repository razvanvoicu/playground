import java.util.Scanner
import scala.io.Source

object MAJ {
  def main(args: Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader)
    val k = inp.nextInt
    val n = inp.nextInt
    (1 to k).foreach{
      i =>
        val a = (1 to n).map(_ => inp.nextInt)
        val counts = a.foldLeft(Map[Int,Int]()) {
          case (m,e) => m.get(e) match {
            case None => m + (e -> 1)
            case Some(c) => m + (e -> (m(e)+1))
          }
        }
        val m = counts.foldLeft((0,Int.MinValue)) {
          case ((cure,curmx),(e,c)) =>
            if ( curmx < c )
              (e,c)
            else
              (cure,curmx)
        }
        print ( " " + (if (m._2 > n / 2) m._1 else -1) )
    }
    println
  }
}
