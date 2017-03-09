import math.abs

def a = new Rational(1, 2)
def b = new Rational(1, 4)
def c = new Rational(3, 5)
a + b
a * b
a - b
a / b
a < b
b < a
-a
a.max(b)
c.inverse()


class Rational(n: Int, d: Int) {
  require(d != 0, "denomentator must not be zero")

  private val g = abs(gcd(n, d))
  val numer = (if(d < 0) -n else n) / g
  val denom = abs(d)/g

  def this(x: Int) = this(x, 1)

  def inverse(): Rational = new Rational(denom, numer)

  def < (that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational =
    if (this < that) that else this

  def + (that: Rational): Rational =
    new Rational(numer * that.denom + denom * that.numer, denom * that.denom)

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def - (that: Rational): Rational = this + -that

  def / (that: Rational): Rational =
    this * new Rational(that.denom, that.numer)

  override def toString(): String =
    if (denom == 1) numer.toString()
    else numer + "/" + denom

  def unary_- : Rational = new Rational(-numer, denom)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a
    else gcd(b, a % b)
}