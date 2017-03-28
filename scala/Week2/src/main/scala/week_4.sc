//trait List[T] {
//  def isEmpty: Boolean
//  def head: T
//  def tail: List[T]
//}
//
//class Empty[T] extends List[T] {
//  def isEmpty: Boolean = true
//  def head: Nothing = throw new NoSuchElementException
//  def tail: Nothing = throw new NoSuchElementException
//}
//
//class Cons[T](val head: T, val tail: List[T]) extends List[T] {
//  def isEmpty: Boolean = false
//}
//
//object List {
//  def apply[T]() = new Empty[T]
//  def apply[T](a: T) = new Cons(a, new Empty[T])
//  def apply[T](a: T, b: T) = new Cons(a, new Cons(b, new Empty[T]))
//}

trait Expr {
  def eval: Integer = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }

  def show: String = this match {
    case Number(n) => n.toString
    case Var(n) => n
    case Sum(e1, e2) => e1.show + " + " + e2.show
    case Prod(Sum(e1, e2), Sum(e3, e4)) =>
      "(" + Sum(e1, e2).show + ")" + " * " +
        "(" + Sum(e3, e4).show + ")"
    case Prod(Sum(e1, e2), e3) =>
      "(" + Sum(e1, e2).show + ")" + " * " + e3.show
    case Prod(e1, Sum(e2, e3)) =>
      e1.show + " * " + "(" + Sum(e2, e3).show + ")"
    case Prod(e1, e2) =>
      e1.show + " * " + e2.show
  }
}
case class Number(n: Integer) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Var(n: String) extends Expr

Sum(Number(1), Number(2)).eval
Sum(Number(1), Number(2)).show
Prod(Sum(Number(2), Var("x")), Var("y")).show
Sum(Prod(Number(2), Var("x")), Var("y")).show




def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, isort(ys))
}


def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

isort(List(3,2,1))