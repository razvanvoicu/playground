import org.scalatest._

class Test extends FlatSpec with Matchers {
  "test1" should "doSomething" in {
    assert(Solution.f()==1)
  }
}
