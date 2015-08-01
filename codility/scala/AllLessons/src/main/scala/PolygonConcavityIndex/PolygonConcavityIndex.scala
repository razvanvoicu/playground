package PolygonConcavityIndex

class Point2D(var x: Int, var y: Int)

object Solution {

  def threePt(A: Array[Point2D], i: Int):Long = {
    val n = A.size
    val distY2:Long = A((i+2)%n).y - A(i).y
    val distX1:Long = A((i+1)%n).x - A(i).x
    val distX2:Long = A((i+2)%n).x - A(i).x
    val distY1:Long = A((i+1)%n).y - A(i).y
    distY2 * distX1 - distX2 * distY1
  }

  def clockwise(n:Int, e:Int, s:Int, w:Int): Option[Boolean] = {
    if ( n == e && s == w && n == w && s == e )
      None
    else {
      val a = n <= e && e <= s && s <= w
      val b = e <= s && s <= w && w <= n
      val c = s <= w && w <= n && n <= e
      val d = w <= n && n <= e && e <= s
      Some (a || b || c || d)
    }
  }

  def solution(A: Array[Point2D]): Int = {
    val n = A.size;
    var countPos = 0
    var countNeg = 0
    var lastPos = -1
    var lastNeg = -1
    var sum:Long = 0L;
    var north = Int.MinValue
    var south = Int.MaxValue
    var west = Int.MaxValue
    var east = Int.MinValue
    var northI = -1
    var southI = -1
    var westI = -1
    var eastI = -1
    var rN = Int.MinValue
    var rS = Int.MaxValue
    var rW = Int.MaxValue
    var rE = Int.MinValue
    var rNi = -1
    var rSi = -1
    var rWi = -1
    var rEi = -1
    (0 until n).foreach {
      i => {
        val x:Long = threePt(A, i);
        sum += x;
        if (x > 0) {
          countPos += 1;
          lastPos = i;
        }
        else if (x < 0) {
          countNeg += 1;
          lastNeg = i;
        }
        if (A(i).x > east) {
          east = A(i).x;
          eastI = i;
        }
        if (A(i).x < west) {
          west = A(i).x;
          westI = i;
        }
        if (A(i).y > north) {
          north = A(i).y;
          northI = i;
        }
        if (A(i).y < south) {
          south = A(i).y;
          southI = i;
        }
        if (A(i).x - A(i).y > rE) {
          rE = A(i).x - A(i).y;
          rEi = i;
        }
        if (A(i).x - A(i).y < rW) {
          rW = A(i).x - A(i).y;
          rWi = i;
        }
        if (A(i).x + A(i).y > rN) {
          rN = A(i).x + A(i).y;
          rNi = i;
        }
        if (A(i).x + A(i).y < rS) {
          rS = A(i).x + A(i).y;
          rSi = i;
        }
      }
    }
    clockwise(northI, eastI, southI, westI) match {
      case Some(c) =>
        if (c) {
          if (countNeg == n)
            -1
          else
            (lastPos + 1) % n;
        } else {
          if (countPos == n)
            -1;
          else
            (lastNeg + 1) % n;
        }
      case None => clockwise(rNi,rEi,rSi,rWi) match {
        case Some(false) =>
          if (countPos == n)
            -1
          else
            (lastNeg + 1) % n;
        case _ =>
          if (countNeg == n)
            -1
          else
            (lastPos + 1) % n
      }
    }
  }
}
