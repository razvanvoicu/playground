package TapeEquilibrium

object Scratch {
  val moves = Set((2,3),(1,2),(1,3),(1,1),(3,3),(2,2),(3,1))
  def px (idx:Int)(pair:(Int,Int)) : Boolean = idx == pair._1
  def py (idx:Int)(pair:(Int,Int)) : Boolean = idx == pair._2
  def pf (idx:Int)(pair:(Int,Int)) : Boolean = pair._1 == pair._2
  def pi (idx:Int)(pair:(Int,Int)) : Boolean = pair._1 == 4 - pair._2
  val fst : ((Int,Int)) => Int = _._1
  val snd : ((Int,Int)) => Int = _._2
  val x = Array(px _,py _,pf _,pi _).flatMap(p => (1 to 3).toSet.map((i:Int) => moves.filter(p(i)))).filter(_.size == 3)
  val y = for {
    p <- Array(px _,py _,pf _,pi _)
    i <- (1 to 3).toSet[Int]
  } yield moves.filter(p(i))
  def main(a:Array[String]) = {
    x.foreach{println(_)}
    println
    y.foreach{println(_)}
  }
}
