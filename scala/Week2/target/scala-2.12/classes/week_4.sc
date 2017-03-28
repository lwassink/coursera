trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Empty[T] extends List[T] {
  def isEmpty: true
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
}