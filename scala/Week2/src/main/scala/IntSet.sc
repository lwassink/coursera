abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
  def toString(): String
}

object EmptySet extends IntSet {
  def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
  def contains(x: Int): Boolean = false
  def union(other: IntSet): IntSet = other
  override def toString(): String = " "
}

class NonEmptySet(value: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(x: Int): IntSet =
    if (x < value) new NonEmptySet(value, left.incl(x), right)
    else if (x > value) new NonEmptySet(value, left, right.incl(x))
    else this

  def contains(x: Int): Boolean =
    if (x < value) left.contains(x)
    else if (x > value) right.contains(x)
    else true

  def union(other: IntSet): IntSet =
    left.union(right).union(other.incl(value))

  override def toString(): String =
    left.toString() + value + right.toString()
}

val a = EmptySet.incl(1).incl(2)
val b = EmptySet.incl(3).incl(4)
val c = a union b

c.contains(1)
c.contains(2)
c.contains(3)
c.contains(4)
c.contains(5)