package TapeEquilibrium

import org.scalatest._

class Test extends FunSuite {
  test("sample") {
    assert(1 == Solution.solution(Array(3,1,2,4,3)))
  }
}
