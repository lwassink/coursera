import math.abs

def scalarProduct(xs: List[Double], ys: List[Double]): Double =
  (for ((x, y) <- xs zip ys) yield x * y) sum

scalarProduct(List(1,2,3), List(2,1,3))

object nqueens {
  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens

    placeQueens(n)
  }

  def queensM(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int, prevPlaces: Map[Int, Set[List[Int]]]): Set[List[Int]] =
      if (prevPlaces(k)) Set(List())
      else for {
        queens <- placeQueens(k - 1, prevPlaces)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens

    placeQueens(n, Map((0, Set(Nil))))
  }

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    def loop(queens: List[Int], row: Int): Boolean = queens match {
      case Nil => true
      case q :: _ if q == col || abs(q - col) == row => false
      case _ :: qs => loop(qs, row + 1)
    }

    loop(queens, 1)
  }
}

nqueens.queens(1)
nqueens.queens(2)
nqueens.queens(3)
nqueens.queens(4)
nqueens.queens(8).size