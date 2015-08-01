// https://codility.com/demo/results/demoBJHGH9-ZBS/
package TapeEquilibrium

import scala.collection._

object Solution {
  def solution(A: Array[Int]): Int = {
    val left = A(0)
    val right = A.tail.sum
    A.tail.foldLeft((left,right,Set[Int]())) {
      case ((l,r,b),e) => (l+e,r-e,b + Math.abs(l-r))
    }._3.min
  }
}
