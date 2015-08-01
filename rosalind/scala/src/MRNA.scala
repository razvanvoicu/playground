import scala.io.Source
import scala.collection._

object MRNA {
  def main(args:Array[String]): Unit = {

    val fname = "res/rna_codon.txt"

    val translator = Source.fromFile(fname).getLines.toArray.foldLeft(Map[Char,Int]()) {
      case (m,s) => {
        val tokens = s.split(" ")
        val key = if ( tokens(1) == "Stop" ) '_' else tokens(1).charAt(0)
        m.get(key) match {
          case None => m + (key -> 1)
          case Some(v) => m + (key -> (v+1))
        }
      }
    }

    val line = Source.stdin.getLines.toArray.apply(0)

    val comb = line.foldLeft(1){
      case (product,c) => product * translator(c) % 1000000
    }

    println((comb*3)% 1000000)
  }
}
