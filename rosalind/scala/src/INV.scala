import scala.io.Source
import java.util.Scanner

object INV {
  def main(args: Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader)
    val n = inp.nextInt
    val a = (1 to n).map(_ => inp.nextInt).toArray
    val inv = (0 until n).foldLeft(BigInt(0)) {
      case (k,i) =>
        (0 until i).foldLeft(k) {
          case (k,j) => if (a(i) < a(j)) k+1 else k
        }
    }
    println(inv)
  }
}
