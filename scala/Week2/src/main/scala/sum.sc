import math.abs

def sum(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}

def prod(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc * f(a))
  }
  loop(a, 1)
}

def fact(n: Int): Int = prod(x => x)(1, n)

def reduce(combine: (Int, Int) => Int, init: Int)
          (f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) init
  else combine(f(a), reduce(combine, init)(f)(a + 1, b))
}

def sumSquares: (Int, Int) => Int =
  reduce((x, y) => x + y, 0)(x => x * x)


def fixedPoint(f: Double => Double)(guess: Double): Double = {
  def isCloseEnough(x: Double) =
    abs((x - f(x)) / x) < 0.01

  def iterate(x: Double): Double =
    if (isCloseEnough(x)) x else iterate(f(x))

  iterate(guess)
}

def sqrt(x: Double): Double =
  fixedPoint(y => (y + x / y) / 2)(1.0)

//
//sum(x => x * x)(1, 3)
//prod(x => x * x)(1, 3)
//fact(4)
//sumSquares(1,3)
//reduce((x, y) => x + y, 0)(x => x * x)(1,3)

//fixedPoint(x => 1 + x / 2)(1)
sqrt(4)
sqrt(9)