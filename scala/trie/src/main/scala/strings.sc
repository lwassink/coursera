import scala.math.sqrt


"abcd".substring(1)

Array('a', 'b', 'c').slice(0,1)
"abc".charAt(1)
var x = 1
x = 2

val a = List(1,2)

def f(b: => List[Int]): List[Int] = 3::b

f(a)
a


def isPrime(num: Int) = {
  val s = sqrt(num)
  def loop(fac: Int): Boolean =
    if(fac > s) true
    else if (num % fac == 0) false
    else loop(fac + 2)
  loop(2)
}

isPrime(2)
isPrime(3)
isPrime(4)
isPrime(5)
isPrime(6)
isPrime(7)
scala.Int.MaxValue
isPrime(scala.Int.MaxValue)

"bc"(0)