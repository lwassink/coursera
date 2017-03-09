abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
  def % (that: Nat): Nat
  def >= (that: Nat): Boolean
  def <= (that: Nat): Boolean = that >= this
  def > (that: Nat): Boolean = this >= that && this != that
  def < (that: Nat): Boolean = that > this
  def == (that: Nat): Boolean = this >= that && that >= this
  def != (that: Nat): Boolean = !(this == that)
  override def toString(): String
}

object Zero extends Nat {
  def isZero: Boolean = true

  def predecessor: Nat = throw new IndexOutOfBoundsException("predecessor of zero")

  def + (that: Nat): Nat = that

  def - (that: Nat): Nat =
    if (that.isZero) this
    else throw new IndexOutOfBoundsException("no negative numbers")

  def % (that: Nat): Nat = Zero

  def >= (that: Nat): Boolean = that.isZero

  override def toString(): String = "0"
}


class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false

  def predecessor: Nat = n

  def + (that: Nat): Nat = n + that.successor

  def - (that: Nat): Nat = if (that.isZero) this else n - that.predecessor

  def % (that: Nat): Nat =
    if (that.isZero) this
    else if (this < that) this
    else (this - that) % that

  def >= (that: Nat): Boolean =
    if (that.isZero) true
    else n >= that.predecessor

  override def toString(): String = "0"
}

class Int(val a: Nat, n: Boolean) {
  def neg = a > Zero && n
  def pos = a > Zero && !n
  def abs = new Int(a, false)

  def unary_- (): Int = new Int(a, !n)

  def + (that: Int): Int =
    if (that.a > this.a) that + this
    else if (this.neg) -((-this) + (-that))
    else if (that.neg) new Int(this.a - that.a, false)
    else new Int(this.a + that.a, false)

  def - (that: Int): Int = this + -that

  def * (that: Int) = {
    def loop(n: Nat): Nat =
      if (n.isZero) Zero
      else this.a + loop(n.predecessor)
    new Int(loop(that.a), this.neg ^ that.neg)
  }

  def % (that: Int) = {
    new Int(this.a % that.a, false)
  }

  override def toString(): String = "0"

  def >= (that: Int): Boolean =
    if (this.neg && that.pos) false
    else if (this.pos && that.neg) true
    else if (this.pos && that.pos) this.a >= that.a
    else that.a >= this.a

  def <= (that: Int): Boolean = that >= this
  def > (that: Int): Boolean = this >= that && this != that
  def < (that: Int): Boolean = that > this
  def == (that: Int): Boolean = this >= that && that >= this
  def != (that: Int): Boolean = !(this == that)
}