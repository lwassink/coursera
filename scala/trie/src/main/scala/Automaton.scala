/**
  * Created by lwassink on 4/2/17.
  */


class Automaton(pat: String) {
  type Aut = Map[(Int, Int), Int]
  println(pat.toCharArray)
  val alphabet = 'a' to 'z'
  val R = alphabet.length

  def computeTransitionFunction(string: Array[Char]): Aut = {
    def collect(char: Int, state: Int, trans: Aut): Aut =
      if (state < 0) trans
      else if (char >= R) collect(0, state - 1, trans)
      else collect(char + 1, state, trans.updated((char, state), suffix(alphabet(char), state)))

    def suffix(char: Char, pos: Int): Int = {
      val subPat = pat.substring(0, pos) ++ char.toString
      val len = List(pos + 1, pat.length).min
      if (char == 'b') println(subPat + ", " + pos)
      (0 to len).filter(i =>
        isSufixOf(pat.substring(0, i), subPat)
      ).max
    }

    collect(0, string.length, Map())
  }

  val matcher = this.computeTransitionFunction(pat.toCharArray)

  def isSufixOf(xs: String, ys: String): Boolean =
    if (xs.length > ys.length) false
    else if (xs == "") true
    else if (xs.last != ys.last) false
    else isSufixOf(xs.init, ys.init)

  def search(text: String): List[Int] = {
    val m = pat.length
    val n = text.length

    def collect(idx: Int, state: Int, list: List[Int]): List[Int] = {
      val updatedList = if (state == m) (idx - m) :: list else list

      if (idx >= n) updatedList
      else collect(idx + 1, matcher(text.charAt(idx) - 97, state), updatedList)
    }

    collect(0, 0, Nil)
  }
}


