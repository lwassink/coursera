def abs(x:Double) = if (x < 0) -x else x

def sqrt(x: Double) = {
  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  def isGoodEnough(guess: Double) =
    abs(guess * guess - x) / x < 0.01

  def improve(guess: Double) = (guess + x / guess) / 2

  sqrtIter(1.0)
}

sqrt(1e-6)
sqrt(1e60)
sqrt(0.0001)


def factorial(n: Int): Int = {
  def fac_iter(n: Int, cur: Int): Int =
    if (n == 1) cur
    else fac_iter(n - 1, n * cur)

  fac_iter(n, 1)
}

factorial(3)
factorial(6)
