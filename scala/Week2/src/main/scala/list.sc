trait List[T] {
  def isEmpty(): Boolean
  def head(): T
  def tail(): List[T]
  override def toString(): String
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty(): Boolean = false
  override def toString(): String = head +
    (if (tail.isEmpty()) "" else ":") +
    tail.toString
}

class Nil[T]() extends List[T] {
  def isEmpty(): Boolean = true
  def head() = throw new NoSuchElementException("Nil.head")
  def tail() = throw new NoSuchElementException("Nil.tail")
  override def toString(): String = ""
}

val a = new Cons[Integer](1, new Nil[Integer])
val b = new Cons[Integer](2, a)
val c = new Cons[Integer](3, b)
c.toString()

def nth[T](list: List[T], n: Integer): T = {
  if (list.isEmpty()) throw new IndexOutOfBoundsException("List not long enough")
  else if (n == 0) list.head()
  else nth(list.tail, n - 1)
}

nth(c, 2)
nth(c, 1)
nth(c, 0)