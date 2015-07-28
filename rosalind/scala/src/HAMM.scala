import scala.io.Source

object HAMM {
  def main(args:Array[String]): Unit = {
    def diff(p:(Char,Char)) = p._1 != p._2
    val lines = Source.stdin.getLines.toArray
    val hamm = lines(0).zip(lines(1)).filter(diff).length
    println(hamm)
  }
}