def last[T](list: List[T]): T = list match {
  case Nil => throw new IndexOutOfBoundsException
  case x :: Nil => x
  case _ :: xs => last(xs)
}

def init[T](list: List[T]): List[T] = list match {
  case Nil => throw new IndexOutOfBoundsException
  case x :: Nil => Nil
  case x :: xs => x :: init(xs)
}

def reverse[T](list: List[T]): List[T] = {
  def collect(list: List[T], acc: List[T]): List[T] = list match {
    case Nil => acc
    case x :: xs => collect(xs, x :: acc)
  }

  collect(list, Nil)
}

def badConcat[T](xs: List[T], ys: List[T]): List[T] = {
  val rxs = reverse(xs)

  def collect[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case l :: ls => collect(ls, l :: ys)
  }

  collect(rxs, ys)
}

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case Nil => ys
  case z :: zs => z :: concat(zs, ys)
}

def removeAt[T](n: Int, xs: List[T]): List[T] = xs match {
  case Nil => xs
  case y :: ys if n == 0 => ys
  case y1 :: y2 :: ys if n == 1 => y1 :: ys
  case y :: ys => y :: removeAt(n - 1, ys)
}

removeAt(0, List('a', 'b', 'c', 'd'))
removeAt(1, List('a', 'b', 'c', 'd'))
removeAt(2, List('a', 'b', 'c', 'd'))
removeAt(3, List('a', 'b', 'c', 'd'))

def flatten(xs: List[Any]): List[Any] = xs match {
  case Nil => Nil
  case (y :: ys) :: zs => flatten((y :: ys) ++ zs)
  case y :: ys => y :: flatten(ys)
}

flatten(List(List(1, 1), 2, List(3, List(5, 8))))


import math.Ordering

def mergeSort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val mid = xs.length / 2
  if (mid == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(y, x)) y :: merge(xs, ys1)
          else x :: merge(xs1, ys)
      }

    val (xs1, xs2) = xs.splitAt(mid)
    merge(mergeSort(xs1), mergeSort(xs2))
  }
}

mergeSort(List(3,1,5,2,1,4,6,8,3))
mergeSort(List("bear", "club", "apple"))


def transform[T, U](xs: List[T])(f: T => U): List[U] = xs match {
  case Nil => Nil
  case y :: ys => f(y) :: transform(ys)(f)
}

def reduce[T](xs: List[T], acc: T)(f: (T, T) => T): T = xs match {
  case Nil => acc
  case y :: ys => reduce(ys, f(acc, y))(f)
}

def select[T](f: T => Boolean)(xs: List[T]): List[T] = xs match {
  case Nil => Nil
  case y :: ys => if (f(y)) y :: select(f)(ys) else select(f)(ys)
}

val a = List(1, 2, 3, 4)
transform[Int, Int](a)(_ * 2)
reduce(a, 0)(_ + _)
def selectEven(list: List[Int]) = select[Int](x => (x % 2 == 0))(list)
selectEven(a)


def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: _ =>
    val (eq, uneq) = xs.span(_ == x)
    eq :: pack(uneq)
}

pack(List("a", "a", "a", "b", "c", "c", "a"))

def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (ys => (ys.head, ys.length))


encode(List("a", "a", "a", "b", "c", "c", "a"))