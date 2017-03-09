package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def increment(char: Char): Int =
        if (char == '(') 1 else if (char == ')') -1 else 0

      def balIter(chars: List[Char], bal: Int): Boolean =
        if (bal < 0) false
        else if (chars.isEmpty) bal == 0
        else balIter(chars.tail, bal + increment(chars.head))

      balIter(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0) return 1
      if (coins.isEmpty) return 0
      if (money < 0) return 0

      (coins.zipWithIndex foldLeft(0)) {
        (count, e) => count + countChange(money - e._1, coins.drop(e._2))
      }
    }
  }
