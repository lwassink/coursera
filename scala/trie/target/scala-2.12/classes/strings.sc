import scala.math.sqrt

def isPrime(num: Int) = {
  val s = sqrt(num)
  def loop(fac: Int): Boolean =
    if(fac > s) true
    else if (num % fac == 0) false
    else loop(fac + 2)
  loop(2)
}

isPrime(scala.Int.MaxValue)

"ab".substring(0,1)




val m = new Automaton("b")

//m.search("bcb")
m.search("bdbqlb")
