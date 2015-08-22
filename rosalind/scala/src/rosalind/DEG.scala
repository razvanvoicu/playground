package rosalind

import java.util.Scanner

import scala.io.Source

object DEG {
  def add(m:Map[Int,Int],k:Int) : Map[Int,Int] = m.get(k) match {
    case None => m + (k -> 1)
    case Some(j) => m + (k -> (j+1))
  }

  def main(args:Array[String]): Unit = {
    val inp = new Scanner(Source.stdin.bufferedReader)
    val n = inp.nextInt
    inp.nextInt
    lazy val edges : Stream[(Int,Int,Boolean,Boolean)] = (inp.nextInt,inp.nextInt,inp.hasNextInt,true) #:: edges.map{ case (_,_,h,_) => if (h) (inp.nextInt,inp.nextInt,inp.hasNextInt,h) else (0,0,false,h)}
    val degRaw = edges.takeWhile(_._4).foldLeft(Map[Int,Int]()){
      case (m,(s,d,_,_)) => add(add(m,s),d)
    }
    val deg = (1 to n) map { k => degRaw.get(k) match { case None => 0 ; case Some(v) => v}}
    println(deg.mkString(" "))
  }
}